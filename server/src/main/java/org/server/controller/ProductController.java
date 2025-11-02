package org.server.controller;

import org.server.modal.Product;
import org.server.modal.Users;
import org.server.service.CustomUserDetailsService;
import org.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

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

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        try{
            product = productService.addProduct(product,imageFile);
            return  new ResponseEntity<>(product,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        return new ResponseEntity<>(productService.getProductImage(id),HttpStatus.OK);
    }

    @PutMapping("product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile) {
        try{
            product = productService.updateProduct(id,product,imageFile);;
            return  new ResponseEntity<>(product,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        boolean isDeleted = productService.deleteProduct(id);
        if(isDeleted){
            return new ResponseEntity<>("Product Deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products/search")
    public List<Product> getProductsByKeyword(@RequestParam String keyword){
        return productService.getProductsByKeyword(keyword);
    }

    @PostMapping("/users/addUser")
    public ResponseEntity<?> addUser(@RequestBody Users user){
        return new ResponseEntity<>(customUserDetailsService.addUser(user),HttpStatus.CREATED);
    }

}
