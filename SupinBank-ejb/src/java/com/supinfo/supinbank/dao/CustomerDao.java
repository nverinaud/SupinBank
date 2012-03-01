/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.dao;

import com.supinfo.supinbank.entity.Customer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nico
 */
@Local
public interface CustomerDao 
{
    List<Customer> findAllCustomers();
    Customer findCustomerById(Long id);
    Customer findCustomerByEmail(String email);
    void addCustomer(Customer c);
}
