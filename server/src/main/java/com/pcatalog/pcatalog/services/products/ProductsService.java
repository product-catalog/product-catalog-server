package com.pcatalog.pcatalog.services.products;

import com.pcatalog.pcatalog.dtos.ProductDto;
import com.pcatalog.pcatalog.models.Product;

import java.util.List;

public interface ProductsService {
    Product createNewProduct(ProductDto productDto);
    Product deleteProduct(Long id);
    Iterable<Product> getAllProducts();
    Iterable<Product> getProductByName(String name);
    Iterable<Product> getProductByPrice(Double price);
    Iterable<Product> getProductByNameAndByPrice(String name, Double price);
    Product getByRecordId(Long id);
    Product editProduct(Product product);
}
