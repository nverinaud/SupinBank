/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.dao.jpa;

import com.supinfo.supinbank.dao.OperationDao;
import com.supinfo.supinbank.entity.Operation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nico
 */
@Stateless
public class JpaOperationDao implements OperationDao
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Operation find(Long id) 
    {
        return em.find(Operation.class, id);
    }

    @Override
    public void create(Operation o) 
    {
        em.persist(o);
    }
}
