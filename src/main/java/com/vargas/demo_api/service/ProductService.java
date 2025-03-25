package com.vargas.demo_api.service;

import com.vargas.demo_api.exceptions.RecursoNaoEncontradoException;
import com.vargas.demo_api.model.Product;
import com.vargas.demo_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Integer id) {
        return productRepository.findById(id)
            .orElseThrow(()-> new RecursoNaoEncontradoException("Produto com ID: " + id + " não encontrado"));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Integer id) {

        if (productRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto com ID "+id +" não encontrado.");
        }

        productRepository.deleteById(id);
    }

}
