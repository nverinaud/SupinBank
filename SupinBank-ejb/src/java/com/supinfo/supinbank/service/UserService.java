/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.service;

import com.supinfo.supinbank.dao.UserDao;
import com.supinfo.supinbank.entity.User;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author nico
 */
@Stateless
public class UserService 
{
    @EJB
    private UserDao userDao;
    
    public List<User> getAllCustomers()
    {
        return userDao.getAllCustomers();
    }
    
    
    public User authenticateUserWithEmailPassword(String email, String password)
    {
        return userDao.authenticateUserWithEmailPassword(email, password);
    }
}
