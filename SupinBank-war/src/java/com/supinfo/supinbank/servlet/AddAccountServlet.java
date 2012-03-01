/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.servlet;

import com.supinfo.supinbank.entity.Account;
import com.supinfo.supinbank.entity.Customer;
import com.supinfo.supinbank.service.AccountService;
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
@WebServlet(name = "AddAccountServlet", urlPatterns = {"/advisor/account/new"})
public class AddAccountServlet extends HttpServlet 
{
    @EJB
    private CustomerService customerService;
    
    @EJB
    private AccountService accountService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String customerId = request.getParameter("customerId");
        if (customerId == null || customerId.isEmpty())
        {
            request.getSession().setAttribute("flashError", "You have to specify a customer id for which you want to add an account.");
            response.sendRedirect(getServletContext().getContextPath()+"/advisor/customers");
        }
        else
        {
            Customer c = customerService.findCustomerById(Long.parseLong(customerId));
            if (c == null)
            {
               request.getSession().setAttribute("flashError", "No customer with id " + customerId + " found.");
               response.sendRedirect(getServletContext().getContextPath()+"/advisor/customers"); 
            }
            else
            {
                request.setAttribute("account", new Account());
                request.setAttribute("customer", c);
                request.getRequestDispatcher("/advisor/account/new.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String customerId = request.getParameter("customerId");
        if (customerId == null || customerId.isEmpty())
        {
            request.getSession().setAttribute("flashError", "You have to specify a customer id for which you want to add an account.");
            response.sendRedirect(getServletContext().getContextPath()+"/advisor/customers");
        }
        else
        {
            Customer customer = customerService.findCustomerById(Long.parseLong(customerId));
            if (customer == null)
            {
               request.getSession().setAttribute("flashError", "No customer with id " + customerId + " found.");
               response.sendRedirect(getServletContext().getContextPath()+"/advisor/customers"); 
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
                
                accountService.saveAccount(account);

                request.getSession().setAttribute("flashSuccess", "New account successfull created !");
                response.sendRedirect(getServletContext().getContextPath()+"/customer?id="+customer.getId());
            }
        }
    }
}
