package com.enviro.assessment.grad001.alithembamakongwana.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enviro.assessment.grad001.alithembamakongwana.Entity.Product;
import com.enviro.assessment.grad001.alithembamakongwana.Repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByInvestorId(Long investorId) {
        return productRepository.findByInvestorId(investorId);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
}
