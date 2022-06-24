package co.com.ias.handyman.serviceTechnician.application.ports.output;

import co.com.ias.handyman.serviceTechnician.application.domain.ServiceTechnician;
import org.springframework.stereotype.Repository;

public interface ServiceTechnicianRepository  {

    void store(ServiceTechnician serviceTechnician);

}
