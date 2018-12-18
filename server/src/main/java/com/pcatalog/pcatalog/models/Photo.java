package com.pcatalog.pcatalog.models;

import com.pcatalog.pcatalog.models.base.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "photos")
public class Photo extends Model {

    @NotEmpty
    private String name;

    @Lob
    @NotEmpty
    private String photo;

    protected Photo(){}

    public Photo(@NotEmpty String name, @NotEmpty String photo) {
        setName(name);
        setPhoto(photo);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
