/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.service;

import com.supinfo.supinbank.dao.UserDao;
import com.supinfo.supinbank.entity.User;
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
    
    public User authenticateUserWithEmailPassword(String email, String password)
    {
        return userDao.authenticateUserWithEmailPassword(email, password);
    }
    
    public String encryptedPassword(String clearPassword)
    {
        return userDao.encryptPassword(clearPassword);
    }
    
    public void saveUser(User u)
    {
        userDao.addUser(u);
    }
}
