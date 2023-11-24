package com.example.furniture.controllers;

import com.example.furniture.models.Manufacturer;
import com.example.furniture.models.Rooms;
import com.example.furniture.repositories.RoomsRepository;
import com.example.furniture.services.RoomsService;
import com.example.furniture.services.StaffService;
import com.example.furniture.util.RoomsValidator;
import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomsController {
    private final RoomsService roomsService;
    private final RoomsValidator roomsValidator;
    private final StaffService staffService;

    @GetMapping()
    public String rooms(@RequestParam(name = "nameOfRoomForSearch", required = false, defaultValue = "") String nameOfRoom,
                        Model model, Principal principal, @ModelAttribute("room") Rooms rooms) {
        model.addAttribute("rooms", roomsService.listRooms(nameOfRoom));
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        model.addAttribute("nameOfRoomForSearch", nameOfRoom);
        return "rooms-views/index";
    }

    @PostMapping("/create")
    public String createRoom(@ModelAttribute("room") @Valid Rooms rooms, BindingResult bindingResult, Model model, Principal principal) {
        roomsValidator.validate(rooms, bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("rooms", roomsService.listRooms(null));
            model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
            return "rooms-views/index";
        }
        roomsService.saveRooms(rooms);
        return "redirect:/rooms";
    }

    @PostMapping("/delete")
    public String deleteRoom(@RequestParam(name = "id_rooms", required = false) Long id_rooms, Model model, Principal principal,
                             @ModelAttribute("room") @Valid Rooms rooms, BindingResult bindingResult) {
        roomsValidator.validate(rooms, bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("rooms", roomsService.listRooms(null));
            model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
            return "rooms-views/index";
        }
        roomsService.deleteRooms(id_rooms);
        return "redirect:/rooms";
    }

    @GetMapping("/{id_rooms}/edit")
    public String startEditRoom(@PathVariable(value = "id_rooms") Long id_room, Model model, Principal principal){
        model.addAttribute("room", roomsService.getRoomsById(id_room));
        model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
        return "rooms-views/edit";
    }

    @PostMapping("/{id_rooms}/editInformation")
    public String editRoom(@PathVariable(value = "id_rooms") Long id_room, Model model, Principal principal,
                           @ModelAttribute("room") @Valid Rooms room, BindingResult bindingResult){
        roomsValidator.validate(room, bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("staff", staffService.getStaffByPrincipal(principal));
            return "rooms-views/edit";
        }
        roomsService.editRoom(id_room, room);
        return "redirect:/rooms";
    }
}
