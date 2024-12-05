package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Quotation;
import com.ennea.enneaservices.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface QuotationRepository extends CrudRepository<Quotation, Long> {


    @Query("select quotation from Quotation quotation where quotation.supplier = :user and (:query is null or lower(quotation.productName) like CONCAT('%',lower(:query),'%')) order by quotation.creationDateTime desc")
    Page<Quotation> findAllBySupplierAndProductName(User user, String query, Pageable pageable);

    @Query("select distinct quotation from Quotation quotation join quotation.districts qd where qd.district.id = :id and quotation.showToDistributor = true and quotation.active = true and quotation.supplier <> :user and (:query is null or lower(quotation.productName) like CONCAT('%',lower(:query),'%')) order by quotation.creationDateTime desc")
    Page<Quotation> findAllByDistrictAndProductNameAndShowToDistributor(int id, User user, String query,
                                                                        Pageable pageable);

    @Query("select distinct quotation from Quotation quotation join quotation.districts qd where qd.district.id = :id and quotation.showToRetailer = true and quotation.active = true and quotation.supplier <> :user and (:query is null or lower(quotation.productName) like CONCAT('%',lower(:query),'%')) order by quotation.creationDateTime desc")
    Page<Quotation> findAllByDistrictAndProductNameAndShowToRetailer(int id, User user, String query,
                                                                     Pageable pageable);
}
