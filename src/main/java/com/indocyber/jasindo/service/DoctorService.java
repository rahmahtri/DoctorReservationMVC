package com.indocyber.jasindo.service;

import com.indocyber.jasindo.dto.DoctorGridDto;
import com.indocyber.jasindo.dto.UpsertDoctorDTO;
import com.indocyber.jasindo.entity.Doctor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DoctorService {
    Page<DoctorGridDto> getDoctorGrid(String fullName, Integer page);

    UpsertDoctorDTO findDoctorById(Long id);

    public Long saveDoctor(UpsertDoctorDTO dto);

    public void deleteDoctor(Long id);

    void updateDataDoctor(UpsertDoctorDTO dto);

    Doctor findById(Long doctorId);
}
