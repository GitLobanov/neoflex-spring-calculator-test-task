package com.neoflex.vacationcalculator.strategy.impl;

import com.neoflex.vacationcalculator.dto.VacationPayRequestDto;
import com.neoflex.vacationcalculator.dto.VacationPayResponseDto;
import com.neoflex.vacationcalculator.strategy.VacationPayStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimpleVacationPayStrategy implements VacationPayStrategy {

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
