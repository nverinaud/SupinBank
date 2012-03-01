/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.servlet;

import com.supinfo.supinbank.entity.Customer;
import com.supinfo.supinbank.service.CustomerService;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ListAccountsOfCustomerServlet", urlPatterns = {"/customer"})
public class ListAccountsOfCustomerServlet extends HttpServlet 
{
    @EJB
    private CustomerService customerService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String id = request.getParameter("id");
        if (id == null || id.isEmpty())
        {
            doRedirect(request, response);
        }
        else
        {
            Customer customer = customerService.findCustomerById(Long.parseLong(id));
            
            if (customer == null || !isAuthorizedToSeeCustomer(request, response, customer))
            {
                doRedirect(request, response);
            }
            else
            {
                request.setAttribute("customer", customer);
                request.setAttribute("accounts", customer.getAccounts());
                
                System.out.println(customer.getAccounts().toString());
                
                request.getRequestDispatcher("/customer.jsp").forward(request, response);
            }
        }
    }
    
    protected void doRedirect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        boolean isNull = request.getSession().getAttribute("isAdvisor") == null;
        if (!isNull)
        {
            boolean isAdvisor = (Boolean)request.getSession().getAttribute("isAdvisor");
        
            if (isAdvisor)
                response.sendRedirect(getServletContext().getContextPath() + "/advisor/customers");
            else
                response.sendRedirect(getServletContext().getContextPath() + "/myaccounts");
        }
        else
        {
            response.sendRedirect(getServletContext().getContextPath());
        }
    }
    
    
    protected boolean isAuthorizedToSeeCustomer(HttpServletRequest request, HttpServletResponse response, Customer customer)
            throws ServletException, IOException
    {
        if (customer == null)
            return false;
        
        boolean isAdvisor = (Boolean)request.getSession().getAttribute("isAdvisor");
        if (isAdvisor)
            return true;
        
        String loggedInUserEmail = (String) request.getSession().getAttribute("userEmail");
        return customer.getEmail().equals(loggedInUserEmail);
    }
}
