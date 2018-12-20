package com.pcatalog.pcatalog.services.products;

import com.pcatalog.pcatalog.dtos.ProductDto;
import com.pcatalog.pcatalog.models.Photo;
import com.pcatalog.pcatalog.models.Product;
import com.pcatalog.pcatalog.repositories.PhotoRepository;
import com.pcatalog.pcatalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "productsService")
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createNewProduct(ProductDto productDto) {
        Photo photo = new Photo(productDto.getPhoto().getName(), productDto.getPhoto().getPhoto());
        photoRepository.save(photo);
        Product product = new Product(productDto.getName(), productDto.getDescription(), photo, productDto.getPrice());
        productRepository.save(product);
        return product;
    }

    @Override
    public Product deleteProduct(Long id) {
        Product product = productRepository.findByRecordId(id);
        if (product == null){
            return null;
        }
        productRepository.delete(product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts;
    }

    @Override
    public Iterable<Product> getProductByName(String name) {
        Iterable<Product> filteredProductsByName = productRepository.findAllByName(name);
        return filteredProductsByName;
    }

    @Override
    public Iterable<Product> getProductByPrice(Double price) {
        Iterable<Product> filteredProductsByPrice = productRepository.findAllByPrice(price);
        return filteredProductsByPrice;
    }

    @Override
    public Iterable<Product> getProductByNameAndByPrice(String name, Double price) {
        Iterable<Product> filteredProductsByNameAndByPrice = productRepository.findAllByNameAndPrice(name, price);
        return filteredProductsByNameAndByPrice;
    }
}
