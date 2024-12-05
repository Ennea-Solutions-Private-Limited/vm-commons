package com.ennea.enneaservices.model.Dto;

public interface NotificationDTO {

    Long getUpdatedItemId();

    boolean getViewed();

    String getNotificationType();

    String getDescription();
}
