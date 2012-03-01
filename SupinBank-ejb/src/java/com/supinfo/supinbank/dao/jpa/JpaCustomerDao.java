/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.dao.jpa;

import com.supinfo.supinbank.dao.CustomerDao;
import com.supinfo.supinbank.entity.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nico
 */
@Stateless
public class JpaCustomerDao implements CustomerDao 
{
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<Customer> findAllCustomers() 
    {
        return em.createQuery("SELECT c FROM Customer c").getResultList();
    }

    @Override
    public Customer findCustomerById(Long id) 
    {
        return em.find(Customer.class, id);
    }

    @Override
    public void addCustomer(Customer c) 
    {
        em.persist(c);
    }

    @Override
    public Customer findCustomerByEmail(String email) 
    {
        return (Customer) em.createQuery("SELECT c FROM Customer c WHERE c.email = :email").setParameter("email", email).getSingleResult();
    }
    
}
