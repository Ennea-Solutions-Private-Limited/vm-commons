package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.model.UserGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {

    @Query("select userGroup from UserGroup userGroup where userGroup.owner = :owner and userGroup.id = :id")
    Optional<UserGroup> findUserGroupByIdAndOwner(@Param("id") long id, @Param("owner") User owner);

    @Query(
        "select userGroup from UserGroup userGroup where userGroup.owner.roleConfiguration.configuration.name = :configuration and "
        + " (:query is null or lower(userGroup.name) like CONCAT('%',lower(:query),'%'))")
    Page<UserGroup> findAllUserGroupsByOwnerConfiguration(String configuration, String query, Pageable pageable);

    List<UserGroup> findAllUserGroupsByOwner(User owner);

    @Query(nativeQuery = true, value = "select distinct customer_id from user_group_members where user_group_id in (select id from user_group ug where ug.owner_id = :ownerId);")
    Set<Long> getAllCustomerIdsByOwnerId(long ownerId);

    @Query("select userGroup from UserGroup userGroup where userGroup.owner = :owner and "
           + " (:query is null or lower(userGroup.name) like CONCAT('%',lower(:query),'%'))")
    Page<UserGroup> findAllUserGroupByOwnerAndGroupName(@Param("owner") User owner, @Param("query") String query,
                                                        Pageable pageable);

    @Query("select userGroup from UserGroup userGroup where userGroup.owner = :owner and "
           + " (:query is null or lower(userGroup.name) like CONCAT('%',lower(:query),'%'))")
    List<UserGroup> findAllUserGroupByOwnerAndGroupName(@Param("owner") User owner, @Param("query") String query);

}
