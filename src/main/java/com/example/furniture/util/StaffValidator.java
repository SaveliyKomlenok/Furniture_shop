package com.example.furniture.util;

import com.example.furniture.models.Staff;
import com.example.furniture.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class StaffValidator implements Validator {
    private final StaffRepository staffRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return Staff.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Staff staff = (Staff) target;

        if(staffRepository.findStaffByLogin(staff.getLogin()) != null && !Objects.equals(staff.getIdStaff(), staffRepository.findStaffByLogin(staff.getLogin()).getIdStaff())){
            errors.rejectValue("login", "", "Введенный логин уже занят");
        }
    }
}