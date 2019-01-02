package com.pcatalog.pcatalog.services.products;

import com.pcatalog.pcatalog.dtos.ProductDto;
import com.pcatalog.pcatalog.models.Product;

import java.util.List;

public interface ProductsService {
    Product createNewProduct(ProductDto productDto);
    Product deleteProduct(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByName(String name);
    List<Product> getProductsByPrice(Double min, Double max);
    List<Product> getProductsByNameAndByPrice(String name, Double minPrice, Double maxPrice);
    Product getByRecordId(Long id);
    Product editProduct(Product product);
}
