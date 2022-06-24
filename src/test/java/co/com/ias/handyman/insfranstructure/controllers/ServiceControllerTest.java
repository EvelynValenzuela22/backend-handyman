package co.com.ias.handyman.insfranstructure.controllers;

import co.com.ias.handyman.service.application.ports.input.QueryServiceByIdUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class ServiceControllerTest {

    @MockBean
    private QueryServiceByIdUseCase queryServiceByIdUseCase;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get service by id completed")
    void get() {

    }
}
