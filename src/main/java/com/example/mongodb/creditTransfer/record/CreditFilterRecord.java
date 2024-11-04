package com.example.mongodb.creditTransfer.record;

import com.example.mongodb.creditTransfer.enumuration.CreditTransferType;

public record CreditFilterRecord(CreditTransferType creditTransferType,
                                 String userId) {
}
