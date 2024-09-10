package com.neoflex.vacationcalculator.util;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ValidationUtils {

    public static void validateAverageSalary(BigDecimal averageSalary) {
        if (averageSalary == null || averageSalary.signum() <= 0) {
            throw new IllegalArgumentException("Invalid average salary");
        }
    }

    public static void validateDates(LocalDate startDay, LocalDate endDay) {
        if (startDay != null && endDay != null && startDay.isAfter(endDay)) {
            throw new IllegalArgumentException("Start date must not be after end date");
        }
    }

}
