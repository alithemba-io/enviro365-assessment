package com.enviro.assessment.grad001.alithembamakongwana.Entity;

import java.math.BigDecimal;
import java.util.Date;

public class WithdrawalNoticeDTO {
    private Long productId;
    private BigDecimal withdrawalAmount;
    private Date withdrawalDate;
    
    //Getters and setters

    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }
    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }
    public Date getWithdrawalDate() {
        return withdrawalDate;
    }
    public void setWithdrawalDate(Date withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }
    public Long getInvestorId() {
        return null;
    }    
}
