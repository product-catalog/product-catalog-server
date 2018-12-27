package com.pcatalog.pcatalog.dtos;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;

public class PhotoDto {

    private String name;

    private byte[] photo;

    protected PhotoDto(){}

    public PhotoDto(String name, byte[] photo){
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
