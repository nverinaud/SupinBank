/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.supinfo.supinbank.servlet;

import com.supinfo.supinbank.entity.Account;
import com.supinfo.supinbank.entity.Customer;
import com.supinfo.supinbank.entity.Operation;
import com.supinfo.supinbank.service.AccountService;
import com.supinfo.supinbank.service.CustomerService;
import com.supinfo.supinbank.service.OperationService;
import java.io.IOException;
import java.math.BigDecimal;
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
@WebServlet(name="BankTransfersServlet", urlPatterns={"/owner/transfer"})
public class BankTransfersServlet extends HttpServlet 
{
    @EJB
    private CustomerService customerService;
    
    @EJB
    private AccountService accountService;
    
    @EJB
    private OperationService operationService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        Customer customer = getCurrentCustomer(request, response);
        if (customer == null)
        {
            doRedirectInvalidCustomer(request, response);
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
        Customer customer = getCurrentCustomer(request, response);
        if (customer == null)
        {
            doRedirectInvalidCustomer(request, response);
        }
        else
        {
            String transferType = request.getParameter("transferType");
            String sourceAccountId = request.getParameter("sourceAccountId");
            Account sourceAccount = accountService.find(sourceAccountId);
            
            if (!sourceAccount.getOwner().equals(customer))
            {
                doRedirectInvalidCustomer(request, response);
            }
            else
            {
                BigDecimal amount = new BigDecimal(request.getParameter("amount"));
                String wording = request.getParameter("wording");
                
                if ("internal".equals(transferType))
                {
                    String destinationAccountId = request.getParameter("destinationAccountId");
                    Account destinationAccount = accountService.find(destinationAccountId);
                    
                    doBalancingAndSave(sourceAccount, destinationAccount, amount, wording);
                    
                    request.getSession().setAttribute("flashSuccess", "Your transfer has been made !");
                    response.sendRedirect(getServletContext().getContextPath()+"/myaccounts");
                    
                }
                else if ("supinbank".equals(transferType))
                {
                    try
                    {
                        Account destinationAccount = destinationAccountFromBBAN(request, response, true);
                        
                        doBalancingAndSave(sourceAccount, destinationAccount, amount, wording);
                    
                        request.getSession().setAttribute("flashSuccess", "Your transfer has been made !");
                        response.sendRedirect(getServletContext().getContextPath()+"/myaccounts");
                    }
                    catch (Exception ex)
                    {
                        request.getSession().setAttribute("flashError", "BBAN account error : " + ex.getMessage());
                        response.sendRedirect(getServletContext().getContextPath()+"/owner/transfer");
                    }
                }
                else if ("external".equals(transferType))
                {
                    request.getSession().setAttribute("flashError", "Unsupported Operation.");
                    response.sendRedirect(getServletContext().getContextPath()+"/owner/transfer");
                }
            }
        }
    }
    
    
    private Customer getCurrentCustomer(HttpServletRequest request, HttpServletResponse response)
    {
        String userEmail = (String) request.getSession().getAttribute("userEmail");
        return customerService.findCustomerByEmail(userEmail);
    }
    
    
    private void doRedirectInvalidCustomer(HttpServletRequest request, HttpServletResponse response) 
            throws IOException
    {
        request.getSession().setAttribute("flashError", "Invalid email. You're not a customer. Log In again.");
        request.getSession().removeAttribute("isAdvisor");
        request.getSession().removeAttribute("userEmail");
        response.sendRedirect(getServletContext().getContextPath()+"/signin");
    }
    
    
    private Account destinationAccountFromBBAN(HttpServletRequest request, HttpServletResponse response, boolean isSupinbank) 
            throws Exception
    {
        Account account = null;        
        String accountNumber = request.getParameter("accountNumber");
        
        if (accountNumber == null || accountNumber.isEmpty() || accountNumber.length()<11)
            throw new Exception("Invalid parameters.");
        
        if (isSupinbank)
        {
              account = accountService.find(accountNumber);
        }
        else
        {
            String establishmentCode = request.getParameter("establishmentCode");
            String branchCode = request.getParameter("branchCode");
            String key = request.getParameter("key");
            
            if (establishmentCode == null || establishmentCode.isEmpty() || establishmentCode.length()<5 ||
                    branchCode == null || branchCode.isEmpty() || branchCode.length()<5 ||
                    key == null || key.isEmpty() || key.length()<2)
                throw new Exception("Invalid parameters.");
            
            int computedKey = computeKey(establishmentCode, branchCode, accountNumber);
            int providedKey = Integer.parseInt(key);
            
            if (providedKey == computedKey)
            {
                account = new Account();
                account.setEstablishementCode(establishmentCode);
                account.setBranchCode(branchCode);
                account.setAccountNumber(accountNumber);
                account.setKey(key);
                account.setName("External Account");
            }
            else
            {
                throw new Exception("Key is invalid.");
            }
        }
        
        return account;
    }

    private void doBalancingAndSave(Account sourceAccount, Account destinationAccount, BigDecimal amount, String wording) 
    {
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));

        Operation credit = new Operation();
        credit.setAmount(amount.abs());
        credit.setDescription(wording);
        credit.setIsExternal(false);
        credit.setSourceAccount(sourceAccount);
        credit.setDestinationAccount(destinationAccount);

        Operation withdrawal = new Operation(credit, true); // negate

        operationService.save(credit);
        operationService.save(withdrawal);
        accountService.saveAccount(sourceAccount);
        accountService.saveAccount(destinationAccount);
    }

    private int computeKey(String establishmentCode, String branchCode, String accountNumber) 
    {
        String codes = establishmentCode+branchCode+accountNumber;
        int code = Integer.parseInt(codes);
        
        return 97 - (code * 100 % 97);
    }
}
