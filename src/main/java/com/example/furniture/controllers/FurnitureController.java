package com.example.furniture.controllers;

import com.example.furniture.models.Furniture;
import com.example.furniture.services.*;
import com.example.furniture.util.FurnitureValidator;
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
@RequestMapping("/furniture")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SELLER', 'ROLE_MANAGER', 'ROLE_USER')")
public class FurnitureController {
    private final FurnitureService furnitureService;
    private final ManufacturerService manufacturerService;
    private final RoomsService roomsService;
    private final SizeService sizeService;
    private final StaffService staffService;

    private final FurnitureValidator furnitureValidator;

    @GetMapping()
    public String furniture(@RequestParam (name = "nameOfFurnitureForSearch", required = false) String nameOfFurniture,
                            @RequestParam (name = "isSorted", required = false) boolean isSorted,
                            Model model, Principal principal) {
        model.addAttribute("furnitures", furnitureService.sortFurnitureByName(furnitureService.listFurniture(nameOfFurniture), isSorted));
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        model.addAttribute("nameOfFurnitureForSearch", nameOfFurniture);
        model.addAttribute("isSorted", isSorted);
        return "furniture-views/index";
    }

    @GetMapping("/{id_furniture}")
    public String furnitureInfo(@PathVariable Long id_furniture, Model model, Principal principal) {
        model.addAttribute("furniture", furnitureService.getFurnitureById(id_furniture));
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        return "furniture-views/info";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @GetMapping("/new")
    public String newFurniture(Model model, @ModelAttribute("furniture") Furniture furniture) {
        model.addAttribute("furnitures", furnitureService.listFurniture(null));
        addSecondaryAttributes(model);
        return "furniture-views/new";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PostMapping("/create")
    public String createFurniture(@ModelAttribute("furniture") @Valid Furniture furniture, BindingResult bindingResult, Model model) {
        furnitureValidator.validate(furniture, bindingResult);
        if(bindingResult.hasErrors()){
            addSecondaryAttributes(model);
            return "furniture-views/new";
        }
        furnitureService.saveFurniture(furniture);
        return "redirect:/furniture";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PostMapping("/delete/{id_furniture}")
    public String deleteFurniture(@PathVariable Long id_furniture) {
        furnitureService.deleteFurniture(id_furniture);
        return "redirect:/furniture";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @GetMapping("/{id_furniture}/edit")
    public String startEditFurniture(@PathVariable(value = "id_furniture") Long id_furniture, Model model, Principal principal){
        model.addAttribute("furniture", furnitureService.getFurnitureById(id_furniture));
        addSecondaryAttributes(model);
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        // return furniture-edit;
        return "furniture-views/edit";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PostMapping("/{id_furniture}/editInformation")
    public String editFurniture(@PathVariable (value = "id_furniture") Long id_furniture, Model model, Principal principal,
                                @Valid Furniture furniture, BindingResult bindingResult){
        furnitureValidator.validate(furniture, bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("furniture", furniture);
            addSecondaryAttributes(model);
            model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
            return "furniture-views/edit";
        }
        furnitureService.editFurniture(id_furniture, furniture);
        return "redirect:/furniture";
    }

    private void addSecondaryAttributes(Model model) {
        model.addAttribute("manufacturers", manufacturerService.listManufacturer(null));
        model.addAttribute("rooms", roomsService.listRooms(null));
        model.addAttribute("sizes", sizeService.listSize(null));
    }
}
