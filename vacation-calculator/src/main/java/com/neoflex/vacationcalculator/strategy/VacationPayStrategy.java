package com.neoflex.vacationcalculator.strategy;

import com.neoflex.vacationcalculator.dto.VacationPayRequestDto;
import com.neoflex.vacationcalculator.dto.VacationPayResponseDto;

public interface VacationPayStrategy {
    VacationPayResponseDto calculate(VacationPayRequestDto requestDto);
}
