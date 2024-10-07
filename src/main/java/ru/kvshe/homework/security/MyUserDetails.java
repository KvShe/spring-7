package ru.kvshe.homework.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kvshe.homework.model.MyUser;

import java.util.Arrays;
import java.util.Collection;

@AllArgsConstructor
public class MyUserDetails implements UserDetails {
    private MyUser user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
        return Arrays.stream(user.getRoles().split(", "))
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }
}

