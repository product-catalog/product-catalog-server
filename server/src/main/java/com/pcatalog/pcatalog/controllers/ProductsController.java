package com.pcatalog.pcatalog.controllers;

import com.pcatalog.pcatalog.dtos.ProductDto;
import com.pcatalog.pcatalog.models.Product;
import com.pcatalog.pcatalog.services.products.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @PostMapping("/new")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> newProduct(@RequestBody ProductDto productDto) {
        Product product = productsService.createNewProduct(productDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> delete(@RequestParam Long id) {
        Product product = productsService.deleteProduct(id);
        if (product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Iterable<Product>> get() {
        Iterable<Product> allProducts = productsService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }
    
    @GetMapping("/getByName")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Iterable<Product>> getByName(@RequestParam String name) {
        Iterable<Product> filteredProductsByName = productsService.getProductByName(name);
        return new ResponseEntity<>(filteredProductsByName, HttpStatus.OK);
    }

    @GetMapping("/getByPrice")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Iterable<Product>> getByPrice(@RequestParam Double minPrice, Double maxPrice) {
        Iterable<Product> filteredProductsByPrice = productsService.getProductByPrice(minPrice, maxPrice);
        return new ResponseEntity<>(filteredProductsByPrice, HttpStatus.OK);
    }

    @GetMapping("/getByNameAndPrice")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Iterable<Product>> getByNameAndPrice(@RequestParam String name, @RequestParam Double minPrice, @RequestParam Double maxPrice) {
        Iterable<Product> filteredProductsByNameAndByPrice = productsService.getProductByNameAndByPrice(name, minPrice, maxPrice);
        return new ResponseEntity<>(filteredProductsByNameAndByPrice, HttpStatus.OK);
    }

    @GetMapping("/getById")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> getProductById(@RequestParam Long id){
        return new ResponseEntity<>(productsService.getByRecordId(id), HttpStatus.OK);
    }

    @PutMapping("/edit")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> editProduct(@RequestBody Product product){
        Product product1 = productsService.editProduct(product);
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }
}
