package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.NotificationType;
import org.springframework.data.repository.CrudRepository;

public interface NotificationTypeRepository extends CrudRepository<NotificationType, Integer> {

    NotificationType findByType(String notificationType);
}
