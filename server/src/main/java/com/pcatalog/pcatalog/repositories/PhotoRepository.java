package com.pcatalog.pcatalog.repositories;

import com.pcatalog.pcatalog.models.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {
}
