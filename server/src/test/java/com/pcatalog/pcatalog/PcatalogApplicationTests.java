package com.pcatalog.pcatalog;

import com.pcatalog.pcatalog.dtos.PhotoDto;
import com.pcatalog.pcatalog.dtos.ProductDto;
import com.pcatalog.pcatalog.models.Product;
import com.pcatalog.pcatalog.repositories.PhotoRepository;
import com.pcatalog.pcatalog.repositories.ProductRepository;
import com.pcatalog.pcatalog.services.products.ProductsServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PcatalogApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private ProductsServiceImpl productsServiceImpl;

    @Test
    @DirtiesContext
    public void createNewProduct_Should_BeSavedInDB_When_MethodCalled() {
        ProductDto productDto = new ProductDto("abc", "bcd", new PhotoDto("aa", "aa"), 2.0);
        Product product = productsServiceImpl.createNewProduct(productDto);
        Assert.assertEquals(1, productRepository.findAll().size());
    }

    @Test
    @DirtiesContext
    public void createNewProduct_Should_ReturnTheNewProduct_When_MethodCalled() {
        ProductDto productDto = new ProductDto("abc", "bcd", new PhotoDto("aa", "aa"), 2.0);
        Product product = productsServiceImpl.createNewProduct(productDto);
        Assert.assertEquals(product.getRecordId(), productRepository.findAll().get(productRepository.findAll().size()
                - 1).getRecordId());
    }

    @Test
    @DirtiesContext
    public void createNewProduct_Should_BePhotoSavedInDB_When_MethodCalled() {
        ProductDto productDto = new ProductDto("abc", "bcd", new PhotoDto("aa", "aa"), 2.0);
        Product product = productsServiceImpl.createNewProduct(productDto);
        Assert.assertEquals(product.getPhoto().getRecordId(), photoRepository.findAll().get(photoRepository.findAll()
                .size() - 1).getRecordId());
    }

    @Test
    @DirtiesContext
    public void deleteProduct_Should_DeleteProduct_When_MethodCalled() {
        ProductDto productDto = new ProductDto("abc", "bcd", new PhotoDto("aa", "aa"), 2.0);
        Product product = productsServiceImpl.createNewProduct(productDto);
        productsServiceImpl.deleteProduct(product.getRecordId());
        Assert.assertEquals(0, productRepository.findAll().size());
    }

    @Test
    @DirtiesContext
    public void deleteProduct_Should_ReturnDeletedProduct_When_MethodCalled() {
        ProductDto productDto = new ProductDto("abc", "bcd", new PhotoDto("aa", "aa"), 2.0);
        Product product = productsServiceImpl.createNewProduct(productDto);
        productsServiceImpl.deleteProduct(product.getRecordId());
        Assert.assertNull(productRepository.findByRecordId(product.getRecordId()));
    }

    @Test
    @DirtiesContext
    public void deleteProduct_Should_ReturnNull_When_ProductDoesntExists() {
        Product deletedProduct = productsServiceImpl.deleteProduct(10L);
        Assert.assertNull(deletedProduct);
    }

    @Test
    @DirtiesContext
    public void getAllProducts_Should_ReturnListOfAllProductsInDB_When_Called() {
        ProductDto productDto = new ProductDto("abc", "bcd", new PhotoDto("aa", "aa"), 2.0);
        productsServiceImpl.createNewProduct(productDto);
        productsServiceImpl.createNewProduct(productDto);
        productsServiceImpl.createNewProduct(productDto);
        Assert.assertEquals(3, productRepository.findAll().size());
    }

    @Test
    @DirtiesContext
    public void getProductByName_Should_ReturnListOfFilteredProductsInDB_When_Called() {
        ProductDto productDto = new ProductDto("abc", "bcd", new PhotoDto("aa", "aa"), 2.0);
        productsServiceImpl.createNewProduct(productDto);
        productsServiceImpl.createNewProduct(productDto);
        productDto.setName("ccc");
        productsServiceImpl.createNewProduct(productDto);
        Assert.assertEquals(2, productRepository.findAllByName("abc").size());
    }

    @Test
    @DirtiesContext
    public void getProductByPrice_Should_ReturnListOfFilteredProductsInDB_When_Called() {
        ProductDto productDto = new ProductDto("abc", "bcd", new PhotoDto("aa", "aa"), 2.0);
        productsServiceImpl.createNewProduct(productDto);
        productsServiceImpl.createNewProduct(productDto);
        productDto.setPrice(30.0);
        productsServiceImpl.createNewProduct(productDto);
        Assert.assertEquals(2, productRepository.findAllByPrice(2.0).size());
    }

    @Test
    @DirtiesContext
    public void getProductByNameAndByPrice_Should_ReturnListOfFilteredProductsInDB_When_Called() {
        ProductDto productDto = new ProductDto("abc", "bcd", new PhotoDto("aa", "aa"), 2.0);
        productsServiceImpl.createNewProduct(productDto);
        productDto.setName("ccc");
        productsServiceImpl.createNewProduct(productDto);
        productDto.setPrice(30.0);
        productsServiceImpl.createNewProduct(productDto);
        Assert.assertEquals(1, productRepository.findAllByNameAndPrice("abc", 2.0).size());
    }
}

