package com.example.furniture.controllers;

import com.example.furniture.models.Furniture;
import com.example.furniture.models.Sales;
import com.example.furniture.services.FurnitureService;
import com.example.furniture.services.SalesService;
import com.example.furniture.services.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SalesController {
    private final SalesService salesService;
    private final FurnitureService furnitureService;
    private final StaffController staffController;
    private final StaffService staffService;
    Sales newSale;

    @GetMapping()
    public String sale(@RequestParam(name = "dateOfSales", required = false) String dateOfSales, Model model, Principal principal,
                       @ModelAttribute("sale") Sales sales) {
        model.addAttribute("sales", salesService.listSales(dateOfSales));
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        return "sales-views/index";
    }

    @GetMapping("/{id_sales}")
    public String soldFurniture(@PathVariable Long id_sales, Model model) {
        model.addAttribute("sale", salesService.getSalesById(id_sales));
        return "sales-views/info";
    }

    @GetMapping("/startCreate")
    public String startSaleCreation(Model model, @ModelAttribute("furniture") Furniture furniture) {
        newSale = new Sales();

        newSale.setDateOfSales("");
        newSale.setStaff(null);

        newSale.setSoldFurnitureToSales(new ArrayList<>());

        model.addAttribute("newSale", newSale);
        model.addAttribute("furnitures", furnitureService.listFurniture(null));
        return "sales-views/new";
    }

    @GetMapping("/create")
    public String saleCreation(Model model, @ModelAttribute("furniture") Furniture furniture) {
        model.addAttribute("newSale", newSale);
        model.addAttribute("furnitures", furnitureService.listFurniture(null));
        return "sales-views/new";
    }

    @PostMapping("/addFurniture")
    public String createSoldFurniture(@RequestParam(name = "id_furniture", required = false) Long id_furniture, int amountOfFurniture, Model model,
                                      @ModelAttribute("furniture") Furniture furniture) {
        int availableAmount = salesService.getAvailableAmount(id_furniture, newSale);
        if (availableAmount < amountOfFurniture) {
            model.addAttribute("newSale", newSale);
            model.addAttribute("furnitures", furnitureService.listFurniture(null));
            model.addAttribute("availableAmount", availableAmount);
            return "sales-views/new";
        }
        newSale = salesService.addFurnitureToSale(newSale, id_furniture, amountOfFurniture);
        return "redirect:/sales/create";
    }

    @PostMapping("/save")
    public String saveSale() {
        newSale.setStaff(staffController.staff);
        salesService.saveSales(newSale);
        return "redirect:/sales";
    }

    @PostMapping("/delete")
    public String deleteSale(@RequestParam(name = "id_sales", required = false) Long id_sales) {
        salesService.deleteSales(id_sales);
        return "redirect:/sales";
    }

    @GetMapping("/delete/{new_index}")
    public String deleteNewSoldFurniture(@PathVariable int new_index) {
        newSale = salesService.deleteNewSoldFurniture(new_index, newSale);
        return "redirect:/sales/create";
    }
}