package com.indocyber.jasindo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertPatientDTO {
    private Long id;
    @NotBlank
    private String firstName;
    private String lastName;
    @NotNull
    private String gender;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @NotBlank
    private String disease;
    private String contactPhone;
    @NotBlank
    private String email;
    private String address;
}
