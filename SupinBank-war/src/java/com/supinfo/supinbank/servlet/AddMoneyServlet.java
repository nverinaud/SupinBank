/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.servlet;

import com.supinfo.supinbank.entity.Account;
import com.supinfo.supinbank.entity.Operation;
import com.supinfo.supinbank.service.AccountService;
import com.supinfo.supinbank.service.OperationService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
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
@WebServlet(name = "AddMoneyServlet", urlPatterns = {"/advisor/account/add_money"})
public class AddMoneyServlet extends HttpServlet 
{
    @EJB
    private AccountService accountService;
    
    @EJB
    private OperationService operationService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String accountId = request.getParameter("accountId");
        if (accountId == null || accountId.isEmpty())
        {
            request.getSession().setAttribute("flashError", "You have to provide an account id to add money to.");
            response.sendRedirect(getServletContext().getContextPath()+"/advisor/customers");
        }
        else
        {
            try
            {
                Account account = accountService.find(accountId);
                if (account == null)
                {
                    request.getSession().setAttribute("flashError", "No account with id "+accountId+" found.");
                    response.sendRedirect(getServletContext().getContextPath()+"/advisor/customers");
                }
                else
                {
                    request.setAttribute("account", account);
                    request.getRequestDispatcher("/advisor/account/add_money.jsp").forward(request, response);
                }
            }
            catch (Exception ex)
            {
                request.getSession().setAttribute("flashError", "Whoops ! Unexpected error occured.");
                response.sendRedirect(getServletContext().getContextPath()+"/advisor/customers");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String accountId = request.getParameter("accountId");
        String amount = request.getParameter("amount");
        String wording = request.getParameter("wording");
        if (accountId == null || accountId.isEmpty() || amount == null || amount.isEmpty() || wording == null || wording.isEmpty())
        {
            String errorMsg = new String();
            if (accountId == null || accountId.isEmpty())
                errorMsg += "You have to provide an account id to add money to. ";
            
            if (amount == null || amount.isEmpty())
                errorMsg += "You have to provide an amount to add. ";
            
            if (wording == null || wording.isEmpty())
                errorMsg += "You must specify a reason.";
            
            request.getSession().setAttribute("flashError", errorMsg);
            response.sendRedirect(getServletContext().getContextPath()+"/advisor/customers");
        }
        else
        {
            try
            {
                Account account = accountService.find(accountId);
                if (account == null)
                {
                    request.getSession().setAttribute("flashError", "No account with id "+accountId+" found.");
                    response.sendRedirect(getServletContext().getContextPath()+"/advisor/customers");
                }
                else
                {
                    try 
                    {
                        BigDecimal amnt = new BigDecimal(Double.parseDouble(amount));                        
                        BigDecimal current = account.getBalance();
                        account.setBalance(current.add(amnt));
                        accountService.saveAccount(account);
                        
                        Operation operation = new Operation();
                        operation.setAmount(amnt);
                        operation.setDescription(wording);
                        operation.setDestinationAccount(account);
                        operation.setSourceAccount(account);
                        operationService.save(operation);
                        
                        request.getSession().setAttribute("flashSuccess", "Account has been cashed $$$ !!");
                        response.sendRedirect(getServletContext().getContextPath()+"/customer?id="+account.getOwner().getId());
                    }
                    catch (NumberFormatException ex)
                    {
                        request.getSession().setAttribute("flashError", "Malformed amount " + amount);
                        response.sendRedirect(getServletContext().getContextPath()+"/advisor/account/add_money?accountId="+accountId);
                    }
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                request.getSession().setAttribute("flashError", "Whoops ! Unexpected error occured.");
                response.sendRedirect(getServletContext().getContextPath()+"/advisor/customers");
            }
        }
    }
}
