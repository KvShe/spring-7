package ru.kvshe.homework.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WebController {
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('user')")
    public String getUsers() {
        return "users";
    }

    @GetMapping("/admins")
    @PreAuthorize("hasAuthority('admin')")
    public String getAdmins() {
        return "admins";
    }

    @GetMapping("/other")
    public String getOther() {
        return "other";
    }
}
