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

    @Override
    public Account find(String id) 
    {
        return em.find(Account.class, Long.parseLong(id));
    }

    @Override
    public void update(Account a) 
    {
        em.createQuery("UPDATE Account a SET a.balance = :balance, a.name = :name WHERE a.id = :id ")
                .setParameter("balance", a.getBalance())
                .setParameter("name", a.getName())
                .setParameter("id", a.getId())
                .executeUpdate();
    }
}
