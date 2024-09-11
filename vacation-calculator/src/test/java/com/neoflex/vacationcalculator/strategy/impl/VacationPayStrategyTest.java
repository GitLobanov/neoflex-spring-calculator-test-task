package com.neoflex.vacationcalculator.strategy.impl;

import com.neoflex.vacationcalculator.dto.VacationPayRequestDto;
import com.neoflex.vacationcalculator.dto.VacationPayResponseDto;
import com.neoflex.vacationcalculator.strategy.VacationPayStrategy;
import com.neoflex.vacationcalculator.util.DateUtils;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class VacationPayStrategyTest {

    @Autowired
    @Qualifier("simpleVacationPayStrategy")
    VacationPayStrategy simpleVacationPayStrategy;

    @Autowired
    @Qualifier("holidayWeekendVacationPayStrategy")
    VacationPayStrategy holidayWeekendVacationPayStrategy;

    @Test
    public void testSimpleCalculate() throws Exception {
        VacationPayRequestDto requestDto = VacationPayRequestDto.builder()
                .averageSalary(new BigDecimal(45000))
                .vacationDays(7)
                .build();

        VacationPayResponseDto result = simpleVacationPayStrategy.calculate(requestDto);
        Assertions.assertEquals(new VacationPayResponseDto(new BigDecimal(10787)), result);
    }

    @Test
    public void testHolidayWeekendCalculate() throws Exception {
        VacationPayRequestDto requestDto = VacationPayRequestDto.builder()
                .averageSalary(new BigDecimal(45000))
                .startDay(LocalDate.of(2024,12,30))
                .endDay(LocalDate.of(2025,2,28))
                .build();

        long amountHolidays = DateUtils.getAmountHolidays(requestDto.getStartDay(), requestDto.getEndDay());
        long amountWeekends = DateUtils.getAmountWeekend(requestDto.getStartDay(), requestDto.getEndDay());

        System.out.println("Amount of holidays: " + amountHolidays);
        System.out.println("Amount of weekends: " + amountWeekends);

        VacationPayResponseDto responseDto = holidayWeekendVacationPayStrategy.calculate(requestDto);
        Assertions.assertEquals(new VacationPayResponseDto(new BigDecimal(55476)), responseDto);
    }

}

