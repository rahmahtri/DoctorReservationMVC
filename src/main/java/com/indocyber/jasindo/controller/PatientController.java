package com.indocyber.jasindo.controller;

import com.indocyber.jasindo.dto.PatientGridDTO;
import com.indocyber.jasindo.dto.UpsertPatientDTO;
import com.indocyber.jasindo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String fullName,
                        Model model){
        List<PatientGridDTO> patients = patientService.getPatientGrid(fullName, page);
        long totalPages = patientService.getTotalPages(fullName);

        model.addAttribute("fullName", fullName);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("grid", patients);
        model.addAttribute("currentPage", page);
        model.addAttribute("breadCrumbs", "Patient Index");
        return "patient/patient-index";
    }

    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) Long id,
                             Model model){
        if (id != null){
            UpsertPatientDTO dto = patientService.findUpsertPatientById(id);
            model.addAttribute("patient", dto);
            model.addAttribute("type", "update");
            model.addAttribute("breadCrumbs", "Update Data Patient");
        } else {
            UpsertPatientDTO dto = new UpsertPatientDTO();
            model.addAttribute("patient", dto);
            model.addAttribute("type", "insert");
            model.addAttribute("breadCrumbs", "Insert Data Patient");
        }
        return "patient/patient-form";
    }

    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute("patient") UpsertPatientDTO dto,
                         BindingResult bindingResult,
                         Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("type", "insert");
            model.addAttribute("breadCrumbs", "Insert Data Patient");
            return "patient/patient-form";
        } else {
            patientService.savePatient(dto);
            return "redirect:/patient/index";
        }
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("patient") UpsertPatientDTO dto,
                         BindingResult bindingResult,
                         Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("type", "update");
            model.addAttribute("breadCrumbs", "Update Data Patient");
            return "patient/patient-form";
        }
        patientService.updateDataPatient(dto);
        return "redirect:/patient/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) Long id){
        patientService.deletePatient(id);
        return "redirect:/patient/index";
    }
}
