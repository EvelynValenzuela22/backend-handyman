package co.com.ias.handyman.insfranstructure.adapters.output;

import co.com.ias.handyman.infranstructure.adapters.output.PostgreSqlServiceTechnicianRespository;
import co.com.ias.handyman.infranstructure.models.ServiceTechnicianDTO;
import co.com.ias.handyman.serviceTechnician.application.domain.ServiceTechnician;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianFinalDate;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianStartDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.Month.JUNE;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostgreSqlServiceTechnicianRepositoryTest {

    @Autowired
    private PostgreSqlServiceTechnicianRespository repository;

    @Test
    @DisplayName("Valid serviceTechnician should no throw an error")
    void validServiceTechnicianWhenStore() {
        LocalDateTime startDate = LocalDateTime.of(2022, JUNE, 22, 15, 35, 00);
        LocalDateTime finalDate = LocalDateTime.of(2022, JUNE, 22, 16, 35, 00);

        ServiceTechnicianDTO serviceTechnicianDTO = new ServiceTechnicianDTO(11L, 2L, startDate, finalDate);

        ServiceTechnician serviceTechnician = serviceTechnicianDTO.toDomain();

        assertDoesNotThrow(() -> repository.store(serviceTechnician));
    }

    @Test
    @DisplayName("Invalid serviceTechnician should throw an error")
    void invalidServiceTechnicianWhenStore() {
        LocalDateTime startDate = LocalDateTime.of(2022, JUNE, 22, 10, 35, 00);
        LocalDateTime finalDate = LocalDateTime.of(2022, JUNE, 22, 12, 35, 00);
        ServiceTechnicianDTO serviceTechnicianDTO = new ServiceTechnicianDTO(1L, 2L, startDate, finalDate);

        ServiceTechnician serviceTechnician = serviceTechnicianDTO.toDomain();

        assertThrows(RuntimeException.class, () -> repository.store(serviceTechnician));
    }

    @Test
    @DisplayName("Valid star date and final date should return an Optional with data")
    void validDatesWhenGet() {
        LocalDateTime startDate = LocalDateTime.of(2022, JUNE, 22, 10, 35, 00);
        LocalDateTime finalDate = LocalDateTime.of(2022, JUNE, 22, 12, 35, 00);
        ServiceTechnicianStartDate startDate1 = new ServiceTechnicianStartDate(startDate);
        ServiceTechnicianFinalDate finalDate1 = new ServiceTechnicianFinalDate(finalDate);

        ServiceTechnicianDTO serviceTechnicianDTO = new ServiceTechnicianDTO(11L, 2L, startDate, finalDate);

        Optional<ServiceTechnician> serviceTechnician = Optional.of(serviceTechnicianDTO.toDomain());

        //assertEquals(serviceTechnician.get().getIdService().getValue(), repository.get(startDate1, finalDate1).get().getIdService().getValue());
    }

}
