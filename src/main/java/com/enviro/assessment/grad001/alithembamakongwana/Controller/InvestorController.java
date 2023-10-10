package com.enviro.assessment.grad001.alithembamakongwana.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enviro.assessment.grad001.alithembamakongwana.Entity.Investor;
import com.enviro.assessment.grad001.alithembamakongwana.Repository.InvestorRepository;


@RestController
@RequestMapping("/investors")
public class InvestorController {

    private final InvestorRepository investorRepository;

    //@Autowired
    public InvestorController(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    @GetMapping
    public List<Investor> getAllInvestors() {
        return investorRepository.findAll();
    }

    public InvestorRepository getInvestorRepository() {
        return investorRepository;
    }
}
