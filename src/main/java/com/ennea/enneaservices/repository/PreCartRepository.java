package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.PreCart;
import com.ennea.enneaservices.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface PreCartRepository extends CrudRepository<PreCart, Long> {

    @Query("select pc from PreCart pc where pc.user = :user and pc.id in :ids")
    List<PreCart> findAllByUserAndIds(User user, Set<Long> ids);

    @Query("select pc from PreCart pc where pc.user = :user")
    List<PreCart> findAllByUser(User user);
}
