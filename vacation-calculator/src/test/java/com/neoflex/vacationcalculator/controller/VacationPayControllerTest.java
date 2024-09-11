package com.neoflex.vacationcalculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoflex.vacationcalculator.dto.VacationPayRequestDto;
import com.neoflex.vacationcalculator.dto.VacationPayResponseDto;
import com.neoflex.vacationcalculator.service.VacationPayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VacationPayController.class)
public class VacationPayControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VacationPayService vacationPayService;

    @Test
    public void testCalculateVacationPaySuccess() throws Exception {
        BigDecimal averageSalary = new BigDecimal("50000");
        int vacationDays = 10;

        VacationPayResponseDto responseDto = new VacationPayResponseDto(new BigDecimal("12000"));
        when(vacationPayService.calculateVacationPay(VacationPayRequestDto.builder()
                .averageSalary(averageSalary)
                        .vacationDays(vacationDays)
                        .build())).thenReturn(responseDto);

        mockMvc.perform(get("/calculate")
                        .param("averageSalary", "50000")
                        .param("vacationDays", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(responseDto)));
    }

    @Test
    public void testCalculateVacationPayInvalidAverageSalary() throws Exception {
        mockMvc.perform(get("/calculate")
                        .param("averageSalary", "-5000")
                        .param("vacationDays", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCalculateVacationPayInvalidDateOrder() throws Exception {
        mockMvc.perform(get("/calculate")
                        .param("averageSalary", "50000")
                        .param("startDay", "2024-01-10")
                        .param("endDay", "2024-01-01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}

