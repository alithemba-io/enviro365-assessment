package com.enviro.assessment.grad001.alithembamakongwana.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enviro.assessment.grad001.alithembamakongwana.Entity.Product;
import com.enviro.assessment.grad001.alithembamakongwana.Repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * @return
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
