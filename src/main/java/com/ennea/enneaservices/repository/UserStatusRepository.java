package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.UserStatus;
import org.springframework.data.repository.CrudRepository;

public interface UserStatusRepository extends CrudRepository<UserStatus, Integer> {

    UserStatus findByStatus(String status);
}
