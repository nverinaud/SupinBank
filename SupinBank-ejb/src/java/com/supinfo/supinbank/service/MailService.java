/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.service;

import com.supinfo.supinbank.entity.Customer;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author nico
 */
@Stateless
public class MailService 
{
    @Asynchronous
    public Future<Boolean> sendEmail(Customer c, String clearPassword)
    {
        Properties props = new Properties();
        props.put("mail.smtp.host", "mail.supinbank.com");
        props.put("mail.from", "advisor@supinbank.com");
        Session session = Session.getInstance(props, null);

        StringBuilder body = new StringBuilder();
        
        try 
        {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom();
            msg.setRecipients(Message.RecipientType.TO, c.getEmail());
            msg.setSubject("Welcome to SupinBank !");
            msg.setSentDate(new Date());
            
            body.append("Welcome ")
                    .append(c.getFirstname())
                    .append(" ")
                    .append(c.getLastname())
                    .append(" !\n\n")
                    .append("Your account has been created.")
                    .append("\n\n")
                    .append("You will need your password to access to your SupinBank account.")
                    .append("\n")
                    .append("Here it is : ")
                    .append(clearPassword)
                    .append("\n")
                    .append("Thank You !")
                    .append("\n\n")
                    .append("The SupinBank Advisor !");
            
            msg.setText(body.toString());
            Transport.send(msg);
        } 
        catch (MessagingException mex) 
        {
            System.out.println("send failed, exception: " + mex);
            System.out.println("\nEmail that failed: \n" + body.toString());
        }
        
        return new AsyncResult<Boolean>(Boolean.TRUE); 
    }
}
