package com.indocyber.jasindo.service;

import com.indocyber.jasindo.dto.InsertReservationDTO;
import com.indocyber.jasindo.dto.ScheduleGridDto;
import com.indocyber.jasindo.dto.UpsertScheduleDTO;
import com.indocyber.jasindo.dto.Utility.Dropdown;
import com.indocyber.jasindo.entity.Schedule;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getAllSchedule();

    List<Schedule> getSchedule(Integer page);

    UpsertScheduleDTO findScheduleByID(Long id);

    public Long saveSchedule(UpsertScheduleDTO dto);

    public void saveSchedule(UpsertScheduleDTO dto, Long id);

    void deleteSchedule(Long id);


    Schedule findById(Long scheduleId);

    Page<ScheduleGridDto> getScheduleGrid(Long doctorId, Integer page);

}
