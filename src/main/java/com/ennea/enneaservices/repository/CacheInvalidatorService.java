package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CacheInvalidatorService {

    final Logger logger = LoggerFactory.getLogger(CacheInvalidatorService.class);

    private final UserSessionRepository userSessionRepository;

    @Autowired
    public CacheInvalidatorService(UserSessionRepository userSessionRepository) {
        this.userSessionRepository = userSessionRepository;
    }

    @CacheEvict(value = "UserSession", key = "#userSession.sessionId")
    public void deleteSession(UserSession userSession) {
        logger.info("Invalidated user session for user : {}", userSession.getUser().getId());
        userSessionRepository.delete(userSession);
    }

    @CacheEvict(value = "UserSession", key = "#sessionId")
    public void deleteSessionWithSessionId(String sessionId) {
        Optional<UserSession> userSession = userSessionRepository.findUserSessionBySessionId(sessionId);
        if(userSession.isPresent()){
            logger.info("Invalidated user session for user : {}", userSession.get().getUser().getId());
            userSessionRepository.delete(userSession.get());
        }
    }

    @CacheEvict(value = "UserSession", key = "#sessionId")
    public void evictUserSession(String sessionId) {
        logger.info("Invalidated user Session for session : {}", sessionId);
    }


    @CacheEvict(value = "UserDetailsImpl", key = "#username")
    public void evictUserDetails(String username) {
        logger.info("Invalidated user details for user : {}", username);
    }

}
