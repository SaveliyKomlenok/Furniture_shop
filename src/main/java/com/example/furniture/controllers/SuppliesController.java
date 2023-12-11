package com.example.furniture.controllers;


import com.example.furniture.models.Furniture;
import com.example.furniture.models.Supplies;
import com.example.furniture.services.FurnitureService;
import com.example.furniture.services.StaffService;
import com.example.furniture.services.SuppliesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/supplies")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
public class SuppliesController {
    private final StaffController staffController;

    private final SuppliesService suppliesService;
    private final FurnitureService furnitureService;
    private final StaffService staffService;

    Supplies newSupply;
    @GetMapping()
    public String supply(@RequestParam(name = "dateOfSupplies", required = false) String  dateOfSupplies,
                         @RequestParam(name = "isSorted", required = false) boolean isSorted,
                         Model model, Principal principal, @ModelAttribute("supply") Supplies supply) {
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        model.addAttribute("supplies", suppliesService.sortSuppliesByDate(suppliesService.listSupplies(dateOfSupplies), isSorted));
        model.addAttribute("dateOfSupplies", dateOfSupplies);
        model.addAttribute("isSorted", isSorted);
        return "supplies-views/index";
    }

    @GetMapping("/{id_supplies}")
    public String suppliedFurniture(@PathVariable Long id_supplies, Model model){
        model.addAttribute("supply", suppliesService.getSuppliesById(id_supplies));
        return "supplies-views/info";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @GetMapping("/startCreate")
    public String startSupplyCreation(Model model, @ModelAttribute("furniture") Furniture furniture){
        newSupply = new Supplies();

        newSupply.setDateOfSupplies("");
        newSupply.setStaff(null);

        newSupply.setSuppliedFurnitureToSupplies(new ArrayList<>());

        model.addAttribute("supply", newSupply);
        model.addAttribute("furnitures", furnitureService.listFurniture(null));
        return "supplies-views/new";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @GetMapping("/create")
    public String supplyCreation(Model model, @ModelAttribute("furniture") Furniture furniture) {
        model.addAttribute("supply", newSupply);
        model.addAttribute("furnitures", furnitureService.listFurniture(null));
        return "supplies-views/new";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PostMapping("/addFurniture")
    public String createSuppliedFurniture(@RequestParam(name = "id_furniture", required = false) Long id_furniture, int amountOfFurniture) {
        newSupply = suppliesService.addFurnitureToSupply(newSupply, id_furniture, amountOfFurniture);
        return "redirect:/supplies/create";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PostMapping("/save")
    public String saveSupplies(){
        newSupply.setStaff(staffController.staff);
        suppliesService.saveSupplies(newSupply);
        return "redirect:/supplies";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PostMapping("/delete/{id_supplies}")
    public String deleteSupply(@PathVariable Long id_supplies) {
        suppliesService.deleteSupplies(id_supplies);
        return "redirect:/supplies";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @GetMapping("/delete/{new_index}")
    public String deleteNewSuppliedFurniture(@PathVariable int new_index) {
        newSupply = suppliesService.deleteNewSuppliedFurniture(new_index, newSupply);
        return "redirect:/supplies/create";
    }
}
