package com.example.furniture.repositories;

import com.example.furniture.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByFullNameContaining(String fullName);
    Staff findByLoginContaining(String login);
    Staff findStaffByLogin(String login);
}