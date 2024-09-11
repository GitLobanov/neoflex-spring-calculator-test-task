package com.neoflex.vacationcalculator.util;

import de.jollyday.Holiday;
import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameters;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

/**
 * Utility class for date-related calculations.
 */
public class DateUtils {

    /**
     * Calculates the number of holidays within the specified date range.
     *
     * @param start The start date of the range (inclusive).
     * @param end The end date of the range (inclusive).
     * @return The number of holidays between the start and end dates.
     */
    public static long getAmountHolidays (LocalDate start, LocalDate end) {
        HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create("RU"));
        Set<Holiday> holidays = holidayManager.getHolidays(start, end);

        return holidays.size();
    }

    /**
     * Calculates the number of weekends (Saturdays and Sundays) within the specified date range,
     * excluding holidays.
     *
     * @param startDay The start date of the range (inclusive).
     * @param endDay The end date of the range (inclusive).
     * @return The number of weekends between the start and end dates, excluding holidays.
     */
    public static long getAmountWeekend(LocalDate startDay, LocalDate endDay) {
        HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create("RU"));
        long weekends = 0;
        LocalDate date = startDay;
        while (!date.isAfter(endDay)) {
            if (isWeekend(date) && !holidayManager.isHoliday(date)) {
                weekends++;
            }
            date = date.plusDays(1);
        }
        return weekends;

    }

    /**
     * Determines if a given date is a weekend (Saturday or Sunday).
     *
     * @param date The date to check.
     * @return {@code true} if the date is a Saturday or Sunday; {@code false} otherwise.
     */
    public static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

}
