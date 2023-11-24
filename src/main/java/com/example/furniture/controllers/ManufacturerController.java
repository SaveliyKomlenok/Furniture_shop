package com.example.furniture.controllers;

import com.example.furniture.models.Furniture;
import com.example.furniture.models.Manufacturer;
import com.example.furniture.repositories.ManufacturerRepository;
import com.example.furniture.services.ManufacturerService;
import com.example.furniture.services.StaffService;
import com.example.furniture.util.ManufacturerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manufacturer")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;
    private final ManufacturerValidator manufacturerValidator;
    private final StaffService staffService;

    @GetMapping()
    public String manufacturer(@RequestParam(name = "nameOfManufacturerForSearch", required = false, defaultValue = "") String nameOfManufacturer, Model model, Principal principal) {
        model.addAttribute("manufacturers", manufacturerService.listManufacturer(nameOfManufacturer));
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        model.addAttribute("nameOfManufacturerForSearch", nameOfManufacturer);
        return "manufacturer-views/index";
    }

    @GetMapping("/{id_manufacturer}")
    public String manufacturerInfo(@PathVariable Long id_manufacturer, Model model, Principal principal) {
        model.addAttribute("manufacturer", manufacturerService.getManufacturerById(id_manufacturer));
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        return "manufacturer-views/info";
    }

    @GetMapping("/new")
    public String newManufacturer(@ModelAttribute("manufacturer") Manufacturer manufacturer) {
        return "manufacturer-views/new";
    }

    @PostMapping("/create")
    public String createManufacturer(@Valid Manufacturer manufacturer, BindingResult bindingResult) {
        manufacturerValidator.validate(manufacturer, bindingResult);
        if(bindingResult.hasErrors()){
            return "manufacturer-views/new";
        }
        manufacturerService.saveManufacturer(manufacturer);
        return "redirect:/manufacturer";
    }

    @PostMapping("/delete/{id_manufacturer}")
    public String deleteManufacturer(@PathVariable Long id_manufacturer) {
        manufacturerService.deleteManufacturer(id_manufacturer);
        return "redirect:/manufacturer";
    }

    @GetMapping("/{id_manufacturer}/edit")
    public String startEditManufacturer(@PathVariable(value = "id_manufacturer") Long id_manufacturer, Model model, Principal principal){
        model.addAttribute("manufacturer", manufacturerService.getManufacturerById(id_manufacturer));
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        return "manufacturer-views/edit";
    }

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