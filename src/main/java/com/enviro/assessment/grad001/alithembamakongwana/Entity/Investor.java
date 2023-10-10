package com.enviro.assessment.grad001.alithembamakongwana.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Investor {    
    @Id
     
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String firstName;
    private String lastName;

    //Default constructor
    public Investor(){

    }

    //Constructor with parameters
    public Investor(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return 0;
    }    
}
