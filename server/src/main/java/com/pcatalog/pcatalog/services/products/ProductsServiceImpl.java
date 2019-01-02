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
    public List<Product> getProductsByName(String name) {
        return productRepository.findAllByNameContains(name);
    }

    @Override
    public List<Product> getProductsByPrice(Double minPrice, Double maxPrice) {
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> getProductsByNameAndByPrice(String name, Double minPrice, Double maxPrice) {
        return productRepository.findAllByNameContainsAndPriceBetween(name, minPrice, maxPrice);
    }

    @Override
    public Product getByRecordId(Long id) {
        return productRepository.findByRecordId(id);
    }

    @Override
    public Product editProduct(Product product) {
        Product editedProduct = productRepository.findByRecordId(product.getRecordId());
        editedProduct.setPrice(product.getPrice());
        Photo photo = photoRepository.findByRecordId(product.getPhoto().getRecordId());
        photo.setName(product.getPhoto().getName());
        photo.setPhoto(product.getPhoto().getPhoto());
        photoRepository.save(photo);
        editedProduct.setPhoto(product.getPhoto());
        editedProduct.setDescription(product.getDescription());
        editedProduct.setName(product.getName());
        return productRepository.save(editedProduct);
    }
}
