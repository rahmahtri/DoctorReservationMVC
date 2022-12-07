package com.indocyber.jasindo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertScheduleDTO {
    private Long id;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTreat;
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTreat;
    private Long doctorId;
    private Boolean isAvailable;
}
