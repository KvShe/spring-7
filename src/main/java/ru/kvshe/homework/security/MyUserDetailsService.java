package ru.kvshe.homework.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kvshe.homework.model.MyUser;
import ru.kvshe.homework.repository.UserRepository;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<MyUser> user = userRepository.findByLogin(login);

        return user
                .map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(login + " not found"));
    }
}
