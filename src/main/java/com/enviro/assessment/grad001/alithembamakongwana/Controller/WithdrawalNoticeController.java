package com.enviro.assessment.grad001.alithembamakongwana.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enviro.assessment.grad001.alithembamakongwana.Entity.Investor;
import com.enviro.assessment.grad001.alithembamakongwana.Entity.Product;
import com.enviro.assessment.grad001.alithembamakongwana.Entity.ProductType;
import com.enviro.assessment.grad001.alithembamakongwana.Entity.WithdrawalNotice;
import com.enviro.assessment.grad001.alithembamakongwana.Entity.WithdrawalNoticeDTO;
import com.enviro.assessment.grad001.alithembamakongwana.Repository.InvestorRepository;
import com.enviro.assessment.grad001.alithembamakongwana.Repository.WithdrawalNoticeRepository;
import com.enviro.assessment.grad001.alithembamakongwana.Service.ProductService;
import com.opencsv.CSVWriter;

@RestController
@RequestMapping("/withdrawal-notices")
public class WithdrawalNoticeController {
    private final WithdrawalNoticeRepository withdrawalNoticeRepository;
    private final CSVWriter csvWriter;
    private final InvestorRepository investorRepository;
    private final ProductService productService;


   
    public WithdrawalNoticeController(CSVWriter csvWriter, WithdrawalNoticeRepository withdrawalNoticeRepository, InvestorRepository investorRepository, ProductService productService) {
        this.csvWriter = csvWriter;
        this.withdrawalNoticeRepository = withdrawalNoticeRepository;
        this.investorRepository = investorRepository;
        this.productService = productService;
    }
    
    @GetMapping
    public List<WithdrawalNotice> findAll() {
        return withdrawalNoticeRepository.findAll();
    }
     
    @PostMapping("/create-withdrawal")
    public ResponseEntity<String> createWithdrawalNotice(@RequestBody WithdrawalNoticeDTO withdrawalNoticeDTO){
        Investor investor = investorRepository.findById(withdrawalNoticeDTO.getInvestorId()).orElse(null);
        Product product = productService.getProductById(withdrawalNoticeDTO.getProductId());

        if (investor == null || product == null || !product.getInvestor().equals(investor)) {
            return ResponseEntity.badRequest().body("Invalid investor or product");
        }

        if (product.getType() == ProductType.RETIREMENT && investor.getAge() <= 65) {
            return ResponseEntity.badRequest().body("Investor must be older than 65 for RETIREMENT withdrawal");
        }

        BigDecimal currentBalance = product.getCurrentBalance();
        if (withdrawalNoticeDTO.getWithdrawalAmount().compareTo(currentBalance.multiply(new BigDecimal("0.9"))) > 0) {
            return ResponseEntity.badRequest().body("Withdrawal amount exceeds 90% of the current balance");
        }
    
        // Calculate new balance
        BigDecimal withdrawalAmount = withdrawalNoticeDTO.getWithdrawalAmount();

        // Calculate new balance
        BigDecimal newBalance = currentBalance.subtract(withdrawalAmount);
        product.setCurrentBalance(newBalance);

        // Save the updated product
        productService.saveProduct(new com.enviro.assessment.grad001.alithembamakongwana.Entity.Product());

        // Create and save the withdrawal notice
        WithdrawalNotice withdrawalNotice = new WithdrawalNotice();
        withdrawalNotice.setProductId(product.getId());
        withdrawalNotice.setWithdrawalAmount(withdrawalAmount);
        withdrawalNotice.setWithdrawalDate(new Date()); // You can set the withdrawal date here
        withdrawalNoticeRepository.save(withdrawalNotice);

        // Construct the response
        String responseMessage = "Withdrawal notice created successfully\n"
            + "Balance before withdrawal: " + currentBalance + "\n"
            + "Withdrawal amount: " + withdrawalAmount + "\n"
            + "Closing balance: " + newBalance;
        
        return ResponseEntity.ok("Withdrawal notice created successfully");
    }
    
    @GetMapping("/download-csv")
    public ResponseEntity<byte[]> downloadCsv(
            @RequestParam Long productId,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) throws IOException {
        Date parsedStartDate = null;
        Date parsedEndDate = null;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            parsedStartDate = dateFormat.parse(startDate);
            parsedEndDate = dateFormat.parse(endDate);
        } catch (ParseException e) {
            // Handle the parsing exception
            e.printStackTrace();
        }

        List<WithdrawalNotice> withdrawalNotices = withdrawalNoticeRepository.findByProductIdAndWithdrawalDateBetween(productId, parsedStartDate, parsedEndDate);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //CSVWriter csvWriter = new CSVWriter(baos);

        // Write CSV header
        String[] header = {"Withdrawal ID", "Product ID", "Withdrawal Amount", "Withdrawal Date"};
        csvWriter.writeNext(header);

        // Write withdrawal notices to CSV
        for (WithdrawalNotice notice : withdrawalNotices) {
            String[] row = {
                notice.getId().toString(),
                notice.getProductId().toString(),
                String.valueOf(notice.getWithdrawalAmount()),
                notice.getWithdrawalDate().toString()
            };
            csvWriter.writeNext(row);
        }

        csvWriter.close();

        byte[] csvBytes = baos.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "withdrawal-notices.csv");

        return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);
    } 
}
