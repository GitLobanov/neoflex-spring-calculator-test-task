package com.neoflex.vacationcalculator.strategy.impl;

import com.neoflex.vacationcalculator.dto.VacationPayRequestDto;
import com.neoflex.vacationcalculator.dto.VacationPayResponseDto;
import com.neoflex.vacationcalculator.strategy.VacationPayStrategy;
import de.jollyday.Holiday;
import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameters;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HolidayVacationPayStrategy implements VacationPayStrategy {


    @Override
    public VacationPayResponseDto calculate(VacationPayRequestDto requestDto) {

        BigDecimal salary = requestDto.getAverageSalary();
        long totalDays = ChronoUnit.DAYS.between(requestDto.getStartDay(), requestDto.getEndDay()) + 1;
        long holidays = getAmountHolidays(requestDto.getStartDay(), requestDto.getEndDay());
        long weekends = getAmountWeekend(requestDto.getStartDay(), requestDto.getEndDay());

        long workDays = totalDays - holidays - weekends;

        BigDecimal averageAmountDaysInMonth = new BigDecimal("29.2");

        BigDecimal dailySalary = salary
                .divide(averageAmountDaysInMonth, RoundingMode.HALF_UP);

        BigDecimal vacationPayAmount = dailySalary.multiply(new BigDecimal(workDays));

        return new VacationPayResponseDto(vacationPayAmount);
    }

    private long getAmountHolidays (LocalDate start, LocalDate end) {
        HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create("RU"));
        Set<Holiday> holidays = holidayManager.getHolidays(start, end);

        return holidays.size();
    }

    private long getAmountWeekend(LocalDate startDay, LocalDate endDay) {

        List<LocalDate> weekends = new ArrayList<>();
        LocalDate date = startDay;
        while (!date.isAfter(endDay)) {
            if (isWeekend(date)) {
                weekends.add(date);
            }
            date = date.plusDays(1);
        }
        return weekends.size();
    }

    public boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

}
