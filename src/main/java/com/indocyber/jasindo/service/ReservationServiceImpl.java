package com.indocyber.jasindo.service;

import com.indocyber.jasindo.dto.InsertReservationDTO;
import com.indocyber.jasindo.dto.ReservationGridDTO;
import com.indocyber.jasindo.dto.UpsertReservationDTO;
import com.indocyber.jasindo.dto.Utility.Dropdown;
import com.indocyber.jasindo.entity.Patient;
import com.indocyber.jasindo.entity.Reservation;
import com.indocyber.jasindo.entity.Schedule;
import com.indocyber.jasindo.repository.PatientRepository;
import com.indocyber.jasindo.repository.ReservationRepository;
import com.indocyber.jasindo.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    private final int rowsInPage = 2;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservation(Integer page) {
        Pageable pagination = PageRequest.of(page-1, rowsInPage, Sort.by("Id"));
        List<Reservation> reservations = reservationRepository.findReservation(pagination);
        return reservations;
    }

    @Override
    public Reservation findReservationById(Long id) {
        Optional<Reservation> findReservation = reservationRepository.findById(id);
        Reservation reservation = findReservation.get();
        reservation.getId();
        reservation.getPatient();
        reservation.getSchedule();
        reservation.getCreatedAt();
        return reservation;
    }

    @Override
    public Long saveReservation(UpsertReservationDTO dto) {
        Optional<Patient> findPatient = patientRepository.findById(dto.getPatientId());
        Patient patient = findPatient.get();

        Optional<Schedule> findSchedule = scheduleRepository.findById(dto.getScheduleId());
        Schedule schedule = findSchedule.get();
        schedule.setIsAvailable(false);

        LocalDate createdAt = LocalDate.now();

        Reservation entity = new Reservation(
                dto.getId(),
                patient,
                schedule,
                createdAt
        );

        Reservation respond = reservationRepository.save(entity);
        return respond.getId();
    }

    @Override
    public void saveReservation(UpsertReservationDTO dto, Long id) {
        Optional<Patient> findPatient = patientRepository.findById(dto.getPatientId());
        Patient patient = findPatient.get();

        Optional<Schedule> findSchedule = scheduleRepository.findById(dto.getScheduleId());
        Schedule schedule = findSchedule.get();
        schedule.setIsAvailable(false);

        LocalDate createdAt = LocalDate.now();

        Optional<Reservation> findReservation = reservationRepository.findById(id);
        Reservation reservation = findReservation.get();

        reservation.setId(id);
        reservation.setPatient(patient);
        reservation.setSchedule(schedule);
        reservation.setCreatedAt(createdAt);

        reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {

        Optional<Reservation> findReservation = reservationRepository.findById(id);
        Reservation reservation = findReservation.get();

        Optional<Schedule> findSchedule = scheduleRepository.findById(reservation.getSchedule().getId());
        Schedule schedule = findSchedule.get();
        schedule.setIsAvailable(true);

        reservationRepository.deleteById(id);
    }

    @Override
    public Page<ReservationGridDTO> getReservationGrid(Integer page) {
        Pageable pagination = PageRequest.of(page-1, rowsInPage, Sort.by("id"));
        Page<ReservationGridDTO> grid = reservationRepository.findAllReservation(pagination);
        return grid;
    }

    @Override
    public List<Dropdown> getPatientDropdown() {
        List<Dropdown> dropdowns = reservationRepository.findAllPatient();
        return dropdowns;
    }

    @Override
    public void booked(InsertReservationDTO dto, Long scheduleId) {
        Optional<Schedule> findSchedule = scheduleRepository.findById(scheduleId);
        Schedule tempSchedule = null;
        if(findSchedule.isPresent()){
            tempSchedule = findSchedule.get();
        }
        tempSchedule.setIsAvailable(false);

        Optional<Patient> findPatient = patientRepository.findById(dto.getPatientId());
        Patient tempPatient = null;
        if(findPatient.isPresent()){
            tempPatient = findPatient.get();
        }

        Reservation reservation = new Reservation(
                dto.getId(),
                tempPatient,
                tempSchedule,
                LocalDate.now()
        );

        scheduleRepository.save(tempSchedule);
        reservationRepository.save(reservation);
    }

}
