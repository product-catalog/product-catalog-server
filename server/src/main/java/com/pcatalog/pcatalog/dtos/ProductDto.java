package com.pcatalog.pcatalog.dtos;

public class ProductDto {
    private String name;
    private String description;
    private PhotoDto photo;
    private Double price;

    protected ProductDto(){}

    public ProductDto(String name, String description, PhotoDto photo, Double price) {
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PhotoDto getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoDto photo) {
        this.photo = photo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
