package com.example.RealtorStroy.model.repository;

import com.example.RealtorStroy.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
