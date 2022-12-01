package com.indocyber.jasindo.repository;

import com.indocyber.jasindo.dto.DoctorGridDto;
import com.indocyber.jasindo.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("""
            Select new com.indocyber.jasindo.dto.DoctorGridDto(doc.id, Concat(doc.firstName, ' ', doc.lastName), doc.degree,
            doc.contactPhone, doc.email, doc.address)
            From Doctor as doc
            Where Concat(doc.firstName, ' ', doc.lastName) like %:fullName%
            """)
    Page<DoctorGridDto> findAllDoctor(@Param("fullName") String fullName,
                                      Pageable pagination);
}
