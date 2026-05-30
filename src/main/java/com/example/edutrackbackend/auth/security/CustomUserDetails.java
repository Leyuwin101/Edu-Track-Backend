package com.example.edutrackbackend.auth.security;

import com.example.edutrackbackend.common.enums.Role;
import com.example.edutrackbackend.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;

    // return the authenticated user id
    public Long getUserId() { return user.getUserId(); }

    // return the user's username stored in the system
    public String getAccountUsername() { return user.getUsername(); }

    // returns the authenticated user's email
    public String getEmail() { return user.getEmail(); }

    // returns the authenticated user's role
    public Role getRole() { return user.getRole(); }

    // Converts the application's role into Spring security authorities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    // Return the encrypted password used for authentication
    @Override
    public String getPassword() { return user.getPassword(); }

    // Uses email as the login identifier
    @Override
    public String getUsername() { return user.getEmail(); }

    // Indicates whether the account has Expired
    @Override
    public boolean isAccountNonExpired() { return true; }

    // Indicates whether the account's locked
    @Override
    public boolean isAccountNonLocked() { return true; }

    // Indicates whether the user's credential has expired
    @Override
    public boolean isCredentialsNonExpired() { return true; }

    // Indicates whether the account is enabled
    @Override
    public boolean isEnabled() { return true; }
}
