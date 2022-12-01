package com.indocyber.jasindo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientGridDTO {
    private Long id;
    private String fullName;
    private String gender;
    private LocalDate birthDate;
    private String disease;
    private String contactPhone;
    private String email;
    private String address;
}
