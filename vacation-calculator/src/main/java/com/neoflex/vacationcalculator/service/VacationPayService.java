package com.neoflex.vacationcalculator.service;

import com.neoflex.vacationcalculator.dto.VacationPayRequestDto;
import com.neoflex.vacationcalculator.dto.VacationPayResponseDto;
import com.neoflex.vacationcalculator.factory.VacationPayStrategyFactory;
import com.neoflex.vacationcalculator.strategy.VacationPayStrategy;
import org.springframework.stereotype.Service;

@Service
public class VacationPayService {

    private final VacationPayStrategyFactory strategyFactory;

    public VacationPayService(VacationPayStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    /**
     * Calculates vacation pay based on the provided request data.
     *
     * @param requestDto the request data transfer object containing vacation details
     * @return a {@link VacationPayResponseDto} containing the calculated vacation pay
     */
    public VacationPayResponseDto calculateVacationPay(VacationPayRequestDto requestDto)  {
        VacationPayStrategy strategy = strategyFactory.getStrategy(requestDto);
        return strategy.calculate(requestDto);
    }

}
