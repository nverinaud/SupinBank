/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.service;

import com.supinfo.supinbank.dao.AccountDao;
import com.supinfo.supinbank.entity.Account;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author nico
 */
@Stateless
public class AccountService 
{
    @EJB
    private AccountDao accountDao;
    
    public void saveAccount(Account a)
    {
        if (a.getId() == null)
            accountDao.createAccount(a);
        else
            accountDao.update(a);
    }
    
    public Account find(String id)
    {
        return accountDao.find(id);
    }
}
