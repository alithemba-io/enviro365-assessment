package com.enviro.assessment.grad001.alithembamakongwana.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enviro.assessment.grad001.alithembamakongwana.Entity.Investor;
import com.enviro.assessment.grad001.alithembamakongwana.Repository.InvestorRepository;

@Service
public class InvestorService {
    private final InvestorRepository investorRepository;

    @Autowired
    public InvestorService(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public Investor createInvestor(Investor investor) {
        return investorRepository.save(investor);
    }

    public Investor getInvestorById(Long id) {
        return investorRepository.findById(id).orElse(null);
    }

    public void deleteInvestor(Long id) {
        investorRepository.deleteById(id);
    }
}
