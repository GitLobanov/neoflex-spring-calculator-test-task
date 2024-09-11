package com.neoflex.vacationcalculator.strategy.impl;

import com.neoflex.vacationcalculator.dto.VacationPayRequestDto;
import com.neoflex.vacationcalculator.dto.VacationPayResponseDto;
import com.neoflex.vacationcalculator.strategy.VacationPayStrategy;
import com.neoflex.vacationcalculator.util.DateUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

@Component("holidayWeekendVacationPayStrategy")
public class HolidayWeekendVacationPayStrategy implements VacationPayStrategy {

    /**
     * Calculates the vacation pay amount considering holidays and weekends.
     *
     * @param requestDto the request data transfer object containing salary and date range
     * @return the response data transfer object containing vacation pay amount
     */
    @Override
    public VacationPayResponseDto calculate(VacationPayRequestDto requestDto) {

        BigDecimal salary = requestDto.getAverageSalary();

        long totalDays = ChronoUnit.DAYS.between(requestDto.getStartDay(), requestDto.getEndDay()) + 1;
        long holidays = DateUtils.getAmountHolidays(requestDto.getStartDay(), requestDto.getEndDay());
        long weekends = DateUtils.getAmountWeekend(requestDto.getStartDay(), requestDto.getEndDay());

        long workDays = totalDays - holidays - weekends;

        BigDecimal averageAmountDaysInMonth = new BigDecimal("29.2");

        BigDecimal dailySalary = salary
                .divide(averageAmountDaysInMonth, RoundingMode.HALF_UP);

        BigDecimal vacationPayAmount = dailySalary.multiply(new BigDecimal(workDays));

        return new VacationPayResponseDto(vacationPayAmount);
    }

}
