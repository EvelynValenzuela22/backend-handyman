package co.com.ias.handyman.service.application.services;

import co.com.ias.handyman.infranstructure.models.ServiceDTO;
import co.com.ias.handyman.service.application.domain.Service;
import co.com.ias.handyman.service.application.domain.valueObjs.ServiceId;
import co.com.ias.handyman.service.application.ports.output.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class QueryServiceByIdServiceTest {

    private MockMvc mockMvc;


    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private QueryServiceByIdService queryServiceByIdService;

    @BeforeEach
    void setUp() {

        Validator mockValidator = mock(Validator.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(queryServiceByIdService)
                .setValidator(mockValidator)
                .build();
    }

    @Test
    void getServiceByIdValid() {
        Long id = 2L;
        ServiceId serviceId = new ServiceId(id);
        Optional<Service> result = Optional.of(new ServiceDTO().toDomain());

        when(serviceRepository.get(any(ServiceId.class))).thenReturn(result);



    }
}
