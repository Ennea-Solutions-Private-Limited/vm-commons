package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.FirebaseToken;
import com.ennea.enneaservices.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FirebaseTokenRepository extends CrudRepository<FirebaseToken, Long> {

    @Query("Select firebaseToken from FirebaseToken firebaseToken where firebaseToken.user = :user")
    List<FirebaseToken> findByUser(User user);

    @Query("Select firebaseToken from FirebaseToken firebaseToken where firebaseToken.token = :token")
    Optional<FirebaseToken> findByToken(String token);

}
