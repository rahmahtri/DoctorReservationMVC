package com.indocyber.jasindo.restcontroller;

import com.indocyber.jasindo.dto.PatientGridDTO;
import com.indocyber.jasindo.dto.UpsertPatientDTO;
import com.indocyber.jasindo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientRestController {

    @Autowired
    private PatientService patientService;

    @GetMapping(value = {
            "/page={page}",
            "/name={fullName}"
    })
    public ResponseEntity<Object> get(@PathVariable(required = false) Integer page,
                                      @PathVariable(required = false) String fullName){
        page = (page == null) ? 1 : page;
        fullName = (fullName == null) ? "" : fullName;
        try {
            List<PatientGridDTO> patients = patientService.getPatientGrid(fullName, page);
            return ResponseEntity.status(HttpStatus.OK).body(patients);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

    @GetMapping(value ={
            "/pages",
            "/pages/name={fullName}"
    })
    public ResponseEntity<Object> getTotalPage(@PathVariable(required = false) String fullName){
        fullName = (fullName == null) ? "" : fullName;
        try {
            Long totalPages = patientService.getTotalPages(fullName);
            return ResponseEntity.status(HttpStatus.OK).body(totalPages);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(required = true) Long id){
        try {
            UpsertPatientDTO dto = patientService.findUpsertPatientById(id);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@Valid @RequestBody UpsertPatientDTO dto){
        try {
            patientService.savePatient(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Data Patient berhasil ditambahkan");
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

    @PutMapping
    public ResponseEntity<Object> put(@Valid @RequestBody UpsertPatientDTO dto){
        try {
            patientService.updateDataPatient(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Data Patient berhasil diupdate");
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Long id){
        try {
            patientService.deletePatient(id);
            return ResponseEntity.status(HttpStatus.OK).body("Data Patient berhasil dihapus");
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }
}
