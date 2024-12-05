package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Dto.NotificationDTO;
import com.ennea.enneaservices.model.Notification;
import com.ennea.enneaservices.model.TransactionMode;
import com.ennea.enneaservices.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

    @Query("select notification.id from Notification notification where notification.user= :user and notification.viewed = :viewed and notification.transactionMode.name = :mode")
    List<Long> findByUserAndModeAndViewed(User user, String mode, boolean viewed);

    @Query("select notification.updatedItemId as updatedItemId, " +
           "notification.description as description, " +
           "notification.viewed as viewed, " +
           "notification.notificationType.type as notificationType " +
           "from Notification notification " +
           "where notification.user= :user and notification.transactionMode.name = :mode order by notification.viewed asc, notification.id desc")
    List<NotificationDTO> findByUserAndMode(User user, String mode);

    @Transactional
    @Modifying
    @Query("update Notification notification set notification.viewed = true where notification.user = :user and notification.transactionMode = :transactionMode")
    void updateNotificationsToViewedByUserAndMode(User user, TransactionMode transactionMode);

    @Transactional
    @Modifying
    @Query("update Notification notification set notification.viewed = true where notification.id=:id and notification.user = :user and notification.transactionMode = :transactionMode ")
    void updateNotificationsToViewedByUserAndModeAndId(User user, TransactionMode transactionMode, Long id);

    @Transactional
    @Modifying
    @Query("delete from Notification notification where (FUNCTION('DATEDIFF', CURRENT_DATE, notification.date) > :days) ")
    void deleteNotificationsBeforeDays(int days);
}
