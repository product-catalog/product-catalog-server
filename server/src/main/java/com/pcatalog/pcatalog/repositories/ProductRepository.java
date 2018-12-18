package com.pcatalog.pcatalog.repositories;

import com.pcatalog.pcatalog.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
