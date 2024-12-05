package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.ErpCredentials;
import com.ennea.enneaservices.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ErpCredentialsRepository extends CrudRepository<ErpCredentials, Long> {

    Optional<ErpCredentials> findByUser(User user);

    Optional<ErpCredentials> findByExternalKey(String key);

    @Query("select erpCredentials from ErpCredentials erpCredentials where erpCredentials.user = :user "
           + "and erpCredentials.erp.name = :erp")
    Optional<ErpCredentials> findByUserAndErp(User user, String erp);

    @Query("select erpCredentials from ErpCredentials erpCredentials where erpCredentials.user.id = :userId "
           + "and erpCredentials.erp.name = :erp")
    Optional<ErpCredentials> findByUserIdAndErp(Long userId, String erp);

    @Query("select erpCredentials from ErpCredentials erpCredentials where erpCredentials.user in (:users) "
           + "and erpCredentials.erp.name = :erp")
    List<ErpCredentials> findByUsersAndErp(@Param("users") List<User> users, @Param("erp") String erp);

    @Query(
        "select erpCredentials from ErpCredentials erpCredentials where erpCredentials.user.businessName = :businessName "
        + "and erpCredentials.systemUUID = :uuid")
    Optional<ErpCredentials> findByUUIDAndBusinessName(@Param("uuid") String uuid,
                                                       @Param("businessName") String businessName);

    @Query(
        "select erpCredentials from ErpCredentials erpCredentials where erpCredentials.user.settings.manualInventoryUpload = false "
        +
        "and erpCredentials.erp.name = :erp")
    List<ErpCredentials> findByErpAndManualInventoryUploadDisabled(@Param("erp") String erp);

    @Query("select erpCredentials from ErpCredentials erpCredentials where erpCredentials.erp.name = :erp")
    List<ErpCredentials> findByERP(String erp);

    @Query("select erpCredentails.user from ErpCredentials erpCredentails where erpCredentails.erp.name not in :erp and erpCredentails.user.id in :userIds")
    List<User> findByUsersNotInErps(Set<Long> userIds, Set<String> erp);
}