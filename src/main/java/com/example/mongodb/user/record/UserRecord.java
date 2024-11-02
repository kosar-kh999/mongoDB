package com.example.mongodb.user.record;

import java.util.List;

public record UserRecord(String userId, List<String> roleIds) {
}
