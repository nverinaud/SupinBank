/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.service;

import com.supinfo.supinbank.dao.OperationDao;
import com.supinfo.supinbank.entity.Operation;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author nico
 */
@Stateless
public class OperationService 
{
    @EJB
    private OperationDao operationDao;
    
    public Operation find(String id)
    {
        return operationDao.find(Long.parseLong(id));
    }
    
    public void create(Operation o)
    {
        operationDao.create(o);
    }
}
