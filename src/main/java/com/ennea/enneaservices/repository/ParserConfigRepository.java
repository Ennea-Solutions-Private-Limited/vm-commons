package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.ParserConfig;
import com.ennea.enneaservices.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ParserConfigRepository extends CrudRepository<ParserConfig, Long> {

    Optional<ParserConfig> findByUserAndTemplateName(User user, String templateName);

}
