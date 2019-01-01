package com.pcatalog.pcatalog.services.products;

import com.pcatalog.pcatalog.dtos.ProductDto;
import com.pcatalog.pcatalog.models.Photo;
import com.pcatalog.pcatalog.models.Product;
import com.pcatalog.pcatalog.repositories.PhotoRepository;
import com.pcatalog.pcatalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.print(productDto.getPhoto().getName());
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
        return productRepository.findAll();
    }

    @Override
    public Iterable<Product> getProductByName(String name) {
        return productRepository.findAllByNameContains(name);
    }

    @Override
    public Iterable<Product> getProductByPrice(Double minPrice, Double maxPrice) {
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public Iterable<Product> getProductByNameAndByPrice(String name, Double minPrice, Double maxPrice) {
        return productRepository.findAllByNameContainsAndPriceBetween(name, minPrice, maxPrice);
    }

    @Override
    public Product getByRecordId(Long id) {
        return productRepository.findByRecordId(id);
    }

    @Override
    public Product editProduct(Product product) {
        Product product1 = productRepository.findByRecordId(product.getRecordId());
        product1.setPrice(product.getPrice());
        Photo photo = photoRepository.findByRecordId(product.getPhoto().getRecordId());
        photo.setName(product.getPhoto().getName());
        photo.setPhoto(product.getPhoto().getPhoto());
        photoRepository.save(photo);
        product1.setPhoto(product.getPhoto());
        product1.setDescription(product.getDescription());
        product1.setName(product.getName());
        return productRepository.save(product1);
    }
}
