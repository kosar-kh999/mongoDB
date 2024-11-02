package com.example.mongodb.user.record;

public record ResetPasswordRecord(String id, String newPassword, String confirmedPassword) {
}
