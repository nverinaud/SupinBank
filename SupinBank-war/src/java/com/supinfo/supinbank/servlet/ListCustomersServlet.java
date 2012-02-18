/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.servlet;

import com.supinfo.supinbank.entity.User;
import com.supinfo.supinbank.service.UserService;
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
    private UserService userService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        List<User> customers = userService.getAllCustomers();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/listCustomers.jsp").forward(request, response);
    }
}
