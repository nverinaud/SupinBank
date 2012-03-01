/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.service;

import com.supinfo.supinbank.entity.Operation;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

/**
 *
 * @author nico
 */
@Stateless
public class CentralBankService 
{
    @Resource(mappedName="jms/supinbankConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(mappedName="jms/supinbankCentralBankQueue")
    private Destination destination;
    
    
    public void performTransfer(Operation operation) 
    {    
        Connection connection = null;
        
        try 
        {
            connection = connectionFactory.createConnection();
            
            Session session = connection.createSession(false, 
                    Session.AUTO_ACKNOWLEDGE);
            
            MessageProducer producer = session.createProducer(destination);
            
            TextMessage message = session.createTextMessage("Kikoo");
            
            producer.send(message);
            
        } 
        catch (JMSException ex) 
        {
            System.out.println(ex.getMessage());
        } 
        finally 
        {
            if(null != connection) 
            {
                try 
                {
                    connection.close();
                } 
                catch (JMSException ex) 
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
        
    }
}
