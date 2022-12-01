package com.indocyber.jasindo.restcontroller;

import com.indocyber.jasindo.dto.DoctorGridDto;
import com.indocyber.jasindo.dto.UpsertDoctorDTO;
import com.indocyber.jasindo.entity.Doctor;
import com.indocyber.jasindo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorRestController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping(value = {
            "/page={page}",
            "/name={fullName}"
    })
    public ResponseEntity<Object> get(@PathVariable(required = false) Integer page,
                                      @PathVariable(required = false) String fullName){
        page = (page == null) ? 1 : page;
        fullName = (fullName == null) ? "" : fullName;
        try {
            Page<DoctorGridDto> doctors = doctorService.getDoctorGrid(fullName, page);
            return ResponseEntity.status(HttpStatus.OK).body(doctors);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

    @GetMapping("/id={id}")
    public ResponseEntity<Object> findById(@PathVariable(required = true) Long id){
        try {
            UpsertDoctorDTO dto = doctorService.findDoctorById(id);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody UpsertDoctorDTO dto){
        try {
            doctorService.saveDoctor(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Data Doctor berhasil ditambahkan");
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

    @PutMapping
    public ResponseEntity<Object> put(@RequestBody UpsertDoctorDTO dto){
        try {
            doctorService.updateDataDoctor(dto);
            return ResponseEntity.status(HttpStatus.OK).body("Data Doctor berhasil diupdate");
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Long id){
        try {
            doctorService.deleteDoctor(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Data Patient berhasil dihapus");
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }


}
