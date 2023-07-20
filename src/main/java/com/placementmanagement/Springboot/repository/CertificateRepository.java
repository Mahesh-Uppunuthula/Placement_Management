package com.placementmanagement.Springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.placementmanagement.Springboot.entity.Certificate;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer>{

}
