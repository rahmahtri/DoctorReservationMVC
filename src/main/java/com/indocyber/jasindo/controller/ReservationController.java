package com.indocyber.jasindo.controller;

import com.indocyber.jasindo.dto.InsertReservationDTO;
import com.indocyber.jasindo.dto.ReservationGridDTO;
import com.indocyber.jasindo.dto.Utility.Dropdown;
import com.indocyber.jasindo.entity.Schedule;
import com.indocyber.jasindo.service.ReservationService;
import com.indocyber.jasindo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        Model model){
        Page<ReservationGridDTO> grid = reservationService.getReservationGrid(page);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", grid.getTotalPages());
        model.addAttribute("grid", grid);
        model.addAttribute("breadCrumbs", "Reservation Index");
        return "reservation/reservation-index";
    }

    @GetMapping("/reservationForm")
    public String getReservationForm(@RequestParam(required = true) Long doctorId,
                                     @RequestParam(required = true) Long scheduleId,
                                     Model model){
        List<Dropdown> patientDropdown = reservationService.getPatientDropdown();
        model.addAttribute("patientDropdown", patientDropdown);

        Schedule schedule = scheduleService.findById(scheduleId);
        String doctorName = schedule.getDoctor().getFirstName().concat(" ").concat(schedule.getDoctor().getLastName());

        InsertReservationDTO dto = new InsertReservationDTO();
        model.addAttribute("reservation", dto);
        model.addAttribute("scheduleId",scheduleId);
        model.addAttribute("doctorId", doctorId);
        model.addAttribute("date", schedule.getDate());
        model.addAttribute("startTreat", schedule.getStartTreat());
        model.addAttribute("endTreat", schedule.getEndTreat());
        model.addAttribute("doctorName", doctorName);
        model.addAttribute("type","insert");
        model.addAttribute("breadCrumbs", "Insert Reservation");
        return "reservation/reservation-form";
    }

    @PostMapping("/insert")
    public String bookedReservation(@ModelAttribute("dto") InsertReservationDTO dto,
                                    @RequestParam Long doctorId,
                                    @RequestParam Long scheduleId){
        reservationService.booked(dto, scheduleId);
        return "redirect:/schedule/index?doctorId="+doctorId;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) Long id){
        reservationService.deleteReservation(id);
        return "redirect:/reservation/index";
    }
}
