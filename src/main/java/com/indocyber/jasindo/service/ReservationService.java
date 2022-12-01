package com.indocyber.jasindo.service;

import com.indocyber.jasindo.dto.InsertReservationDTO;
import com.indocyber.jasindo.dto.ReservationGridDTO;
import com.indocyber.jasindo.dto.UpsertReservationDTO;
import com.indocyber.jasindo.dto.Utility.Dropdown;
import com.indocyber.jasindo.entity.Reservation;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations();

    List<Reservation> getReservation(Integer page);

    Reservation findReservationById(Long id);

    public Long saveReservation(UpsertReservationDTO dto);

    public void saveReservation(UpsertReservationDTO dto, Long id);

    public void deleteReservation(Long id);

    Page<ReservationGridDTO> getReservationGrid(Integer page);

    List<Dropdown> getPatientDropdown();

    void booked(InsertReservationDTO dto, Long scheduleId);
}
