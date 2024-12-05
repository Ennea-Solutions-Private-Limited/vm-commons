package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.CouponSummary;
import com.ennea.enneaservices.model.Ledger;
import com.ennea.enneaservices.model.SupplierCustomer;
import com.ennea.enneaservices.model.SupplierRepresentative;
import com.ennea.enneaservices.model.User;
import lombok.Data;

@Data
public class PaymentMetadataDAO {
    private User supplier;

    private User customer;

    private User representative;

    private Ledger ledger;

    private SupplierRepresentative supplierRepresentative;

    private SupplierCustomer supplierCustomer;

    private CouponSummary couponSummary;
}
