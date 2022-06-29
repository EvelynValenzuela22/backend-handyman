package co.com.ias.handyman.serviceTechnician.application.services;

import co.com.ias.handyman.infranstructure.models.ServiceTechnicianDTO;
import co.com.ias.handyman.serviceTechnician.application.domain.ServiceTechnician;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianFinalDate;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianIdService;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianIdTechnician;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianStartDate;
import co.com.ias.handyman.serviceTechnician.application.ports.output.ServiceTechnicianRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static java.time.Month.JUNE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateServiceTechnicianServiceTest {

    @Mock
    private ServiceTechnicianRepository repository;

    @InjectMocks
    private CreateServiceTechnicianService createServiceTechnicianService;

    @Test
    @DisplayName("ServiceTechnicianDTO  valid should return an ServiceTechnicianDTO")
    void createServiceTechnicianWhenDTOIsValid() throws Exception {
        String status = "Created";

        ServiceTechnicianDTO serviceTechnicianDTO = mock(ServiceTechnicianDTO.class);
        ServiceTechnician serviceTechnician = mock(ServiceTechnician.class);

        when(serviceTechnicianDTO.toDomain()).thenReturn(serviceTechnician);

        assertFalse(createServiceTechnicianService.serviceExistsBetweenDates(serviceTechnician));
        assertFalse(createServiceTechnicianService.technicianExistBetweenDates(serviceTechnician));

        when(serviceTechnicianDTO.getStatus()).thenReturn(status);

        doNothing().when(serviceTechnicianDTO).setStatus(status);

        doNothing().when(repository).store(serviceTechnician);

        assertNotNull(createServiceTechnicianService.execute(serviceTechnicianDTO));
        assertEquals(serviceTechnicianDTO, serviceTechnicianDTO);

    }


}




