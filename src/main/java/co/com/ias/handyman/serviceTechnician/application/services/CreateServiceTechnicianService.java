package co.com.ias.handyman.serviceTechnician.application.services;

import co.com.ias.handyman.infranstructure.models.ServiceTechnicianDTO;
import co.com.ias.handyman.serviceTechnician.application.domain.ServiceTechnician;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianFinalDate;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianStartDate;
import co.com.ias.handyman.serviceTechnician.application.ports.input.CreateServiceTechnicianUseCase;
import co.com.ias.handyman.serviceTechnician.application.ports.output.ServiceTechnicianRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;
import java.util.Objects;
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

        resultDatabase.ifPresent(result -> {
            boolean serviceExist = Objects.equals(result.getIdService().getValue(), serviceTechnicianDTO.getIdService());
            boolean technicianExist = Objects.equals(result.getIdTechnician().getValue(), serviceTechnicianDTO.getIdService());
            if(!serviceExist || !technicianExist) {
                repository.store(serviceTechnicianDTO.toDomain());
                serviceTechnicianDTO.setStatus("Created");
            }
        });
        return serviceTechnicianDTO;

    }
}
