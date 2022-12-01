package com.indocyber.jasindo.repository;

import com.indocyber.jasindo.dto.PatientGridDTO;
import com.indocyber.jasindo.entity.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("""
            Select new com.indocyber.jasindo.dto.PatientGridDTO(pat.id, Concat(pat.firstName, ' ', pat.lastName),
            pat.gender, pat.birthDate, pat.disease, pat.contactPhone, pat.email, pat.address)
            From Patient as pat
            Where Concat(pat.firstName, ' ', pat.lastName) like %:fullName%
            """)
    List<PatientGridDTO> findAll(@Param("fullName") String fullName, Pageable pagination);

    @Query("""
            Select Count(concat(pat.firstName, ' ', pat.lastName))
            From Patient as pat
            Where concat(pat.firstName, ' ', pat.lastName) like %:fullName% 
            """)
    public Long count(@Param("fullName") String fullName);
}
