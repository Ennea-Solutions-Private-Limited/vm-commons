package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.PreferenceDefault;
import com.ennea.enneaservices.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PreferenceDefaultRepository extends CrudRepository<PreferenceDefault, Long> {

    Optional<PreferenceDefault> findFirstBySourceRoleNameAndFeatureNameAndTargetRoleName(String sourceRole,
                                                                                         String feature,
                                                                                         String targetRole);

    List<PreferenceDefault> findAllBySourceRole(Role role);

}
