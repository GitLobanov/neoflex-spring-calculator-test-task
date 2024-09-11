package com.neoflex.vacationcalculator.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class DateUtilsTest {

    @Test
    public void testGetAmountHolidays() throws Exception {
        long result = DateUtils.getAmountHolidays(
                LocalDate.of(2024,12,30),
                LocalDate.of(2025,2,28));
        Assertions.assertEquals(9L, result);
    }

    @Test
    public void testGetAmountWeekend() throws Exception {

        long result = DateUtils.getAmountWeekend(
                LocalDate.of(2024,12,30),
                LocalDate.of(2025,2,28));
        Assertions.assertEquals(16L, result);
    }

    @Test
    public void testIsWeekend() throws Exception {
        boolean result = DateUtils.isWeekend(LocalDate.of(2024, Month.SEPTEMBER, 14));
        Assertions.assertEquals(true, result);
    }
}