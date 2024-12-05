package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.model.UserSession;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserSessionRepository extends CrudRepository<UserSession, Long> {

    Optional<UserSession> findUserSessionBySessionId(String sessionId);

    @Query("select userSession from UserSession userSession where userSession.user= :user")
    List<UserSession> findByUser(User user);

    @Query("select userSession.sessionId from UserSession userSession where userSession.user = :user and userSession.phoneNumber = :number and userSession.sessionId != :sessionId")
    List<String> findAllSessionIdsByUserAndPhoneNumber(@Param("user") User user, @Param("number") Long phoneNumber,
                                                       @Param("sessionId") String sessionId);

    @Query("select userSession.sessionId from UserSession userSession where userSession.user = :user and userSession.sessionId != :sessionId")
    List<String> findAllSessionIdsByUser(@Param("user") User user, @Param("sessionId") String sessionId);

    @Query("SELECT us.phoneNumber AS phoneNumber, COUNT(us.id) AS totalSessions FROM UserSession AS us WHERE us.phoneNumber IN :numbers GROUP BY us.phoneNumber ORDER BY us.phoneNumber DESC")
    List<ISessionCount> countTotalIdsByPhoneNumberInterface(@Param("numbers") List<Long> numbers);

    interface ISessionCount {
        Long getPhoneNumber();

        Long getTotalSessions();
    }

}