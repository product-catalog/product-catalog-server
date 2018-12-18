package com.pcatalog.pcatalog.models;

import com.pcatalog.pcatalog.models.base.Model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product extends Model {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    @OneToOne
    private Photo photo;
    @NotNull
    private Double price;

    protected Product(){}

    public Product(@NotEmpty String name, @NotEmpty String description, @NotEmpty Photo photo, @NotEmpty Double price) {
        setName(name);
        setDescription(description);
        setPhoto(photo);
        setPrice(price);
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

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
