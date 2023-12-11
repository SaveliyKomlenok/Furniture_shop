package com.example.furniture.controllers;

import com.example.furniture.models.Staff;
import com.example.furniture.services.StaffService;
import com.example.furniture.util.StaffValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;
    private final StaffValidator staffValidator;

    public Staff staff;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/staff")
    public String staff(@RequestParam(name = "fullNameForSearch", required = false) String fullName,
                        @RequestParam(name = "isSorted", required = false) boolean isSorted,
                        Model model, @ModelAttribute("staff") Staff staff) {
        model.addAttribute("staffs", staffService.sortStaffByFullName(staffService.listStaff(fullName), isSorted));
        model.addAttribute("fullNameForSearch", fullName);
        model.addAttribute("isSorted", isSorted);
        return "staff-views/index";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/staff/{id_staff}")
    public String staffInfo(@PathVariable Long id_staff, Model model) {
        model.addAttribute("staff", staffService.getStaffById(id_staff));
        return "staff-views/info";
    }

    @GetMapping("/personalArea")
    public String personalArea(Model model, Principal principal) {
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        return "staff-views/personal-area";
    }

    @GetMapping("/staff/startOwnEdit")
    public String startEditOwnInformation(Model model, Principal principal){
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        return "staff-views/own-edit";
    }

    @PostMapping("/staff/ownEdit")
    public String editOwnInformation(@ModelAttribute("staff") @Valid Staff staff, BindingResult bindingResult, Principal principal) {
        staffValidator.validate(staff, bindingResult);
        if(bindingResult.hasErrors()){
            return "staff-views/own-edit";
        }
        staffService.editStaff(staffService.getStaffByPrincipal(principal).getIdStaff(), staff);
        return "redirect:/login";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/staff/delete/{id_staff}")
    public String deleteManufacturer(@PathVariable Long id_staff) {
        staffService.deleteStaff(id_staff);
        return "redirect:/staff";
    }

    @GetMapping("/")
    public String mainPage(Model model, Principal principal){
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        return "authorization-views/main";
    }

    @GetMapping("/login")
    public String login(){
        return "authorization-views/login";
    }

    @GetMapping("/login-error")
    public String checkLogin(){
        return "authorization-views/login-error";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("staff") Staff staff){
        return "authorization-views/registration";
    }

    @PostMapping("/registration")
    public String createStaff(@ModelAttribute("staff") @Valid Staff staff, BindingResult bindingResult){
        staffValidator.validate(staff, bindingResult);
        if(bindingResult.hasErrors()){
            return "authorization-views/registration";
        }
        staffService.createStaff(staff);
        return "redirect:/login";
    }
}