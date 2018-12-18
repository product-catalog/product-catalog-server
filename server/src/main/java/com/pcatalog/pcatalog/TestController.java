package com.pcatalog.pcatalog;

import com.pcatalog.pcatalog.dtos.ProductDto;
import com.pcatalog.pcatalog.models.Photo;
import com.pcatalog.pcatalog.models.Product;
import com.pcatalog.pcatalog.repositories.PhotoRepository;
import com.pcatalog.pcatalog.repositories.ProductRepository;
import com.pcatalog.pcatalog.services.products.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apply")
public class TestController {

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
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/get")
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
}
