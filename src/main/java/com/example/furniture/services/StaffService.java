package com.example.furniture.services;

import com.example.furniture.models.Staff;
import com.example.furniture.enums.JobTitle;
import com.example.furniture.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;
    public void createStaff(Staff staff){
        staff.setActive(true);
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        staff.getJobTitles().add(JobTitle.ROLE_USER);
        staffRepository.save(staff);
    }

    public List<Staff> listStaff(String fullName){
        if(fullName != null) return  staffRepository.findByFullNameStartingWith(fullName);
        return staffRepository.findAll();
    }

    public List<Staff> sortStaffByFullName(List<Staff> staffList, boolean isSorted){
        if(isSorted){
            staffList.sort(Comparator.comparing(Staff::getFullName));
        }
        return staffList;
    }

    public void deleteStaff(Long id_staff){
        staffRepository.deleteById(id_staff);
    }

    public Staff getStaffById(Long id){
        return staffRepository.findById(id).orElse(null);
    }

    public void banStaff(Long id_staff) {
        Staff staff = staffRepository.findById(id_staff).orElse(null);
        if (staff != null){
            staff.setActive(!staff.isActive());
            staffRepository.save(staff);
        }
    }

    public void changeJobTitleOfStaff(Staff staff, Map<String, String> form){
        Set<String> jobTitles = Arrays.stream(JobTitle.values())
                .map(JobTitle::name)
                .collect(Collectors.toSet());
        staff.getJobTitles().clear();
        for (String value : form.values()){
            if(jobTitles.contains(value)){
                staff.getJobTitles().add(JobTitle.valueOf(value));
            }
        }
        staffRepository.save(staff);
    }

    public void changeJobTitleOfUser(Staff staff){
        staff.getJobTitles().clear();
        staff.getJobTitles().add(JobTitle.valueOf("ROLE_SELLER"));
        staffRepository.save(staff);
    }

    public Staff getStaffByPrincipal(Principal principal) {
        if (principal == null) return new Staff();
        return staffRepository.findStaffByLogin(principal.getName());
    }

    public void editStaff(Long id_staff, Staff staff) {
        Staff newStaff = getStaffById(id_staff);
        newStaff.setFullName(staff.getFullName());
        newStaff.setLogin(staff.getLogin());
        newStaff.setPassword(staff.getPassword());
        newStaff.setPhoneNumber(staff.getPhoneNumber());
        newStaff.setActive(staff.isActive());
        newStaff.setJobTitles(staff.getJobTitles());
        newStaff.setDateOfCreated(staff.getDateOfCreated());
        staffRepository.save(staff);
    }
}