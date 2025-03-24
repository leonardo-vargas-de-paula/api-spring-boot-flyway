package com.vargas.demo_api.controller;

import com.vargas.demo_api.dto.ProductDTO;
import com.vargas.demo_api.model.Product;
import com.vargas.demo_api.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;


    @GetMapping
    public ResponseEntity getAll() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ProductDTO pDto) {
        var product = new Product();
        BeanUtils.copyProperties(pDto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value="id") Integer id) {
        Optional product = productRepository.findById(id);

        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(product.get());
    }
}
