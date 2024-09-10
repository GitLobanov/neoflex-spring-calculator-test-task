package com.neoflex.vacationcalculator.service;

import com.neoflex.vacationcalculator.dto.VacationPayRequestDto;
import com.neoflex.vacationcalculator.dto.VacationPayResponseDto;
import de.jollyday.Holiday;
import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameters;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class VacationPayService {

    public VacationPayResponseDto calculate(VacationPayRequestDto requestDto) {

        if (requestDto.getVacationDays()!=0 && requestDto.getStartDay() == null && requestDto.getEndDay() == null) {
            return calculateVacationPay(requestDto);
        }

        if (requestDto.getStartDay() != null && requestDto.getEndDay() != null) {
            return calculateVacationPayWithWeekendsAndHolidays(requestDto);
        }

        return null;
    }


    public VacationPayResponseDto calculateVacationPay(VacationPayRequestDto vacationPayRequestDto) {

        BigDecimal salary = vacationPayRequestDto.getAverageSalary();
        int days = vacationPayRequestDto.getVacationDays();
        BigDecimal averageAmountDaysInMonth = new BigDecimal("29.2");

        BigDecimal dailySalary = salary
                .divide(averageAmountDaysInMonth, RoundingMode.HALF_UP);

        BigDecimal vacationPayAmount = dailySalary.multiply(new BigDecimal(days));

        return new VacationPayResponseDto(vacationPayAmount);
    }


    public VacationPayResponseDto calculateVacationPayWithWeekendsAndHolidays(VacationPayRequestDto vacationPayRequestDto) {

        BigDecimal salary = vacationPayRequestDto.getAverageSalary();
        long totalDays = ChronoUnit.DAYS.between(vacationPayRequestDto.getStartDay(), vacationPayRequestDto.getEndDay()) + 1;
        long holidays = getAmountHolidays(vacationPayRequestDto.getStartDay(), vacationPayRequestDto.getEndDay());
        long weekends = getAmountWeekend(vacationPayRequestDto.getStartDay(), vacationPayRequestDto.getEndDay());

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
