package org.server.service;

import org.server.modal.Product;
import org.server.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile image) throws IOException {

        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImage(image.getBytes());
        return productRepo.save(product);
    }

    public byte[] getProductImage(int id) {
        Product product= productRepo.findById(id).get();
        return product.getImage();
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException{
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImage(imageFile.getBytes());
        if(productRepo.existsById(id)){
            productRepo.save(product);
        }
        return productRepo.findById(id).orElse(null);
    }

    public boolean deleteProduct(int id) {
        if(productRepo.existsById(id)){
            productRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
