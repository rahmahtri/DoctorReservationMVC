package com.indocyber.jasindo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleGridDto {
    private Long id;
    private LocalDate date;
    private LocalTime startTreat;
    private LocalTime endTreat;
    private String doctorName;
    private boolean isAvailable;
}
