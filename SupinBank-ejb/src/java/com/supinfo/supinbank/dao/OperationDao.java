/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.dao;

import com.supinfo.supinbank.entity.Operation;
import javax.ejb.Local;

/**
 *
 * @author nico
 */
@Local
public interface OperationDao 
{
    Operation find(Long id);
    void create(Operation o);
}
