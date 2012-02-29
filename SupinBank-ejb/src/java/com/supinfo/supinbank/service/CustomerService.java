/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.service;

import com.supinfo.supinbank.dao.CustomerDao;
import com.supinfo.supinbank.entity.Customer;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author nico
 */
@Stateless
public class CustomerService
{
    @EJB
    private CustomerDao customerDao;
    
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
        customerDao.addCustomer(c);
    }
}
