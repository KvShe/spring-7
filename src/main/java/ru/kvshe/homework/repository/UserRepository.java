package ru.kvshe.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kvshe.homework.model.MyUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByLogin(String login);
}
