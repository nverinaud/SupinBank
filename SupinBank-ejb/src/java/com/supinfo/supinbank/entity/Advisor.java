/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author nico
 */
@Entity
@DiscriminatorValue("Advisor")
public class Advisor extends User
{   
}
