package com.example.mongodb.creditTransfer.enumuration;

import java.util.Arrays;

public enum CreditTransferType {
    INITIAL_REQUEST(0,"درخواست اولیه"),
    CONFIRMED(1,"تایید شده"),
    REJECT(2,"رد شده");

    private Integer id;
    private String title;

    CreditTransferType(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public static CreditTransferType getById(Integer id) {
        return Arrays.stream(values()).parallel().filter(value -> value.getId().equals(id)).findFirst().orElse(null);
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
