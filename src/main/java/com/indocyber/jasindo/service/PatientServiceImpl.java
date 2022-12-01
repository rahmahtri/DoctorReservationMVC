package com.indocyber.jasindo.service;

import com.indocyber.jasindo.dto.PatientGridDTO;
import com.indocyber.jasindo.dto.UpsertPatientDTO;
import com.indocyber.jasindo.entity.Patient;
import com.indocyber.jasindo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    PatientRepository patientRepository;

    private final int rowsInPage = 2;

    @Override
    public List<PatientGridDTO> getPatientGrid(String fullName, Integer page) {
        Pageable pagination = PageRequest.of(page-1, rowsInPage, Sort.by("id"));
        List<PatientGridDTO> grid = patientRepository.findAll(fullName, pagination);
        return grid;
    }

    @Override
    public Long getTotalPages(String fullName) {
        double totalData = (double)(patientRepository.count(fullName));
        long totalPage = (long)(Math.ceil(totalData/rowsInPage));
        return totalPage;
    }

    @Override
    public UpsertPatientDTO findUpsertPatientById(Long id) {
        Optional<Patient> nullableEntity = patientRepository.findById(id);
        Patient entity = nullableEntity.get();

        UpsertPatientDTO dto = new UpsertPatientDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getGender(),
                entity.getBirthDate(),
                entity.getDisease(),
                entity.getContactPhone(),
                entity.getEmail(),
                entity.getAddress()
        );
        return dto;
    }

    @Override
    public Long savePatient(UpsertPatientDTO dto) {
        Patient entity = new Patient(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getGender(),
                dto.getBirthDate(),
                dto.getDisease(),
                dto.getContactPhone(),
                dto.getEmail(),
                dto.getAddress()
        );
        Patient respond = patientRepository.save(entity);
        return respond.getId();
    }

    @Override
    public void updateDataPatient(UpsertPatientDTO dto) {
        Patient entity = new Patient(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getGender(),
                dto.getBirthDate(),
                dto.getDisease(),
                dto.getContactPhone(),
                dto.getEmail(),
                dto.getAddress()
        );
        Patient respond = patientRepository.save(entity);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
