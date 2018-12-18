package com.pcatalog.pcatalog;

import com.pcatalog.pcatalog.models.Product;
import com.pcatalog.pcatalog.repositories.PhotoRepository;
import com.pcatalog.pcatalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/new")
    public ResponseEntity<Product> newProduct(@RequestBody Product product){
        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
