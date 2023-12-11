package com.example.furniture.controllers;

import com.example.furniture.models.Furniture;
import com.example.furniture.models.Manufacturer;
import com.example.furniture.repositories.ManufacturerRepository;
import com.example.furniture.services.ManufacturerService;
import com.example.furniture.services.StaffService;
import com.example.furniture.util.ManufacturerValidator;
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
@RequestMapping("/manufacturer")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SELLER', 'ROLE_MANAGER')")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;
    private final ManufacturerValidator manufacturerValidator;
    private final StaffService staffService;

    @GetMapping()
    public String manufacturer(@RequestParam(name = "nameOfManufacturerForSearch", required = false) String nameOfManufacturer,
                               @RequestParam(name = "isSorted", required = false) boolean isSorted,
                               Model model, Principal principal) {
        model.addAttribute("manufacturers", manufacturerService.sortManufacturerByName(manufacturerService.listManufacturer(nameOfManufacturer), isSorted));
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        model.addAttribute("nameOfManufacturerForSearch", nameOfManufacturer);
        model.addAttribute("isSorted", isSorted);
        return "manufacturer-views/index";
    }

    @GetMapping("/{id_manufacturer}")
    public String manufacturerInfo(@PathVariable Long id_manufacturer, Model model, Principal principal) {
        model.addAttribute("manufacturer", manufacturerService.getManufacturerById(id_manufacturer));
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        return "manufacturer-views/info";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @GetMapping("/new")
    public String newManufacturer(@ModelAttribute("manufacturer") Manufacturer manufacturer) {
        return "manufacturer-views/new";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PostMapping("/create")
    public String createManufacturer(@Valid Manufacturer manufacturer, BindingResult bindingResult) {
        manufacturerValidator.validate(manufacturer, bindingResult);
        if(bindingResult.hasErrors()){
            return "manufacturer-views/new";
        }
        manufacturerService.saveManufacturer(manufacturer);
        return "redirect:/manufacturer";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PostMapping("/delete/{id_manufacturer}")
    public String deleteManufacturer(@PathVariable Long id_manufacturer) {
        manufacturerService.deleteManufacturer(id_manufacturer);
        return "redirect:/manufacturer";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @GetMapping("/{id_manufacturer}/edit")
    public String startEditManufacturer(@PathVariable(value = "id_manufacturer") Long id_manufacturer, Model model, Principal principal){
        model.addAttribute("manufacturer", manufacturerService.getManufacturerById(id_manufacturer));
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        return "manufacturer-views/edit";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PostMapping("/{id_manufacturer}/editInformation")
    public String editManufacturer(@PathVariable(value = "id_manufacturer") Long id_manufacturer, Model model, Principal principal,
                                   @Valid Manufacturer manufacturer, BindingResult bindingResult) {
        manufacturerValidator.validate(manufacturer, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("manufacturer", manufacturer);
            model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
            return "manufacturer-views/edit";
        }
        manufacturerService.editManufacturer(id_manufacturer, manufacturer);
        return "redirect:/manufacturer";
    }
}