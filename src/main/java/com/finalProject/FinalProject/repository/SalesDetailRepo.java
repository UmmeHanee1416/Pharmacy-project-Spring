package com.finalProject.FinalProject.repository;

import com.finalProject.FinalProject.dto.SalesDTO;
import com.finalProject.FinalProject.entity.SalesDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SalesDetailRepo extends JpaRepository<SalesDetail,Long> {



}
