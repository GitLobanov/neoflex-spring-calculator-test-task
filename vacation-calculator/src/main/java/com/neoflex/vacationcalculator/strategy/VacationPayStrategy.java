package com.neoflex.vacationcalculator.strategy;

import com.neoflex.vacationcalculator.dto.VacationPayRequestDto;
import com.neoflex.vacationcalculator.dto.VacationPayResponseDto;
import com.neoflex.vacationcalculator.service.VacationPayService;

import java.math.BigDecimal;

public interface VacationPayStrategy {
    VacationPayResponseDto calculate(VacationPayRequestDto requestDto);
}
