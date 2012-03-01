/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.supinfo.supinbank.servlet;

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
@WebServlet(name="OwnAccountsServlet", urlPatterns={"/myaccounts"})
public class OwnAccountsServlet extends HttpServlet 
{
    @EJB
    private CustomerService customerService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try
        {
            String loggedInUserEmail = (String) request.getSession().getAttribute("userEmail");
            boolean isAdvisor = (Boolean) request.getSession().getAttribute("isAdvisor");
            if (isAdvisor)
            {
                response.sendRedirect(getServletContext().getContextPath()+"/advisor/customers");
            }
            else
            {
                Customer customer = customerService.findCustomerByEmail(loggedInUserEmail);
                request.setAttribute("customer", customer);
                request.setAttribute("accounts", customer.getAccounts());
                request.getRequestDispatcher("/myaccounts.jsp").forward(request, response);
            }
        }
        catch (NullPointerException npEx)
        {
            request.getSession().setAttribute("flashError", "You must be login to access this area.");
            response.sendRedirect(getServletContext().getContextPath()+"/signin");
        }
        catch (Exception ex)
        {
            request.getSession().setAttribute("flashError", "Unexpected error occured.");
            response.sendRedirect(getServletContext().getContextPath());
        }
    }
}
