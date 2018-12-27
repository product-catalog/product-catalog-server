package com.pcatalog.pcatalog.models;

import com.pcatalog.pcatalog.models.base.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "photos")
public class Photo extends Model {

    @NotEmpty
    private String name;

    @Lob
    @NotEmpty
    private byte[] photo;

    protected Photo(){}

    public Photo(@NotEmpty String name, @NotEmpty byte[] photo) {
        setName(name);
        setPhoto(photo);
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
