package org.server.controller;

import org.server.modal.Product;
import org.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public String index(){
        return "Hello World";
    }

    @RequestMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = productService.getProductById(id);
        HttpStatus httpResponseStatus = product == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(product, httpResponseStatus);
    }
}
