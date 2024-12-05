package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Role;
import com.ennea.enneaservices.model.RoleBanner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleBannerRepository extends CrudRepository<RoleBanner, Long> {

    Optional<RoleBanner> findFirstByRole(Role role);

    List<RoleBanner> findAllByRole(Role role);
}