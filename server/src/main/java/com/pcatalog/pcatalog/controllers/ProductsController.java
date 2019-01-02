package com.pcatalog.pcatalog.controllers;

import com.pcatalog.pcatalog.dtos.ProductDto;
import com.pcatalog.pcatalog.models.Product;
import com.pcatalog.pcatalog.services.products.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> newProduct(@RequestBody ProductDto productDto) {
        Product product = productsService.createNewProduct(productDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> delete(@RequestParam Long id) {
        Product product = productsService.deleteProduct(id);
        if (product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> allProducts = productsService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/getByName")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Product>> getByName(@RequestParam String name) {
        List<Product> filteredProductsByName = productsService.getProductsByName(name);
        return new ResponseEntity<>(filteredProductsByName, HttpStatus.OK);
    }

    @GetMapping("/getByPrice")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Product>> getByPrice(@RequestParam Double minPrice, Double maxPrice) {
        List<Product> filteredProductsByPrice = productsService.getProductsByPrice(minPrice, maxPrice);
        return new ResponseEntity<>(filteredProductsByPrice, HttpStatus.OK);
    }

    @GetMapping("/getByNameAndPrice")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Product>> getByNameAndPrice(@RequestParam String name, @RequestParam Double minPrice, @RequestParam Double maxPrice) {
        List<Product> filteredProductsByNameAndByPrice = productsService.getProductsByNameAndByPrice(name, minPrice, maxPrice);
        return new ResponseEntity<>(filteredProductsByNameAndByPrice, HttpStatus.OK);
    }

    @GetMapping("/getById")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> getProductById(@RequestParam Long id){
        return new ResponseEntity<>(productsService.getByRecordId(id), HttpStatus.OK);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> editProduct(@RequestBody Product product){
        Product product1 = productsService.editProduct(product);
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }
}
