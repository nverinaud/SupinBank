/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.dao.jpa;

import com.supinfo.supinbank.dao.UserDao;
import com.supinfo.supinbank.entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nico
 */
@Stateless
public class JpaUserDao implements UserDao
{
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<User> getAllCustomers() 
    {
        return em.createQuery("SELECT c FROM Customer c").getResultList();
    }

    @Override
    public User authenticateUserWithIdPassword(Long userId, String clearPassword) 
    {
        return null;
    }

    @Override
    public void addUser(User u) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
