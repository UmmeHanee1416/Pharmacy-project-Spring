package com.finalProject.FinalProject.repository;

import com.finalProject.FinalProject.entity.Generic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenericRepo extends JpaRepository<Generic, Long> {
}
