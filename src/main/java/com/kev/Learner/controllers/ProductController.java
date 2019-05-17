package com.kev.Learner.controllers;

import com.kev.Learner.entities.Product;
import com.kev.Learner.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepo;

    @GetMapping("/products")
    public Iterable<Product> getProducts() {
        return productRepo.findAll();
    }

    @GetMapping("/products/{id}")
    public Optional<Product> getProduct(@PathVariable long id) {
        return productRepo.findById(id);
    }

    @PostMapping("/products/add")
    public Product addProduct(@RequestBody Product product) {
        Product newProduct = new Product();
        return this.enterRecords(newProduct, product);
    }

    @DeleteMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable long id) {
        productRepo.deleteById(id);
    }

    @PutMapping("/products/update/{id}")
    public Product editProduct(@PathVariable long id, @RequestBody Product prod) {
        Product dbProduct = productRepo.getProductByProductId(id);
        return this.enterRecords(dbProduct, prod);
    }

    private Product enterRecords(Product dbProduct, Product userProduct){
        dbProduct.setName(userProduct.getName());
        dbProduct.setDescription(userProduct.getDescription());
        dbProduct.setPrice(userProduct.getPrice());
        dbProduct.setWeight(userProduct.getWeight());
        dbProduct.setUnit(userProduct.getUnit());
        dbProduct.setBatchNumber(userProduct.getBatchNumber());
        dbProduct.setManufactureDate(userProduct.getManufactureDate());
        dbProduct.setExpiryDate(userProduct.getExpiryDate());
        dbProduct.setStatus(userProduct.getStatus());

        productRepo.save(dbProduct);
        return dbProduct;
    }
}
