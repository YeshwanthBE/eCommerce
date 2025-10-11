package org.server.controller;

import org.server.modal.Product;
import org.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
}
