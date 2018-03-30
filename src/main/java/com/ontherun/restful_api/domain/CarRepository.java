package com.ontherun.restful_api.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT c from Car c WHERE c.user.id = :id")
    Car findByUserId(@Param("id") Long id);
}
