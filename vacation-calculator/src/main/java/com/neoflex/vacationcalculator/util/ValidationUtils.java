package com.neoflex.vacationcalculator.util;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Utility class for validation checks.
 */
public class ValidationUtils {

    /**
     * Validates the average salary to ensure it is not null and greater than zero.
     *
     * @param averageSalary The average salary to validate.
     * @throws IllegalArgumentException If the average salary is null or less than or equal to zero.
     */
    public static void validateAverageSalary(BigDecimal averageSalary) {
        if (averageSalary == null || averageSalary.signum() <= 0) {
            throw new IllegalArgumentException("Invalid average salary");
        }
    }

    /**
     * Validates the order of dates to ensure that the start date is not after the end date.
     *
     * @param startDay The start date of the range.
     * @param endDay The end date of the range.
     * @throws IllegalArgumentException If the start date is after the end date.
     */
    public static void validateDates(LocalDate startDay, LocalDate endDay) {
        if (startDay != null && endDay != null && startDay.isAfter(endDay)) {
            throw new IllegalArgumentException("Invalid order of dates");
        }
    }

    /**
     * Validates the combination of vacation days and date range (start and end days).
     * If both start and end dates are null, the vacation days must be greater than zero.
     *
     * @param vacationDays The number of vacation days to validate.
     * @param startDay The start date of the vacation period.
     * @param endDay The end date of the vacation period.
     * @throws IllegalArgumentException if both startDay and endDay are null and vacationDays is less than or equal to zero.
     */
    public static void validateDatesAndVacationDays (Integer vacationDays, LocalDate startDay, LocalDate endDay) {
        if ((startDay == null || endDay == null) && vacationDays <= 0) {
            throw new IllegalArgumentException("Invalid not full date range (need start or end day)");
        }
    }

}
