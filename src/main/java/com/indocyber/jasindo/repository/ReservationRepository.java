package com.indocyber.jasindo.repository;

import com.indocyber.jasindo.dto.ReservationGridDTO;
import com.indocyber.jasindo.dto.Utility.Dropdown;
import com.indocyber.jasindo.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "Select * From Reservation as res", nativeQuery = true)
    List<Reservation> findReservation(Pageable pagination);

    @Query("""
            Select new com.indocyber.jasindo.dto.ReservationGridDTO(res.id, concat(pat.firstName, ' ', pat.lastName), 
            sdl.date, sdl.startTreat, sdl.endTreat, concat(doc.firstName, ' ', doc.lastName), res.createdAt)
            From Reservation as res
            Left Join res.patient as pat
            Left Join res.schedule as sdl
            Left Join sdl.doctor as doc
            """)
    Page<ReservationGridDTO> findAllReservation(Pageable pagination);

    @Query("""
            Select new com.indocyber.jasindo.dto.Utility.Dropdown(pat.id, concat(pat.firstName, ' ', pat.lastName))
            From Patient as pat
            Order By pat.firstName
            """)
    List<Dropdown> findAllPatient();
}
