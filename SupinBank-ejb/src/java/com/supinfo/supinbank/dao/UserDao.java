/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.dao;

import com.supinfo.supinbank.entity.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nico
 */
@Local
public interface UserDao 
{
    List<User> getAllCustomers();
    
    User authenticateUserWithEmailPassword(String email, String clearPassword);
    
    void addUser(User u);
}
