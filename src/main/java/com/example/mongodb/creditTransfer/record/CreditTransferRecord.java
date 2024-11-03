package com.example.mongodb.creditTransfer.record;

import java.math.BigDecimal;

public record CreditTransferRecord(String userId,
                                   BigDecimal amount,
                                   String description) {
}
