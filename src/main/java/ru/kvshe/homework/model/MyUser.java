package ru.kvshe.homework.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true) // уникальное значение
    private String login;
    private String password;
    //    private Role role;
    private String roles;
}
