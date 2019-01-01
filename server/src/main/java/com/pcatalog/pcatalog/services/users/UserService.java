package com.pcatalog.pcatalog.services.users;

import com.pcatalog.pcatalog.dtos.UserDto;
import com.pcatalog.pcatalog.models.User;

import java.util.List;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
