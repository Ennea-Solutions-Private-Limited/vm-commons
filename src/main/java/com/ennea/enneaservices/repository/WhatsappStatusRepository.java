package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.WhatsappStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WhatsappStatusRepository extends JpaRepository<WhatsappStatus, Long> {

    Optional<WhatsappStatus> findByMessageId(String str);

}
