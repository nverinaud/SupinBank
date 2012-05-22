/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.dao.jpa;

import com.supinfo.supinbank.dao.UserDao;
import com.supinfo.supinbank.entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.codec.digest.DigestUtils;

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
    public User authenticateUserWithEmailPassword(String email, String clearPassword) 
    {
        try
        {
            if (email == null || email.isEmpty())
              return null;

            User user = (User) em.createQuery("SELECT u FROM User u WHERE u.email = :email").setParameter("email", email).getSingleResult();
                        
            if (clearPassword == null || clearPassword.isEmpty())
                clearPassword = "";
                        
            String hash = encryptPassword(clearPassword);
            
            if (!user.getPassword().equals(hash))
                user = null;
            
            return user;
        } 
        catch (Exception ex)
        {
            return null;
        }
    }

    @Override
    public void addUser(User u) 
    {
        em.persist(u);
    }

    @Override
    public String encryptPassword(String clearPassword) 
    {
        return DigestUtils.shaHex(clearPassword);
    }
    
}
