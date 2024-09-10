package com.neoflex.vacationcalculator.factory;

import com.neoflex.vacationcalculator.dto.VacationPayRequestDto;
import com.neoflex.vacationcalculator.strategy.VacationPayStrategy;
import com.neoflex.vacationcalculator.strategy.impl.HolidayVacationPayStrategy;
import com.neoflex.vacationcalculator.strategy.impl.SimpleVacationPayStrategy;
import org.springframework.stereotype.Component;

@Component
public class VacationPayStrategyFactory {

    public VacationPayStrategy getStrategy(VacationPayRequestDto requestDto) {
        if (requestDto.getStartDay() != null && requestDto.getEndDay() != null) {
            return new HolidayVacationPayStrategy();
        } else if (requestDto.getVacationDays() != 0) {
            return new SimpleVacationPayStrategy();
        }
        throw new IllegalArgumentException("Invalid request parameters");
    }

}
