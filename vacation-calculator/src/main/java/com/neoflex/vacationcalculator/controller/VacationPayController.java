package com.neoflex.vacationcalculator.controller;

import com.neoflex.vacationcalculator.dto.VacationPayRequestDto;
import com.neoflex.vacationcalculator.dto.VacationPayResponseDto;
import com.neoflex.vacationcalculator.service.VacationPayService;
import com.neoflex.vacationcalculator.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/calculate")
public class VacationPayController {

    private final VacationPayService vacationPayService;

    public VacationPayController(VacationPayService vacationPayService) {
        this.vacationPayService = vacationPayService;
    }

    @GetMapping
    public ResponseEntity<VacationPayResponseDto> calculate (
            @RequestParam BigDecimal averageSalary,
            @RequestParam(required = false, defaultValue = "0") Integer vacationDays,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDay,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDay
            ) {

        ValidationUtils.validateAverageSalary(averageSalary);
        ValidationUtils.validateDates(startDay, endDay);

        VacationPayRequestDto requestDto = VacationPayRequestDto.builder()
                .averageSalary(averageSalary)
                .vacationDays(vacationDays)
                .startDay(startDay)
                .endDay(endDay)
                .build();

        try {
            VacationPayResponseDto responseDto = vacationPayService.calculateVacationPay(requestDto);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

}
