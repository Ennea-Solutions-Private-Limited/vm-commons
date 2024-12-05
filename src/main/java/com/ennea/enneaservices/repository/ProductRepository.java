package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("select p from Product p where (:query is null or upper(p.name) like CONCAT(upper(:query),'%'))"
           + " or (:query is null or upper(p.composition) like CONCAT(upper(:query),'%'))")
    Page<Product> findAllByProductNameOrComposition(@Param("query") String query, Pageable pageable);

    @Query("select p from Product p where p.slug in :slugs")
    List<Product> findAllBySlugs(List<String> slugs);

}
