package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.RoleConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleConfigurationRepository extends CrudRepository<RoleConfiguration, Integer> {

    @Query("select rc from RoleConfiguration rc where rc.role.name = :role and lower(rc.configuration.name) like CONCAT('%',lower(:configurationSuffix))")
    Optional<RoleConfiguration> findByRoleAndConfiguration(@Param("role") String role,
                                                           @Param("configurationSuffix") String configurationSuffix);

    @Query("select rc from RoleConfiguration rc where rc.role.id = :roleId")
    List<RoleConfiguration> findByRoleId(@Param("roleId") int roleId);

}
