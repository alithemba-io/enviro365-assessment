package com.enviro.assessment.grad001.alithembamakongwana.Entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String name;

    public Product() {
    }
    public Product(Long id, String type, String name) {
        this.id = id;
        this.type = type; //Retirement or Savings
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Object getInvestor() {
        return null;
    }
    public BigDecimal getCurrentBalance() {
        return null;
    }
    public void setCurrentBalance(BigDecimal newBalance) {
    }    
}
