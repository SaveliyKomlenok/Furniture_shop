package com.example.furniture.models;

import com.example.furniture.enums.JobTitle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "staff")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_staff")
    private Long idStaff;

    @Column(name = "full_name")
    @Pattern(regexp = "^[А-ЯЁ][а-яё]+\\s[А-ЯЁ][а-яё]+\\s[А-ЯЁ][а-яё]+", message = "Некорректное ФИО, введите ФИО в формате: ФАМИЛИЯ ИМЯ ОТЧЕСТВО")
    private String fullName;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    @Pattern(regexp = "\\+375(17|25|29|33|44)\\d{7}", message = "Некорректный номер телефона, введите номер в формате: +375(код)1234567")
    private String phoneNumber;

    @Column(name = "active")
    private boolean active;

    @Column(name = "date_of_created")
    private LocalDate dateOfCreated;

    @PrePersist
    private void init(){
        dateOfCreated = LocalDate.now();
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
    private List<Sales> staffToSales;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
    private List<Supplies> staffToSupplies;

    @ElementCollection(targetClass = JobTitle.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "job_title", joinColumns = @JoinColumn(name = "id_job_title"))
    @Enumerated(EnumType.STRING)
    private Set<JobTitle> jobTitles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return jobTitles;
    }

    public boolean isUser(){
        return jobTitles.contains(JobTitle.ROLE_USER);
    }
    public boolean isAdmin(){
        return jobTitles.contains(JobTitle.ROLE_ADMIN);
    }
    public boolean isManager(){
        return jobTitles.contains(JobTitle.ROLE_MANAGER);
    }
    public boolean isSeller(){
        return jobTitles.contains(JobTitle.ROLE_SELLER);
    }
    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}