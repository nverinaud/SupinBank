/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author nico
 */
@Entity
public class Account implements Serializable 
{
    public enum InterestPlan {
        CURRENT_ACCOUNT, 
        SAVINGS_ACCOUNT, 
        LIFE_INSURANCE, 
        FIRST_HOME_SAVER_ACCOUNT
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private InterestPlan interestPlan;
    private BigDecimal balance;
    private String establishementCode;
    private String branchCode;
    private String accountNumber;
    
    @Column(name="account_key")
    private String key;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getEstablishementCode() {
        return establishementCode;
    }

    public void setEstablishementCode(String establishementCode) {
        this.establishementCode = establishementCode;
    }

    public InterestPlan getInterestPlan() {
        return interestPlan;
    }

    public void setInterestPlan(InterestPlan interestPlan) {
        this.interestPlan = interestPlan;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.supinfo.supinbank.entity.Account[ id=" + id + " ]";
    }
    
}
