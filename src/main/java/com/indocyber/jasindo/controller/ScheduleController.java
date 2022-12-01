package com.indocyber.jasindo.controller;

import com.indocyber.jasindo.dto.ScheduleGridDto;
import com.indocyber.jasindo.dto.UpsertScheduleDTO;
import com.indocyber.jasindo.entity.Doctor;
import com.indocyber.jasindo.service.DoctorService;
import com.indocyber.jasindo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(required = true) Long doctorId,
                        Model model){
        Page<ScheduleGridDto> grid = scheduleService.getScheduleGrid(doctorId, page);

        model.addAttribute("doctorId", doctorId);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", grid.getTotalPages());
        model.addAttribute("grid", grid);
        model.addAttribute("breadCrumbs", "Schedule Index");
        return "doctor/doctor-schedule";
    }

    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = true) Long doctorId,
                             @RequestParam(required = false) Long id,
                             Model model){
        Doctor doctor = doctorService.findById(doctorId);
        String doctorName = doctor.getFirstName().concat(" ").concat(doctor.getLastName());
        if(id!=null){
            UpsertScheduleDTO dto = scheduleService.findScheduleByID(id);
            model.addAttribute("schedule", dto);
            model.addAttribute("doctorName", doctorName);
            model.addAttribute("type", "update");
            model.addAttribute("breadCrumbs", "Update Schedule");
        } else {
            UpsertScheduleDTO dto = new UpsertScheduleDTO();
            model.addAttribute("schedule", dto);
            model.addAttribute("doctorName", doctorName);
            model.addAttribute("type", "insert");
            model.addAttribute("breadCrumbs", "Insert Schedule");
        }
        return "schedule/schedule-form";
    }

    @PostMapping("/insert")
    public String addSchedule(@ModelAttribute("schedule")UpsertScheduleDTO dto,
                              Model model){
        scheduleService.saveSchedule(dto);
        return "redirect:/schedule/index";
    }

    @PostMapping("/update")
    public String updateSchedule(@ModelAttribute("schedule")UpsertScheduleDTO dto,
                              Model model){
        scheduleService.saveSchedule(dto);
        return "redirect:/schedule/index";
    }



}
