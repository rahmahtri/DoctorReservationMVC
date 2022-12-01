package com.indocyber.jasindo.service;

import com.indocyber.jasindo.dto.DoctorGridDto;
import com.indocyber.jasindo.dto.UpsertDoctorDTO;
import com.indocyber.jasindo.entity.Doctor;
import com.indocyber.jasindo.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorRepository doctorRepository;

    private final int rowsInPage = 2;

    @Override
    public Page<DoctorGridDto> getDoctorGrid(String fullName, Integer page) {
        Pageable pagination = PageRequest.of(page-1, rowsInPage, Sort.by("id"));
        Page<DoctorGridDto> doctors = doctorRepository.findAllDoctor(fullName, pagination);
        return doctors;
    }

    @Override
    public UpsertDoctorDTO findDoctorById(Long id) {
        Optional<Doctor> nullableEntity = doctorRepository.findById(id);
        Doctor entity = nullableEntity.get();
        UpsertDoctorDTO doctorDTO = new UpsertDoctorDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDegree(),
                entity.getContactPhone(),
                entity.getEmail(),
                entity.getAddress()
        );
        return doctorDTO;
    }

    @Override
    public Long saveDoctor(UpsertDoctorDTO dto) {
        Doctor entity = new Doctor(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getDegree(),
                dto.getContactPhone(),
                dto.getEmail(),
                dto.getAddress()
        );

        Doctor respond = doctorRepository.save(entity);
        return respond.getId();
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public void updateDataDoctor(UpsertDoctorDTO dto) {
        Doctor entity = new Doctor(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getDegree(),
                dto.getContactPhone(),
                dto.getEmail(),
                dto.getAddress()
        );
        Doctor respond = doctorRepository.save(entity);
    }

    @Override
    public Doctor findById(Long doctorId) {
        Optional<Doctor> findDoctor = doctorRepository.findById(doctorId);
        Doctor doctor = null;
        if(findDoctor.isPresent()){
            doctor = findDoctor.get();
        }
        return doctor;
    }
}
