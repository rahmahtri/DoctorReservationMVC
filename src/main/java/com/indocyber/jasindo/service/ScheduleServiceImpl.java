package com.indocyber.jasindo.service;

import com.indocyber.jasindo.dto.ScheduleGridDto;
import com.indocyber.jasindo.dto.UpsertScheduleDTO;
import com.indocyber.jasindo.entity.Doctor;
import com.indocyber.jasindo.entity.Schedule;
import com.indocyber.jasindo.repository.DoctorRepository;
import com.indocyber.jasindo.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    private final int rowsInPage = 2;


    @Override
    public List<Schedule> getAllSchedule() {
        List<Schedule> findAllSchedule = scheduleRepository.findAllSchedule();
        return findAllSchedule;
    }

    @Override
    public List<Schedule> getSchedule(Integer page) {
        Pageable pagination = PageRequest.of(page-1, rowsInPage, Sort.by("Id"));
        List<Schedule> schedules = scheduleRepository.findAllSchedule(pagination);
        return schedules;
    }

    @Override
    public UpsertScheduleDTO findScheduleByID(Long id) {
        Optional<Schedule> findSchedule = scheduleRepository.findById(id);
        Schedule entity = findSchedule.get();
        UpsertScheduleDTO scheduleDTO = new UpsertScheduleDTO(
                entity.getId(),
                entity.getDate(),
                entity.getStartTreat(),
                entity.getEndTreat(),
                entity.getDoctor().getId(),
                entity.getIsAvailable()
        );
        return scheduleDTO;
    }

    @Override
    public Long saveSchedule(UpsertScheduleDTO dto) {
        Optional<Doctor> findDoctor = doctorRepository.findById(dto.getDoctorId());
        Doctor doctor = findDoctor.get();

        Schedule entity = new Schedule(
                dto.getId(),
                dto.getDate(),
                dto.getStartTreat(),
                dto.getEndTreat(),
                doctor,
                true
        );

        Schedule respond = scheduleRepository.save(entity);
        return respond.getId();
    }

    @Override
    public void saveSchedule(UpsertScheduleDTO dto, Long doctorId) {
        Optional<Doctor> findDoctor = doctorRepository.findById(doctorId);
        Doctor doctor = findDoctor.get();

        Schedule schedule = new Schedule(
                dto.getId(),
                dto.getDate(),
                dto.getStartTreat(),
                dto.getEndTreat(),
                doctor,
                true
        );

        scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public Schedule findById(Long scheduleId) {
        Optional<Schedule> findSchedule = scheduleRepository.findById(scheduleId);
        Schedule schedule = findSchedule.get();
        return schedule;
    }

    @Override
    public Page<ScheduleGridDto> getScheduleGrid(Long doctorId, Integer page) {
        Pageable pagination = PageRequest.of(page-1, rowsInPage, Sort.by("id"));
        Page<ScheduleGridDto> grid = scheduleRepository.getScheduleGrid(doctorId, pagination);
        return grid;
    }

}
