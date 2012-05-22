/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.web.api;

import com.supinfo.supinbank.entity.Account;
import com.supinfo.supinbank.entity.Customer;
import com.supinfo.supinbank.web.api.helper.ServiceLookupHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author nico
 */
@Path("/accounts")
public class AccountsAPI {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{user_id}")
  public List<Account> getAccountsForUserId(@PathParam("user_id") Long userId) {
    try {
      
      Customer c = ServiceLookupHelper.GetCustomerService().findCustomerById(userId);
      List<Account> accounts = new ArrayList<Account>(c.getAccounts());
      return accounts;
      
    } catch (NamingException ex) {
      
      Logger.getLogger(AccountsAPI.class.getName()).log(Level.SEVERE, null, ex);
      return null;
      
    }
  }
}
