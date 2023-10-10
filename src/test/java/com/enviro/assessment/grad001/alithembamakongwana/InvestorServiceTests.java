package com.enviro.assessment.grad001.alithembamakongwana;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;

import com.enviro.assessment.grad001.alithembamakongwana.Entity.Investor;
import com.enviro.assessment.grad001.alithembamakongwana.Service.InvestorService;

@SpringBootTest
@ComponentScan(basePackages = "com.enviro.assessment.grad001.alithembamakongwana.Service")


@Service
@DirtiesContext
class InvestorServiceTests {

    @Autowired
    private InvestorService investorService;

    @Test
    void testCreateInvestor() {
        Investor investor = new Investor("John", "Doe");
        Investor savedInvestor = investorService.createInvestor(investor);

        assertNotNull(savedInvestor.getId());
        assertEquals("John", savedInvestor.getFirstName());
        assertEquals("Doe", savedInvestor.getLastName());
    }

    @Test
    void testGetInvestorById() {
        // Create an investor and save it
        Investor investor = new Investor("Jane", "Smith");
        Investor savedInvestor = investorService.createInvestor(investor);

        // Retrieve the saved investor by ID
        Long id = savedInvestor.getId();
        Investor retrievedInvestor = investorService.getInvestorById(id);

        assertNotNull(retrievedInvestor);
        assertEquals("Jane", retrievedInvestor.getFirstName());
        assertEquals("Smith", retrievedInvestor.getLastName());
    }

    @Test
    void testDeleteInvestor() {
        // Create an investor and save it
        Investor investor = new Investor("Bob", "Johnson");
        Investor savedInvestor = investorService.createInvestor(investor);

        // Delete the saved investor by ID
        Long id = savedInvestor.getId();
        investorService.deleteInvestor(id);

        // Try to retrieve the deleted investor
        Investor deletedInvestor = investorService.getInvestorById(id);
        assertNull(deletedInvestor);
    }
}
