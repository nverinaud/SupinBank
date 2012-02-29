/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.service;

import com.supinfo.supinbank.dao.CustomerDao;
import com.supinfo.supinbank.dao.UserDao;
import com.supinfo.supinbank.entity.Customer;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author nico
 */
@Stateless
public class CustomerService
{
    @EJB
    private UserDao userDao;
    
    @EJB
    private CustomerDao customerDao;
    
    @EJB
    private MailService mailService;
    
    public List<Customer> findAllCustomers()
    {
        return customerDao.findAllCustomers();
    }
    
    public Customer findCustomerById(Long id)
    {
        return customerDao.findCustomerById(id);
    }
    
    public void saveCustomer(Customer c)
    {
        if (c.getPassword() == null || c.getPassword().isEmpty())
        {
            String pass = RandomStringUtils.randomAlphanumeric(12);
            String hash = userDao.encryptPassword(pass);
            c.setPassword(hash);
            mailService.sendEmail(c, pass);
        }
        customerDao.addCustomer(c);
    }
}
