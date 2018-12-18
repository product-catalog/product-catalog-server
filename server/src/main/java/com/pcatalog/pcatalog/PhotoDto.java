package com.pcatalog.pcatalog;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;

public class PhotoDto {

    private String name;

    private String photo;

    protected PhotoDto(){}

    public PhotoDto(String name, String photo){
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
