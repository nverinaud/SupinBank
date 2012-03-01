/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.seed;

import com.supinfo.supinbank.entity.Account;
import com.supinfo.supinbank.entity.Advisor;
import com.supinfo.supinbank.entity.Customer;
import com.supinfo.supinbank.service.AccountService;
import com.supinfo.supinbank.service.CustomerService;
import com.supinfo.supinbank.service.UserService;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author nico
 */
@WebServlet(name = "PopulateDatabaseServlet", urlPatterns = {"/db/populate"})
public class PopulateDatabaseServlet extends HttpServlet 
{
    @EJB
    private UserService userService;
    
    @EJB
    private CustomerService customerService;
    
    @EJB
    private AccountService accountService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        try
        {
            if (customerService.findAllCustomers().isEmpty())
            {
                createAdvisor();
                int count = 3;
                try
                {
                    count = Integer.parseInt(request.getParameter("count"));
                }
                catch (Exception ex)
                {
                    count = 3;
                }
                createCustomers(count);
                
                request.getSession().setAttribute("flashSuccess", "Database successfully populate.");
            }
            else
            {
                request.getSession().setAttribute("flashInfo", "Database already populate.");
            }
        }
        catch (Exception ex)
        {
            request.getSession().setAttribute("flashError", "Failed to populate databse. See log for more infos.");
        }
        
        response.sendRedirect(getServletContext().getContextPath());
    }
    
    
    private void createAdvisor()
    {
        Advisor advisor = new Advisor();
        advisor.setEmail("advisor@supinbank.com");
        advisor.setPassword(userService.encryptedPassword("foobar"));
        userService.saveUser(advisor);
    }
    
    
    private void createCustomers(int count)
    {
        Customer c;
        for (int i = 0; i < count; i++) 
        {
            c = createCustomer();
            customerService.saveCustomer(c);
            createAccountsForCustomer(i, c);
        }
    }
    
    
    private Customer createCustomer()
    {
        Customer c = new Customer();
        c.setFirstname(randomName());
        c.setLastname(randomName().toUpperCase());
        c.setAddress("No Address");
        c.setCity("No Town");
        c.setEmail(emailFor(c));
        c.setPhone("0110011000");
        c.setZipCode("10001");
        return c;
    }
    
    
    private void createAccountsForCustomer(int count, Customer c)
    {
        Account a;
        for (int i = 0; i < count; i++) 
        {
            a = createAccount();
            a.setOwner(c);
            if (i%4 == 0)
                a.setInterestsPlan(Account.InterestsPlan.LIFE_INSURANCE);
            else if (i%3 == 0)
                a.setInterestsPlan(Account.InterestsPlan.SAVINGS_ACCOUNT);
            else if (i%2 == 0)
                a.setInterestsPlan(Account.InterestsPlan.FIRST_HOME_SAVER_ACCOUNT);
            accountService.saveAccount(a);
        }
    }
    
    
    private Account createAccount()
    {
        Account a = new Account();
        a.setName(randomName());
        return a;
    }
    
    
    private String emailFor(Customer c)
    {
        return c.getFirstname().toLowerCase()+"."+c.getLastname().toLowerCase()+"@supinbank.com";
    }
    
    
    private String randomName()
    {
        return capitalizeFirstLetter(RandomStringUtils.randomAlphabetic(getRandom()));
    }
    
    
    private String capitalizeFirstLetter(String s)
    {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
    
    
    private int getRandom()
    {
        return (int) (Math.random()*(15-3))+3;
    }
}
