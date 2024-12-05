package com.ennea.enneaservices.model.projection;

public interface InventoryAvailabilityView {

    Long getId();

    String getProductName();

    String getProductCode();

    String getInternalGroup();

    UserView getSupplier();

    interface UserView {

        Long getId();
    }

}
