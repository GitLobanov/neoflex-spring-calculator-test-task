package com.neoflex.vacationcalculator.strategy.impl;

import com.neoflex.vacationcalculator.dto.VacationPayRequestDto;
import com.neoflex.vacationcalculator.dto.VacationPayResponseDto;
import com.neoflex.vacationcalculator.strategy.VacationPayStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimpleVacationPayStrategy implements VacationPayStrategy {

    /**
     * Calculates the vacation pay amount based on the average salary and number of vacation days.
     *
     * @param requestDto the request data transfer object containing average salary and vacation days
     * @return a response data transfer object containing the calculated vacation pay amount
     */
    @Override
    public VacationPayResponseDto calculate(VacationPayRequestDto requestDto) {
        BigDecimal salary = requestDto.getAverageSalary();
        int days = requestDto.getVacationDays();
        BigDecimal averageAmountDaysInMonth = new BigDecimal("29.2");

        BigDecimal dailySalary = salary
                .divide(averageAmountDaysInMonth, RoundingMode.HALF_UP);

        BigDecimal vacationPayAmount = dailySalary.multiply(new BigDecimal(days));

        return new VacationPayResponseDto(vacationPayAmount);
    }

}
