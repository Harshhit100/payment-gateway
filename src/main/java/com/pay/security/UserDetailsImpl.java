package com.pay.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pay.model.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = {"userName"})
public class UserDetailsImpl implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String userName;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(User user) {
        final List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getType().name()))
                .toList();

        return new UserDetailsImpl(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
        return true;
    }
}