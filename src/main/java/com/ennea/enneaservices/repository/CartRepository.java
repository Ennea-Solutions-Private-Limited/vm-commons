package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Cart;
import com.ennea.enneaservices.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CartRepository extends CrudRepository<Cart, Long> {

    @Query("select cart from Cart cart where cart.customer = :customer")
    List<Cart> findCartByCustomer(@Param("customer") User customer);

    @Query("select c from Cart c where c.supplier.id = :supplierId")
    List<Cart> findCartsBySupplierId(long supplierId);

    @Query("select cart from Cart cart where cart.customer = :customer and cart.supplier in (:suppliers)")
    List<Cart> findCartByCustomerAndSuppliers(@Param("customer") User customer,
                                              @Param("suppliers") List<User> suppliers);

    @Query("select cart from Cart cart where cart.supplier = :supplier and cart.representative = :representative and cart.customerDetails.partyCode in :partyCodes")
    List<Cart> findCartsBySupplierAndCustomerPartyCodesAndRepresentative(User supplier, Set<String> partyCodes,
                                                                         User representative);

    @Query("select cart from Cart cart where cart.customer = :customer and cart.supplier.id in :supplierIds and cart.representative is null ")
    List<Cart> findCartByCustomerAndSupplierIdsAndNotRepresentative(User customer,
                                                                    Set<Long> supplierIds);

    @Query("select cart from Cart cart where cart.supplier = :supplier and cart.representative = :representative")
    List<Cart> findCartsBySupplierAndRepresentative(User supplier, User representative);
}
