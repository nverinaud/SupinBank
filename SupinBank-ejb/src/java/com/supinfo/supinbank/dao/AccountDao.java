/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.dao;

import com.supinfo.supinbank.entity.Account;
import javax.ejb.Local;

/**
 *
 * @author nico
 */
@Local
public interface AccountDao 
{
    void createAccount(Account a);
    Account find(Long id);
    void update(Account a);
}
