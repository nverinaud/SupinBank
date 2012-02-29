/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.servlet;

import com.supinfo.supinbank.entity.Account;
import com.supinfo.supinbank.entity.Customer;
import com.supinfo.supinbank.service.CustomerService;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nico
 */
@WebServlet(name = "AddCustomerFirstAccountServlet", urlPatterns = {"/advisor/customer/new_first_account"})
public class AddCustomerFirstAccountServlet extends HttpServlet 
{    
    @EJB
    private CustomerService customerService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        if (customer == null)
        {
            response.sendRedirect(getServletContext().getContextPath()+"/advisor/customer/new");
        }
        else
        {
            request.setAttribute("flashInfo", "You must provide " + customer.getFirstname() + " " + customer.getLastname() + " his first account.");
            request.setAttribute("customer", customer);
            request.setAttribute("account", new Account());
            request.getRequestDispatcher("/advisor/customer/new_first_account.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        if (customer == null)
        {
            response.sendRedirect(getServletContext().getContextPath()+"/advisor/customer/new");
        }
        else
        {
            // Handle the creation
            Account account = new Account();
            
            String name = request.getParameter("name");
            if (name.equals(""))
                name = "Main Account";
            account.setName(name);
            
            account.setInterestsPlan(Account.interestsPlanFromString(request.getParameter("interestsPlan")));
            
            account.setOwner(customer);
            customerService.saveCustomer(customer);
            
            request.getSession().removeAttribute("customer");
            request.getSession().setAttribute("flashSuccess", customer.getFirstname() + " " 
                    + customer.getLastname() + " dispose maintenant d'un compte \"" 
                    + Account.stringFromInterestsPlan(account.getInterestsPlan()) + "\" chez SupinBank !");
            response.sendRedirect(getServletContext().getContextPath());
//            response.sendRedirect(getServletContext().getContextPath()+"/advisor/customer/?id="+customer.getId());
        }
    }
}
