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
@WebServlet(name = "AddAccountServlet", urlPatterns = {"/advisor/account/new"})
public class AddAccountServlet extends HttpServlet 
{
    @EJB
    private CustomerService customerService;
    
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
            request.setAttribute("account", new Account());
            
            Customer c = customerService.findCustomerById(Long.parseLong(customerId));
            if (c == null)
            {
               request.getSession().setAttribute("flashError", "No customer with id " + customerId + " found.");
               response.sendRedirect(getServletContext().getContextPath()+"/advisor/customers"); 
            }
            else
            {
                request.setAttribute("customer", c);
                request.getRequestDispatcher("/advisor/account/new.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
    }
}
