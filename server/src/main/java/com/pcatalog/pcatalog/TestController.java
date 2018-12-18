package com.pcatalog.pcatalog;

import com.pcatalog.pcatalog.models.Photo;
import com.pcatalog.pcatalog.models.Product;
import com.pcatalog.pcatalog.repositories.PhotoRepository;
import com.pcatalog.pcatalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/delete")
    public ResponseEntity<Product> delete(@RequestBody Long id){
        Product product = productRepository.findByRecordId(id);
        productRepository.delete(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Iterable<Product>> get(){
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    //filter by name, price, both
    @GetMapping("/getByName")
    public ResponseEntity<Iterable<Product>> getByName(@RequestParam String name){
        return new ResponseEntity<>(productRepository.findAllByName(name), HttpStatus.OK);
    }

    @GetMapping("/getByPrice")
    public ResponseEntity<Iterable<Product>> getByPrice(@RequestParam Double price){
        return new ResponseEntity<>(productRepository.findAllByPrice(price), HttpStatus.OK);
    }

    @GetMapping("/getByNameAndPrice")
    public ResponseEntity<Iterable<Product>> getByNameAndPrice(@RequestParam String name, @RequestParam Double price){
        return new ResponseEntity<>(productRepository.findAllByNameAndPrice(name, price), HttpStatus.OK);
    }
}
