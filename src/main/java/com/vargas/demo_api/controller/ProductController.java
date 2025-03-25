package com.vargas.demo_api.controller;

import com.vargas.demo_api.dto.ProductDTO;
import com.vargas.demo_api.exceptions.RecursoNaoEncontradoException;
import com.vargas.demo_api.model.Product;
import com.vargas.demo_api.repository.ProductRepository;
import com.vargas.demo_api.service.ProductService;
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

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping
    public ResponseEntity getAll() {
        List<Product> products = productService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ProductDTO pDto) {
        var product = new Product();
        BeanUtils.copyProperties(pDto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value="id") Integer id) {
        Product product = productService.getById(id);

//        if (product.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
//        }

        return ResponseEntity.status(HttpStatus.FOUND).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value="id") Integer id){
        Product product = productService.getById(id);

        productService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value="id") Integer id, @RequestBody ProductDTO pDto){
        Product product = productService.getById(id);

        var productModel = product;
        BeanUtils.copyProperties(pDto ,productModel);

        return ResponseEntity.status(HttpStatus.OK).body(productService.save(productModel));
    }

}
