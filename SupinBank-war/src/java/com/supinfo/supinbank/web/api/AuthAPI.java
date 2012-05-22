/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.web.api;

import com.supinfo.supinbank.entity.User;
import com.supinfo.supinbank.service.UserService;
import com.supinfo.supinbank.web.api.helper.ServiceLookupHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author nico
 */
@Path("/auth")
public class AuthAPI {

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public String authenticate(@FormParam("email") String email, @FormParam("pass") String password) {
    try {
      UserService userService = ServiceLookupHelper.GetUserService();
      User u = userService.authenticateUserWithEmailPassword(email, password);
      if (u == null)
        return "Error: provided credentials are invalid !";
      else
        return u.getId().toString();
    } catch (NamingException ex) {
      Logger.getLogger(AuthAPI.class.getName()).log(Level.SEVERE, null, ex);
      return "Error: " + ex.toString();
    }
  }
}
