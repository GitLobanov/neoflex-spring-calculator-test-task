package com.neoflex.vacationcalculator.factory;

import com.neoflex.vacationcalculator.dto.VacationPayRequestDto;
import com.neoflex.vacationcalculator.strategy.VacationPayStrategy;
import com.neoflex.vacationcalculator.strategy.impl.HolidayVacationPayStrategy;
import com.neoflex.vacationcalculator.strategy.impl.SimpleVacationPayStrategy;
import org.springframework.stereotype.Component;

@Component
public class VacationPayStrategyFactory {

    /**
     * Gets the appropriate {@link VacationPayStrategy} based on the provided request data.
     *
     * @param requestDto the request data transfer object containing vacation details
     * @return an instance of {@link VacationPayStrategy} that corresponds to the given request parameters
     * @throws IllegalArgumentException if the request parameters are invalid or do not match any strategy criteria
     */
    public VacationPayStrategy getStrategy(VacationPayRequestDto requestDto) {
        if (requestDto.getStartDay() != null && requestDto.getEndDay() != null) {
            return new HolidayVacationPayStrategy();
        } else if (requestDto.getVacationDays() != 0) {
            return new SimpleVacationPayStrategy();
        }
        throw new IllegalArgumentException("Invalid request parameters");
    }

}
