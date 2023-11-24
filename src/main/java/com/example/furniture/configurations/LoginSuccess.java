package com.example.furniture.configurations;

import com.example.furniture.controllers.StaffController;
import com.example.furniture.models.Staff;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LoginSuccess implements ApplicationListener<AuthenticationSuccessEvent> {
    private final StaffController staffController;
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        staffController.staff = (Staff)event.getAuthentication().getPrincipal();
    }
}
