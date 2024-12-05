package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.ProductShortage;
import com.ennea.enneaservices.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductShortageRepository extends CrudRepository<ProductShortage, Long> {

    Optional<ProductShortage> findProductShortagesByUserAndProductName(User user, String productName);

    Optional<ProductShortage> findProductShortagesByUserAndId(User user, long id);

    List<ProductShortage> findAllByUser(User user);

    @Query("select ps from ProductShortage ps where ps.user = :user and ps.id in :ids")
    List<ProductShortage> findAllByUserAndIds(User user, Set<Long> ids);

}
