package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.District;
import com.ennea.enneaservices.model.RoleConfiguration;
import com.ennea.enneaservices.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select distinct user from User user where (upper(user.contactPerson) like CONCAT('%',upper(:name),'%') or "
           + "upper(replace(replace(replace(user.businessName, ' ', ''),'/',''),'.','')) like CONCAT('%',upper(:name),'%') or "
           + "upper(user.address.city) like CONCAT('%',upper(:name),'%') "
           + "or CAST(user.uuid as string ) like CONCAT('%',upper(:name),'%')) "
           + " and user.roleConfiguration.role.name in (:userRoles) and user.id != :customerId and user.id not in "
           + "(select sc.supplier.id from SupplierCustomer sc where sc.customer.id = :customerId) "
           + " and user.userStatus.status = :status and user.settings.usingSaleMode = true")
    Page<User> findDistinctUserByBusinessNameOrCityAndUserRoleAndUuid(@Param("name") String name,
                                                                      @Param("userRoles") List<String> userRoles,
                                                                      @Param("customerId") Long customerId,
                                                                      @Param("status") String status,
                                                                      Pageable pageable);

    @Query("select distinct user from User user where user.address.district = :district"
           + " and user.roleConfiguration.role.name in (:userRoles) and user.id != :customerId and user.id not in "
           + "(select sc.supplier.id from SupplierCustomer sc where sc.customer.id = :customerId) "
           + " and (FUNCTION('DATEDIFF', user.creationDateTime, CURRENT_DATE) > -720) and user.settings.usingSaleMode = true")
    Page<User> findNewDistinctUserByDistrictAndUserRoleAndStatus(@Param("district") District district,
                                                                 @Param("userRoles") List<String> userRoles,
                                                                 @Param("customerId") Long customerId,
                                                                 Pageable pageable);


    @Query("select user from User user where user.phoneNumber = :phoneNumber and user.userStatus.status = :status")
    Optional<User> findUserByPhoneNumberAndStatus(long phoneNumber, String status);

    Optional<User> findUserByUsername(String username);

    Boolean existsByUsername(String username);

    @Query("select u from User u where u.id in (:userIds)")
    List<User> findAllUsers(@Param("userIds") List<Long> userIds);

    Boolean existsByDrugLicense(String drugLicense);

    Boolean existsByPhoneNumber(Long phoneNumber);

    @Query(value = "select u.username from user u where (cast(u.username as BINARY) REGEXP BINARY :regex) order by u.username desc limit 1",
        nativeQuery = true)
    Optional<String> findUserByUsernameRegex(@Param("regex") String regex);

    @Query("select user from User user where user.settings.automaticInvoicing = :status")
    List<User> findUsersWithAutomaticInvoicingStatus(@Param("status") boolean status);

    @Query("select distinct user from User user where user.roleConfiguration.role.name = :role " +
           "and user.settings.automaticInvoicing = :status")
    List<User> findUsersWithAutomaticInvoicingStatusAndRole(@Param("status") boolean status,
                                                            @Param("role") String role);

    @Query(
        "select u from User u where (u.roleConfiguration.role.name not in (:roles) or u.roleConfiguration.configuration.name in :roleConfigurations) and (:statusId = 0 or u.userStatus.id = :statusId) "
        + "and (:query is null or lower(u.businessName) like CONCAT('%',lower(:query),'%') "
        + "or lower(u.drugLicense) like CONCAT('%',lower(:query),'%') or lower(concat(u.phoneNumber,'')) like CONCAT('%',lower(:query),'%') "
        + "or lower(concat(u.uuid,'')) like CONCAT('%',lower(:query),'%') or lower(u.username) like CONCAT('%',lower(:query),'%')) order by u.id desc")
    Page<User> findAllUserByNotInRolesAndRoleConfigurationsAndStatusAndSearchQuery(List<String> roles,
                                                                                   List<String> roleConfigurations,
                                                                                   int statusId, String query,
                                                                                   Pageable pageable);

    @Query("select distinct user from User user where user.roleConfiguration in (:roleConfigurations) and user.userStatus.status = :status and (:query is null or (lower(user.businessName) like CONCAT(lower(:query),'%')) or (lower(concat(user.uuid,'')) like CONCAT(lower(:query),'%')))")
    List<User> findAllUsersWithRolesAndStatusAndQuery(List<RoleConfiguration> roleConfigurations, String status,
                                                      String query);

    @Query("select user from User user where user.phoneNumber = :phoneNumber")
    Optional<User> findUserByPhoneNumber(long phoneNumber);

    @EntityGraph(attributePaths = {"roleConfiguration.role", "address.district", "userStatus.id"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select user from User user where lower(user.roleConfiguration.role.name) = lower(:role) and  user.address.district.id in :districtIds and user.userStatus.id =:statusId")
    List<User> findAllUsersByRoleAndDistrict(@Param("role") String role,
                                             @Param("districtIds") List<Integer> districtIds,
                                             @Param("statusId") int statusId);

    @Query("select distinct(u.phoneNumber) from User u where u.phoneNumber in :phoneNumbers")
    Set<Long> findAllDuplicatePhoneNumbers(Set<Long> phoneNumbers);

    @Query("select distinct user from User user where user.id in (:userIds) and user.roleConfiguration in (:roleConfigurations) and user.userStatus.status = :status ")
    List<User> findAllUsersWithIdsAndRolesAndStatus(@Param("userIds") List<Long> userIds,
                                                    @Param("roleConfigurations") List<RoleConfiguration> roleConfigurations,
                                                    @Param("status") String status);

    @Query("select user from User user where user.roleConfiguration.role.name = :roleRetailer and user.address.district.id in :districtIds")
    List<User> findAllUserWithRoleAndDistrict(String roleRetailer, Set<Integer> districtIds);
}
