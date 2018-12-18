package com.pcatalog.pcatalog;

import com.pcatalog.pcatalog.models.Photo;
import com.pcatalog.pcatalog.models.Product;
import com.pcatalog.pcatalog.repositories.PhotoRepository;
import com.pcatalog.pcatalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apply")
public class TestController {
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/new")
    public ResponseEntity<Product> newProduct(@RequestBody ProductDto product){
        Photo photo = new Photo(product.getPhoto().getName(), product.getPhoto().getPhoto());
        photoRepository.save(photo);
        Product product1 = new Product(product.getName(), product.getDescription(), photo, product.getPrice());
        productRepository.save(product1);
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(){
        return "dfsfdfsdfdsfds";
    }
}
