package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.District;
import com.ennea.enneaservices.model.State;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends CrudRepository<District, Integer> {

    List<District> findAllByState(State state);

    @Query("select d from District d where d.name = :name and d.state.name = :state")
    Optional<District> findByName(@Param("name") String name, @Param("state") String state);
}
