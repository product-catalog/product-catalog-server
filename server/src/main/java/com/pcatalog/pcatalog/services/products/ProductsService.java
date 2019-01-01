package com.pcatalog.pcatalog.services.products;

import com.pcatalog.pcatalog.dtos.ProductDto;
import com.pcatalog.pcatalog.models.Product;

import java.util.List;

public interface ProductsService {
    Product createNewProduct(ProductDto productDto);
    Product deleteProduct(Long id);
    Iterable<Product> getAllProducts();
    Iterable<Product> getProductByName(String name);
    Iterable<Product> getProductByPrice(Double min, Double max);
    Iterable<Product> getProductByNameAndByPrice(String name, Double minPrice, Double maxPrice);
    Product getByRecordId(Long id);
    Product editProduct(Product product);
}
