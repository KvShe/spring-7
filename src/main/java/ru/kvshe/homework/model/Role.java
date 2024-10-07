package ru.kvshe.homework.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    USER("user"),
    ADMIN("admin");

    private final String name;
}
