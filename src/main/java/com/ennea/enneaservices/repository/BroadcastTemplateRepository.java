package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.BroadcastTemplate;
import com.ennea.enneaservices.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BroadcastTemplateRepository extends CrudRepository<BroadcastTemplate, Long> {

    List<BroadcastTemplate> findAll();

    Optional<BroadcastTemplate> findByIdAndUser(Long id, User user);

    List<BroadcastTemplate> findAllByUser(User user);

}
