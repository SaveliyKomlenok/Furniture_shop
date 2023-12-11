package com.example.furniture.controllers;

import com.example.furniture.models.Staff;
import com.example.furniture.enums.JobTitle;
import com.example.furniture.services.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminController {

    private final StaffService staffService;

    @GetMapping("/{id_staff}")
    public String admin(@PathVariable Long id_staff, Model model, Principal principal){
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        model.addAttribute("staff", staffService.getStaffById(id_staff));
        return "admin-views/admin";
    }

    @GetMapping("/hire")
    public String hireStaff(@RequestParam(name = "fullNameForSearch", required = false) String fullName, Model model,
                            @ModelAttribute("staff") Staff staff) {
        model.addAttribute("staffs", staffService.listStaff(fullName));
        model.addAttribute("allStaffs", staffService.listStaff(null));
        model.addAttribute("fullNameForSearch", fullName);
        return "admin-views/hire";
    }

    @PostMapping("/staff/hire")
    public String hireNewStaff(@RequestParam("idStaff") Staff staff){
        staffService.changeJobTitleOfUser(staff);
        return "redirect:/admin/hire";
    }

    @PostMapping("/staff/ban/{id_staff}")
    public String staffBan(@PathVariable (value = "id_staff") Long id_staff){
        staffService.banStaff(id_staff);
        return "redirect:/admin/{id_staff}";
    }

    @GetMapping("/staff/edit/{id_staff}")
    public String staffEdit(@PathVariable Long id_staff, Model model, @ModelAttribute("staff") Staff staff){
        model.addAttribute("staff", staffService.getStaffById(id_staff));
        model.addAttribute("jobTitles", JobTitle.values());
        return "staff-views/edit";
    }

    @PostMapping("/staff/edit")
    public String staffEdit(@RequestParam("idStaff") Staff staff, @RequestParam Map<String, String> form){
        staffService.changeJobTitleOfStaff(staff, form);
        return "redirect:/staff";
    }
}