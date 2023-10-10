package com.enviro.assessment.grad001.alithembamakongwana.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WithdrawalNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;
    private BigDecimal withdrawalAmount;
    private Date withdrawalDate;

    //Constructors
    public WithdrawalNotice() {
    }
    public WithdrawalNotice(Long productId, BigDecimal withdrawalAmount, Date withdrawalDate) {
        this.productId = productId;
        this.withdrawalAmount = withdrawalAmount;
        this.withdrawalDate = withdrawalDate;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }
    public void setWithdrawalAmount(BigDecimal withdrawalAmount2) {
        this.withdrawalAmount = withdrawalAmount2;
    }
    public Date getWithdrawalDate() {
        return withdrawalDate;
    }
    public void setWithdrawalDate(Date withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }
    
}
