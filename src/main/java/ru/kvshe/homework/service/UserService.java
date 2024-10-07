package ru.kvshe.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kvshe.homework.model.MyUser;
import ru.kvshe.homework.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<MyUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<MyUser> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public MyUser createUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
