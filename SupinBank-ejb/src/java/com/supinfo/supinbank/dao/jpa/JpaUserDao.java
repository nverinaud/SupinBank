/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.dao.jpa;

import com.supinfo.supinbank.dao.UserDao;
import com.supinfo.supinbank.entity.User;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public List<User> getAllCustomers() 
    {
        return em.createQuery("SELECT c FROM Customer c").getResultList();
    }

    @Override
    public User authenticateUserWithEmailPassword(String email, String clearPassword) 
    {
        if (email == null || email.isEmpty())
          return null;
        
        User user = (User) em.createQuery("SELECT u FROM User u WHERE u.email = :email").setParameter("email", email).getSingleResult();

        if (clearPassword == null || clearPassword.isEmpty())
            clearPassword = "";

        String hash = DigestUtils.shaHex(clearPassword);
        if (!user.getPassword().equals(hash))
            user = null;
        
        return user;
    }

    @Override
    public void addUser(User u) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
