package com.pcatalog.pcatalog.repositories;

import com.pcatalog.pcatalog.models.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Photo, Long> {
}
