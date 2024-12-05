package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.CreditProvider;
import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.model.UserCreditProvider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCreditProviderRepository extends CrudRepository<UserCreditProvider, Long> {

    @Query(value = "select distinct ucp.creditProvider from UserCreditProvider ucp where ucp.user = :user")
    List<CreditProvider> findAllCreditProvidersByUser(User user);

    boolean existsByUserAndCreditProvider(User user, CreditProvider creditProvider);

}
