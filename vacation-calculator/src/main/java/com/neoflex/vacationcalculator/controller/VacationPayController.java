package com.neoflex.vacationcalculator.controller;

import com.neoflex.vacationcalculator.dto.VacationPayRequestDto;
import com.neoflex.vacationcalculator.dto.VacationPayResponseDto;
import com.neoflex.vacationcalculator.service.VacationPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/calculate")
public class VacationPayController {

    @Autowired
    private VacationPayService vacationPayService;

    @GetMapping
    public VacationPayResponseDto calculate (
            @RequestParam BigDecimal averageSalary,
            @RequestParam(required = false, defaultValue = "0") Integer vacationDays,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDay,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDay
            ) {

        VacationPayRequestDto requestDto = VacationPayRequestDto.builder()
                .averageSalary(averageSalary)
                .vacationDays(vacationDays)
                .startDay(startDay)
                .endDay(endDay)
                .build();

        return vacationPayService.calculate(requestDto);
    }

}
