package co.com.ias.handyman.serviceTechnician.application.ports.output;

import co.com.ias.handyman.serviceTechnician.application.domain.ServiceTechnician;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianFinalDate;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianStartDate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ServiceTechnicianRepository  {

    void store(ServiceTechnician serviceTechnician);
    Optional<ServiceTechnician>  get(ServiceTechnicianStartDate startDate, ServiceTechnicianFinalDate finalDate);

}
