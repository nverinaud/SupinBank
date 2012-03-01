/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.servlet;

import com.supinfo.supinbank.entity.Customer;
import com.supinfo.supinbank.service.CustomerService;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "ListCustomersServlet", urlPatterns = {"/advisor/customers"})
public class ListCustomersServlet extends HttpServlet 
{   
    @EJB
    private CustomerService customerService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        List<Customer> customers = customerService.findAllCustomers();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/advisor/customers.jsp").forward(request, response);
    }
}
