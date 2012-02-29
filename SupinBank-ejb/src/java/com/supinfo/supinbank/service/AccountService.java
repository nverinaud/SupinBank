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
        accountDao.createAccount(a);
    }
}
