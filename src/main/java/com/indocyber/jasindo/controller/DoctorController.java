package com.indocyber.jasindo.controller;

import com.indocyber.jasindo.dto.DoctorGridDto;
import com.indocyber.jasindo.dto.UpsertDoctorDTO;
import com.indocyber.jasindo.dto.UpsertPatientDTO;
import com.indocyber.jasindo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String fullName,
                        Model model){
        Page<DoctorGridDto> doctors = doctorService.getDoctorGrid(fullName, page);

        model.addAttribute("fullName", fullName);
        model.addAttribute("totalPages", doctors.getTotalPages());
        model.addAttribute("grid", doctors);
        model.addAttribute("currentPage", page);
        model.addAttribute("breadCrumbs", "Doctor Index");
        return "doctor/doctor-index";
    }

    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) Long id,
                             Model model){
        if(id!=null){
            UpsertDoctorDTO dto = doctorService.findDoctorById(id);
            model.addAttribute("doctor", dto);
            model.addAttribute("type", "update");
            model.addAttribute("breadCrumbs", "Update Data Doctor");
        } else {
            UpsertDoctorDTO dto = new UpsertDoctorDTO();
            model.addAttribute("doctor", dto);
            model.addAttribute("type", "insert");
            model.addAttribute("breadCrumbs", "Insert Data Doctor");
        }
        return "doctor/doctor-form";
    }

    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute("doctor") UpsertDoctorDTO dto,
                         BindingResult bindingResult,
                         Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("type", "insert");
            model.addAttribute("breadCrumbs", "Insert Data Doctor");
            return "doctor/doctor-form";
        } else {
            doctorService.saveDoctor(dto);
            return "redirect:/doctor/index";
        }
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("doctor") UpsertDoctorDTO dto,
                         BindingResult bindingResult,
                         Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("type", "update");
            model.addAttribute("breadCrumbs", "Update Data Doctor");
            return "doctor/doctor-form";
        } else {
            doctorService.updateDataDoctor(dto);
            return "redirect:/doctor/index";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) Long id){
        doctorService.deleteDoctor(id);
        return "redirect:/doctor/index";
    }
}
