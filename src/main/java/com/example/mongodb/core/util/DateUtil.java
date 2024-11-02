package com.example.mongodb.core.util;

import com.github.mfathi91.time.PersianDate;

import java.time.LocalDate;

public class DateUtil {

    public static LocalDate convertPersianDateStringToLocalDate(String persianDateString) {
        String[] parts = persianDateString.split("/");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid Persian date format. Expected format: yyyy/MM/dd");
        }

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        PersianDate persianDate = PersianDate.of(year, month, day);

        return persianDate.toGregorian();
    }
}
