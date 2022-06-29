package co.com.ias.handyman.serviceTechnician.application.ports.output;

import co.com.ias.handyman.serviceTechnician.application.domain.ServiceTechnician;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianFinalDate;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianIdService;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianIdTechnician;
import co.com.ias.handyman.serviceTechnician.application.domain.valueObjs.ServiceTechnicianStartDate;

import java.util.List;
import java.util.Optional;


public interface ServiceTechnicianRepository  {

    void store(ServiceTechnician serviceTechnician);
    Optional<ServiceTechnician> getServiceBetweenDates(ServiceTechnicianStartDate startDate, ServiceTechnicianFinalDate finalDate, ServiceTechnicianIdService idService);

    Optional<ServiceTechnician> getTechnicianBetweenDates(ServiceTechnicianStartDate startDate, ServiceTechnicianFinalDate finalDate, ServiceTechnicianIdTechnician idTechnician);

}
