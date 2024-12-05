package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRatingRepository extends JpaRepository<UserRating, Long> {
}