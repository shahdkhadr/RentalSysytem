package com.rental.rental.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rental.rental.dto.PaymentDTO;
import com.rental.rental.model.Payment;
import com.rental.rental.model.PaymentStatus;
import com.rental.rental.service.PaymentService.PaymentFactoryImplementation.PaymentFactoryService;
import com.rental.rental.service.PaymentService.PaymentService;
import com.rental.rental.service.PaymentService.PaymentStrategyImplementation.CardPaymentContext;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@WebMvcTest(PaymentController.class) // Used to Test only Web Layer of an application
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentControllerUnitTests {

    @Autowired
    private MockMvc mockMvc; // it's a part of Spring Test Framework used for testing Spring MVC Controller
                             // Used for simulating HTTP requests and verifying response

    @MockBean // Used to mock dependencies
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper; // Jackson Convertor

    @MockBean
    private CardPaymentContext cardPaymentContext;

    @MockBean
    private PaymentFactoryService paymentFactoryService;

    PaymentDTO paymentDTO;

    @BeforeEach
    public void setup() throws ParseException {
        String dateString = "2024-11-05";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(dateString);
        paymentDTO = PaymentDTO.builder()
                .paymentId(1)
                .status(PaymentStatus.valueOf("PENDING"))
                .paymentMethod("card")
                .cardType("PayPal")
                .paymentDate(date)
                .totalAmount(200)
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: Save Payment Test")
    @Rollback(value = false)
    public void savePaymentTest() throws Exception{
        // Precondition
        given(paymentService.processPayment(any(PaymentDTO.class))).willReturn("Payment processed successfully");
        // given() --> provide mocks for testing
        // paymentService.processPayment(any(PaymentDTO.class)) --> This tell mockito to mock The behavior of processPayment method at PaymentService Service for any Object of PaymentDTO
        // any is a Mockito matcher that allows you to specify any instance of a specific class
        // willReturn() --> The expected returns value of processPayment method behavior

        // Action
        ResultActions response = mockMvc.perform(post("/api/v1/car-rental-system/process")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paymentDTO))
        );

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("Payment processed successfully")));

     // verify( mock Object, # times ).method_being_verified(expected_argument_which_method_need)
        verify(paymentService, times(1)).processPayment(any(PaymentDTO.class));
        // بنستخدمها عشان نعرف الميثود اللي اسمها processPayment اللي موجودة في الموك اوبجكت اللي اسمه paymentService كم مرة تنفذت

    }
}
