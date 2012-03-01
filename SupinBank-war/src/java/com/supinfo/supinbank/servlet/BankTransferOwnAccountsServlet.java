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
@WebServlet(name="BankTransferOwnAccountsServlet", urlPatterns={"/owner/transfer"})
public class BankTransferOwnAccountsServlet extends HttpServlet 
{
    @EJB
    private CustomerService customerService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        String userEmail = (String) request.getSession().getAttribute("userEmail");
        Customer customer = customerService.findCustomerByEmail(userEmail);
        if (customer == null)
        {
            request.getSession().setAttribute("flashError", "Invalid email. You're not a customer. Log In again.");
            request.getSession().removeAttribute("isAdvisor");
            request.getSession().removeAttribute("userEmail");
            response.sendRedirect(getServletContext().getContextPath()+"/signin");
        }
        else
        {
            request.setAttribute("accounts", customer.getAccounts());
            request.getRequestDispatcher("/owner/transfer.jsp").forward(request, response);
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        request.getSession().setAttribute("flashSuccess", "Your transfer has been made !");
        response.sendRedirect(getServletContext().getContextPath()+"/myaccounts");
    }
}
