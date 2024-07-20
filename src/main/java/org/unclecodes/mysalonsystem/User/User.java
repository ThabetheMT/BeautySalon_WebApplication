package org.unclecodes.mysalonsystem.User;

public record User(
        String firstName,
        String lastName,
        String phone,
        String email,
        String password,
        String role
) {}
