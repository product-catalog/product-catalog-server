package com.pcatalog.pcatalog.repositories;

import com.pcatalog.pcatalog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByRecordId(Long id);
    List<Product> findAllByNameContainsAndPriceBetween(String regex, Double minPrice, Double maxPrice);
    List<Product> findAllByPriceBetween(Double min, Double max);
    List<Product> findAllByNameContains(String regex);
    List<Product> findAll();
}
