package com.indocyber.jasindo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertDoctorDTO {
    private Long id;
    @NotBlank
    private String firstName;
    private String lastName;
    @NotBlank
    private String degree;
    private String contactPhone;
    @NotBlank
    private String email;
    private String address;
}
