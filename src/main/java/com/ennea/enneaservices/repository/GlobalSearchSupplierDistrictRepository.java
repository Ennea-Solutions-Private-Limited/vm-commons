package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.GlobalSearchSupplierDistrict;
import com.ennea.enneaservices.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GlobalSearchSupplierDistrictRepository extends CrudRepository<GlobalSearchSupplierDistrict, Long> {

    @Query(value = "select distinct sd.supplier from GlobalSearchSupplierDistrict sd where sd.district.id = :districtId and sd.supplier.settings.globalSearch = true")
    List<User> searchSuppliersByDistrictAndSupplierGlobalSearchSetting(int districtId);


}
