/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.dao.jpa;

import com.supinfo.supinbank.dao.AccountDao;
import com.supinfo.supinbank.entity.Account;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nico
 */
@Stateless
public class JpaAccountDao implements AccountDao
{
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void createAccount(Account a) 
    {
        em.persist(a);
    }
}
