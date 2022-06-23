package co.com.ias.handyman.infranstructure.repository;

import co.com.ias.handyman.infranstructure.entity.ReportEntity;
import co.com.ias.handyman.serviceTechnician.services.CreateServiceTechnicianService;

import java.util.List;

public class ReportRepository extends JpaRepository<CreateServiceTechnicianService , Integer> {
    public Object save(ReportEntity report) {
        return null;
    }

    public List<ReportEntity> findAll() {
        return null;
    }

    public void deleteById(Integer valueOf) {
    }
}
