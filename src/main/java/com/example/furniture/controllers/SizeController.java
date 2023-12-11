package com.example.furniture.controllers;

import com.example.furniture.models.Size;
import com.example.furniture.services.SizeService;
import com.example.furniture.services.StaffService;
import com.example.furniture.util.SizeValidator;
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
@RequestMapping("/size")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_SELLER')")
public class SizeController {
    private final SizeService sizeService;
    private final StaffService staffService;
    private final SizeValidator sizeValidator;

    @GetMapping()
    public String size(@RequestParam(name = "id_size", required = false) Long id_size,
                       @RequestParam(name = "isSorted", required = false) boolean isSorted,
                       Model model, Principal principal, @ModelAttribute("size") Size size) {
        model.addAttribute("sizes", sizeService.sortSizesByAllParameters(sizeService.listSize(id_size), isSorted));
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        model.addAttribute("isSorted", isSorted);
        return "size-views/index";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PostMapping("/create")
    public String createSize(@Valid Size size, BindingResult bindingResult, Model model, Principal principal) {
        sizeValidator.validate(size, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("sizes", sizeService.listSize(null));
            model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
            return "size-views/index";
        }
        sizeService.saveSize(size);
        return "redirect:/size";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PostMapping("/delete")
    public String deleteSize(@RequestParam(name = "id_size", required = false) Long id_size, Model model, Principal principal,
                             @ModelAttribute("size") @Valid Size size, BindingResult bindingResult) {
        sizeValidator.validate(size, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("sizes", sizeService.listSize(null));
            model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
            return "size-views/index";
        }
        sizeService.deleteSize(id_size);
        return "redirect:/size";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @GetMapping("/{id_size}/edit")
    public String startEditSize(@PathVariable(value = "id_size") Long id_size, Model model, Principal principal) {
        model.addAttribute("size", sizeService.getSizeById(id_size));
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        return "size-views/edit";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PostMapping("/{id_size}/editInformation")
    public String editRoom(@PathVariable(value = "id_size") Long id_size, Model model, Principal principal,
                           @ModelAttribute("size") @Valid Size size, BindingResult bindingResult) {
        sizeValidator.validate(size, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
            return "size-views/edit";
        }
        sizeService.editSize(id_size, size);
        return "redirect:/size";
    }
}
