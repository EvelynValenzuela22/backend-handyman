package co.com.ias.handyman.insfranstructure.controllers;

import co.com.ias.handyman.infranstructure.controllers.TechnicianController;
import co.com.ias.handyman.serviceTechnician.application.ports.input.CreateServiceTechnicianUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class ServiceTechnicianControllerTest {
    @MockBean
    private CreateServiceTechnicianUseCase createServiceTechnicianUseCase;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Create Service Technician successfully")
    public void CreateTechnicianController(){


    }
}
