/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.web.api.helper;

import com.supinfo.supinbank.service.AccountService;
import com.supinfo.supinbank.service.CustomerService;
import com.supinfo.supinbank.service.UserService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author nico
 */
public class ServiceLookupHelper {

  private static Object GetService(String jndi)
          throws NamingException {
    Context ctx = new InitialContext();
    Object service = ctx.lookup(jndi);
    return service;
  }

  public static UserService GetUserService()
          throws NamingException {
    return (UserService) GetService("java:global/SupinBank/SupinBank-ejb/UserService");
  }

  public static AccountService GetAccountService() 
          throws NamingException {
    return (AccountService) GetService("java:global/SupinBank/SupinBank-ejb/AccountService");
  }
  
  public static CustomerService GetCustomerService()
          throws NamingException {
    return (CustomerService) GetService("java:global/SupinBank/SupinBank-ejb/CustomerService");
  }
}
