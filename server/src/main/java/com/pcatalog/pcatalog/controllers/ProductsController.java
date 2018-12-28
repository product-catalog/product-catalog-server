package com.pcatalog.pcatalog.controllers;

import com.pcatalog.pcatalog.dtos.ProductDto;
import com.pcatalog.pcatalog.models.Photo;
import com.pcatalog.pcatalog.models.Product;
import com.pcatalog.pcatalog.repositories.PhotoRepository;
import com.pcatalog.pcatalog.repositories.ProductRepository;
import com.pcatalog.pcatalog.services.products.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @PostMapping("/new")
    public ResponseEntity<Product> newProduct(@RequestBody ProductDto productDto) {
        Product product = productsService.createNewProduct(productDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Product> delete(@RequestParam Long id) {
        Product product = productsService.deleteProduct(id);
        if (product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<Product>> get() {
        Iterable<Product> allProducts = productsService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    //filter by name, price, both
    @GetMapping("/getByName")
    public ResponseEntity<Iterable<Product>> getByName(@RequestParam String name) {
        Iterable<Product> filteredProductsByName = productsService.getProductByName(name);
        return new ResponseEntity<>(filteredProductsByName, HttpStatus.OK);
    }

    @GetMapping("/getByPrice")
    public ResponseEntity<Iterable<Product>> getByPrice(@RequestParam Double price) {
        Iterable<Product> filteredProductsByPrice = productsService.getProductByPrice(price);
        return new ResponseEntity<>(filteredProductsByPrice, HttpStatus.OK);
    }

    @GetMapping("/getByNameAndPrice")
    public ResponseEntity<Iterable<Product>> getByNameAndPrice(@RequestParam String name, @RequestParam Double price) {
        Iterable<Product> filteredProductsByNameAndByPrice = productsService.getProductByNameAndByPrice(name, price);
        return new ResponseEntity<>(filteredProductsByNameAndByPrice, HttpStatus.OK);
    }

    @GetMapping("/getById")
    public ResponseEntity<Product> getProductById(@RequestParam Long id){
        return new ResponseEntity<>(productsService.getByRecordId(id), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Product> editProduct(@RequestBody Product product){
        Product product1 = productsService.editProduct(product);
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }
}
