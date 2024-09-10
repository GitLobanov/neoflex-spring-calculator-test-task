package com.neoflex.vacationcalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VacationPayResponseDto {

    private BigDecimal vacationPayAmount;

}
