package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    private static final Map<Integer, UserData> USERS = new HashMap<>();

    static {
        USERS.put(1, new UserData(1, "Alice", "alice@example.com"));
        USERS.put(2, new UserData(2, "Bob", "bob@example.com"));
    }

    public UserData findById(int id) {
        return USERS.get(id);
    }

    public record UserData(int id, String name, String email) {}
}