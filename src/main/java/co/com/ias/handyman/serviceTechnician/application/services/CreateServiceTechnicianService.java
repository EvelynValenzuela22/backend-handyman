package co.com.ias.handyman.serviceTechnician.application.services;

import co.com.ias.handyman.infranstructure.models.ServiceTechnicianDTO;
import co.com.ias.handyman.serviceTechnician.application.domain.ServiceTechnician;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianFinalDate;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianStartDate;
import co.com.ias.handyman.serviceTechnician.application.ports.input.CreateServiceTechnicianUseCase;
import co.com.ias.handyman.serviceTechnician.application.ports.output.ServiceTechnicianRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateServiceTechnicianService implements CreateServiceTechnicianUseCase {
    private final ServiceTechnicianRepository repository;

    public CreateServiceTechnicianService(ServiceTechnicianRepository repository) {
        this.repository = repository;
    }

    @Override
    public ServiceTechnicianDTO execute(ServiceTechnicianDTO serviceTechnicianDTO) {
        ServiceTechnicianStartDate startDate = new ServiceTechnicianStartDate(serviceTechnicianDTO.getStartDate());
        ServiceTechnicianFinalDate finalDate = new ServiceTechnicianFinalDate(serviceTechnicianDTO.getFinalDate());
        Optional<ServiceTechnician> resultDatabase = repository.get(startDate, finalDate);
        if(resultDatabase.isPresent()) {
            serviceTechnicianDTO.setStatus("Can not be created");
        } else {
            repository.store(serviceTechnicianDTO.toDomain());
            serviceTechnicianDTO.setStatus("Created");
        }
        return serviceTechnicianDTO;

    }
}
