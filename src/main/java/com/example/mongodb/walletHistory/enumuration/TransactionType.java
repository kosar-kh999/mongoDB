package com.example.mongodb.walletHistory.enumuration;

import java.util.Arrays;

public enum TransactionType {
    DEPOSIT(0, "واریز"),
    WITHDRAWAL(1, "برداشت");

    private Integer id;
    private String title;

    TransactionType(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public static TransactionType getById(Integer id) {
        return Arrays.stream(values()).parallel().filter(value -> value.getId().equals(id)).findFirst().orElse(null);
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
