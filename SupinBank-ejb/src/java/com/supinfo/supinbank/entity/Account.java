/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supinbank.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author nico
 */
@Entity
@Table(name="accounts")
public class Account implements Serializable 
{
    public enum InterestsPlan {
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
    private InterestsPlan interestsPlan;
    private BigDecimal balance;
    private String establishementCode;
    private String branchCode;
    private String accountNumber;
    
    @Column(name="account_key")
    private String key;
    
    @ManyToOne
    @JoinColumn
    private User owner;
    
    @OneToMany(mappedBy="sourceAccount")
    private List<Operation> sourceOperations;
    
    @OneToMany(mappedBy="destinationAccount")
    private List<Operation> destinationOperations;
    
    public Account()
    {
        interestsPlan = InterestsPlan.CURRENT_ACCOUNT;
        name = "Main Account";
        balance = new BigDecimal(0);
    }
    
    public List<String> getInterestsPlansDescriptions()
    {
        List<String> interestPlansDescriptions = new ArrayList<String>();
        interestPlansDescriptions.add(stringFromInterestsPlan(InterestsPlan.CURRENT_ACCOUNT));
        interestPlansDescriptions.add(stringFromInterestsPlan(InterestsPlan.SAVINGS_ACCOUNT));
        interestPlansDescriptions.add(stringFromInterestsPlan(InterestsPlan.LIFE_INSURANCE));
        interestPlansDescriptions.add(stringFromInterestsPlan(InterestsPlan.FIRST_HOME_SAVER_ACCOUNT));
        return interestPlansDescriptions;
    }
    
    public static InterestsPlan interestsPlanFromString(String ipAsString)
    {
        if (ipAsString.equals("Current Account"))
            return InterestsPlan.CURRENT_ACCOUNT;
        else if (ipAsString.equals("Savings Account"))
            return InterestsPlan.SAVINGS_ACCOUNT;
        else if (ipAsString.equals("Life Insurance"))
            return InterestsPlan.LIFE_INSURANCE;
        else if (ipAsString.equals("First Home Saver Account"))
            return InterestsPlan.FIRST_HOME_SAVER_ACCOUNT;
            
        return InterestsPlan.CURRENT_ACCOUNT;
    }
    
    public static String stringFromInterestsPlan(InterestsPlan ip)
    {
        switch(ip)
        {
            case CURRENT_ACCOUNT:
                return "Current Account";
            
            case SAVINGS_ACCOUNT:
                return "Savings Account";
                    
            case LIFE_INSURANCE:
                return "Life Insurance";
                        
            case FIRST_HOME_SAVER_ACCOUNT:
                return "First Home Saver Account";
                
            default:
                return "Current Account";
        }
    }
    
    public List<Operation> getOperations() 
    {
        List<Operation> operations = getDestinationOperations();
        operations.addAll(getSourceOperations());
        Collections.sort(operations);
        return operations;
    }

    public List<Operation> getDestinationOperations() {
        return destinationOperations;
    }

    public void setDestinationOperations(List<Operation> destinationOperations) {
        this.destinationOperations = destinationOperations;
    }

    public List<Operation> getSourceOperations() {
        return sourceOperations;
    }

    public void setSourceOperations(List<Operation> sourceOperations) {
        this.sourceOperations = sourceOperations;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

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

    public InterestsPlan getInterestsPlan() {
        return interestsPlan;
    }

    public void setInterestsPlan(InterestsPlan interestPlan) {
        this.interestsPlan = interestPlan;
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
