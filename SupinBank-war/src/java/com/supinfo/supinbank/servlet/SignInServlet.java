/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.servlet;

import com.supinfo.supinbank.entity.Advisor;
import com.supinfo.supinbank.entity.Customer;
import com.supinfo.supinbank.entity.User;
import com.supinfo.supinbank.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "SignInServlet", urlPatterns = {"/signin"})
public class SignInServlet extends HttpServlet 
{
    @EJB
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        User user = userService.authenticateUserWithEmailPassword(email, password);
        
        if (user != null)
        {
            boolean isAdvisor = user instanceof Advisor;
            request.getSession().setAttribute("isAdvisor", isAdvisor);
            request.getSession().setAttribute("userEmail", user.getEmail());
            
            if (isAdvisor) // Redirect to list of customers
                response.sendRedirect(getServletContext().getContextPath() + "/advisor/customers");
            else // Redirect to list of own account
                response.sendRedirect(getServletContext().getContextPath()/* + "/accounts"*/);
        }
        else
        {
            request.getSession().setAttribute("flashError", "Username and password mismatch !");
            doGet(request, response);
        }
    }
}
