package com.pcatalog.pcatalog.repositories;

import com.pcatalog.pcatalog.models.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {
    Photo findByRecordId(Long id);
    List<Photo> findAll();
}
