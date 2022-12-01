package com.indocyber.jasindo.service;

import com.indocyber.jasindo.dto.PatientGridDTO;
import com.indocyber.jasindo.dto.UpsertPatientDTO;

import java.util.List;

public interface PatientService {
    List<PatientGridDTO> getPatientGrid(String fullName, Integer page);

    Long getTotalPages(String fullName);

    UpsertPatientDTO findUpsertPatientById(Long id);

    Long savePatient(UpsertPatientDTO dto);

    void updateDataPatient(UpsertPatientDTO dto);

    void deletePatient(Long id);
}
