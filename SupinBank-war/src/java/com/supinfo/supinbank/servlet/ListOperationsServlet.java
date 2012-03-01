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
@WebServlet(name="ListOperationsServlet", urlPatterns={"/operations"})
public class ListOperationsServlet extends HttpServlet 
{
    @EJB
    private AccountService accountService;
    
    @EJB
    private CustomerService customerService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        String accountId = request.getParameter("accountId");
        if (accountId == null || accountId.isEmpty())
        {
            request.getSession().setAttribute("flashError", "You must specify an account id to list operations.");
            response.sendRedirect(getServletContext().getContextPath());
        }
        else
        {
            Account account = accountService.findAndFetchAll(accountId);
            boolean isAdvisor = (Boolean) request.getSession().getAttribute("isAdvisor");
            boolean isAuthorized = isAdvisor;
            
            if (!isAdvisor)
            {
                String loggedInUserEmail = (String) request.getSession().getAttribute("userEmail");
                Customer customer = customerService.findCustomerByEmail(loggedInUserEmail);
                isAuthorized = account.getOwner().equals(customer);
            }
            
            if (isAuthorized)
            {
                request.setAttribute("selectedAccountId", account.getId());
                request.setAttribute("owner", account.getOwner());
                request.setAttribute("accounts", account.getOwner().getAccounts());
                request.setAttribute("operations", account.getOperations());
                request.getRequestDispatcher("/operations.jsp").forward(request, response);
            }
            else
            {
                request.getSession().setAttribute("flashError", "Access denied.");
                response.sendRedirect(getServletContext().getContextPath());
            }
        }
    }
}
