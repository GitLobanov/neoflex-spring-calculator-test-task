package com.neoflex.vacationcalculator.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ValidationUtilsTest {

    @Test
    public void testValidateAverageSalary() {
        assertDoesNotThrow(() -> ValidationUtils.validateAverageSalary(new BigDecimal("1000")));
        assertThrows(IllegalArgumentException.class, () -> ValidationUtils.validateAverageSalary(null));
        assertThrows(IllegalArgumentException.class, () -> ValidationUtils.validateAverageSalary(new BigDecimal("-1000")));
        assertThrows(IllegalArgumentException.class, () -> ValidationUtils.validateAverageSalary(BigDecimal.ZERO));
    }

    @Test
    public void testValidateDates() {
        assertDoesNotThrow(() -> ValidationUtils.validateDates(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 2)));
        assertThrows(IllegalArgumentException.class, () -> ValidationUtils.validateDates(LocalDate.of(2024, 1, 2), LocalDate.of(2023, 1, 1)));
    }


    @Test
    public void testValidateDatesAndVacationDays() {
        assertDoesNotThrow(() -> ValidationUtils.validateDatesAndVacationDays(0, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 7)));
        assertThrows(IllegalArgumentException.class, () -> ValidationUtils.validateDatesAndVacationDays(0, LocalDate.of(2024, 1, 1), null));
        assertThrows(IllegalArgumentException.class, () -> ValidationUtils.validateDatesAndVacationDays(-1, null, LocalDate.of(2024, 1, 1)));
        assertDoesNotThrow(() -> ValidationUtils.validateDatesAndVacationDays(5, null, null));
        assertDoesNotThrow(() -> ValidationUtils.validateDatesAndVacationDays(5, LocalDate.of(2024, 1, 1), null));
    }

}



