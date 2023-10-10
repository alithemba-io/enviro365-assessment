package com.enviro.assessment.grad001.alithembamakongwana.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enviro.assessment.grad001.alithembamakongwana.Entity.Investor;

@Repository
public interface  InvestorRepository extends  JpaRepository<Investor, Long>{
   //Query to retrieve investors by first name
   
    //Query to retrieve investors by first name
    List<Investor> findByFirstName(String firstName);

    //Query to retrieve investors by last name 
    List<Investor> findByLastName(String lastName);  
}
