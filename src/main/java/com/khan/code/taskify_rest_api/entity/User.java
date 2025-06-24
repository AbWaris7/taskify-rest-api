package com.khan.code.taskify_rest_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    private List<Authority> authorities;


    // ===== UserDetails interface methods =====

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // You can return user roles here later (e.g., ROLE_USER, ROLE_ADMIN)
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return email; // We use email as the username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You can add logic to expire accounts
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Can add lock status logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Useful for password expiry features
    }

    @Override
    public boolean isEnabled() {
        return true; // Can be false for disabled/suspended accounts
    }
}
