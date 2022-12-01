package com.indocyber.jasindo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorGridDto {
    private Long id;
    private String fullName;
    private String degree;
    private String contactPhone;
    private String email;
    private String address;
}
