package com.neoflex.vacationcalculator.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class VacationPayRequestDto {

    private BigDecimal averageSalary;
    private int vacationDays;
    private LocalDate startDay;
    private LocalDate endDay;

}
