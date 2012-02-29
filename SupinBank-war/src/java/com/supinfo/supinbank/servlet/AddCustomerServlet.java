/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.servlet;

import com.supinfo.supinbank.entity.Customer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author nico
 */
@WebServlet(name = "AddCustomerServlet", urlPatterns = {"/advisor/customer/new"})
public class AddCustomerServlet extends HttpServlet 
{  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.getSession().removeAttribute("customer"); // Clear if customer were in session (request aborted)
        request.getRequestDispatcher("/advisor/customer/new.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        Customer customer = new Customer();
        customer.setEmail(request.getParameter("email"));
        customer.setFirstname(request.getParameter("firstname"));
        customer.setLastname(request.getParameter("lastname"));
        customer.setCity(request.getParameter("city"));
        customer.setAddress(request.getParameter("address"));
        customer.setZipCode(request.getParameter("zipCode"));
        customer.setPhone(request.getParameter("phone"));
        
        request.setAttribute("customer", customer);
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(customer);
        
        if (!constraintViolations.isEmpty())
        {
            HashMap<String, String> errors = new HashMap<String, String>();
            
            for (ConstraintViolation<Customer> constraintViolation : constraintViolations) 
                errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            
            request.setAttribute("errors", errors);
            request.setAttribute("flashError", "Unable to create a new Customer due to errors (see below).");
            doGet(request, response);
        }
        else
        {
            request.getSession().setAttribute("customer", customer);
            request.setAttribute("flashInfo", "You must provide " + customer.getFirstname() + " " + customer.getLastname() + " his first account.");
            response.sendRedirect(getServletContext().getContextPath()+"/advisor/customer/new_first_account");
        }
    }
}
