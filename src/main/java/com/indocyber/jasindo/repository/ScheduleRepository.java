package com.indocyber.jasindo.repository;

import com.indocyber.jasindo.dto.ScheduleGridDto;
import com.indocyber.jasindo.dto.Utility.Dropdown;
import com.indocyber.jasindo.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value = "Select * From Schedule as sdl Where sdl.isAvailable = 1", nativeQuery = true)
    List<Schedule> findAllSchedule(Pageable pagination);

    @Query(value = "Select * From Schedule as sdl Where sdl.isAvailable = 1", nativeQuery = true)
    List<Schedule> findAllSchedule();

    @Query("""
            Select new com.indocyber.jasindo.dto.ScheduleGridDto(sdl.id, sdl.date, sdl.startTreat, sdl.endTreat,
            concat(doc.firstName, ' ', doc.lastName), sdl.isAvailable)
            From Schedule as sdl
            Left Join sdl.doctor as doc
            Where doc.id = :doctorId
            And sdl.isAvailable = 1
            """)
    Page<ScheduleGridDto> getScheduleGrid(@Param("doctorId") Long doctorId,
                                          Pageable pagination);

//    @Query("""
//            Select new com.indocyber.jasindo.dto.Utility.Dropdown(pat.id, concat(pat.firstName, ' ', pat.lastName))
//            From Patient as pat
//            Order By pat.firstName
//            """)
//    List<Dropdown> findAllPatient();
}
