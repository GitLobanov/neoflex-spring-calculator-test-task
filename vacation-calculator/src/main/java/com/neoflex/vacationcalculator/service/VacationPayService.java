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

    public VacationPayResponseDto calculateVacationPay(VacationPayRequestDto requestDto)  {
        VacationPayStrategy strategy = strategyFactory.getStrategy(requestDto);
        return strategy.calculate(requestDto);
    }

}
