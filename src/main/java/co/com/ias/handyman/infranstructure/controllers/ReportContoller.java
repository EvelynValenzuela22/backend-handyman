package co.com.ias.handyman.infranstructure.controllers;

import co.com.ias.handyman.infranstructure.entity.ReportEntity;
import co.com.ias.handyman.infranstructure.models.TechnicianDAO;
import co.com.ias.handyman.infranstructure.repository.ReportRepository;
import co.com.ias.handyman.service.application.domain.valueObjs.TimeWorked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/api")
public class ReportContoller {
    @Autowired
    private ReportRepository reportRepository;

    @PostMapping("/reports")
    public ResponseEntity createReport(@RequestBody ReportEntity report)throws ParseException{
        Date startDate = TimeWorked.formatDate(report.getStartDate());
        Date endDate = TimeWorked.formatDate(report.getEndDate());
        if(startDate.before(endDate)) {
            report.setWeek(TimeWorked.calculateWeek(startDate));
            report.setTotalHoursService(TimeWorked.calculateHourService(startDate, endDate));
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(reportRepository.save(report));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Start date cannot be greater than end date");
    }

    @GetMapping("/report")
    public List<ReportEntity> findAll() { return reportRepository.findAll(); }

    @GetMapping("/serviceById")
    public List<ReportEntity> findByIdHandyman(@RequestParam String idHandyman, @RequestParam int week) {
        List<ReportEntity> results = findAll();
        List<ReportEntity> finalList = new ArrayList<>();
        for(ReportEntity rs: results) {
            if(rs.getIdTechnician().equals(idHandyman) && rs.getWeek() == week){
                finalList.add(rs);
            }
        }
        return finalList;
    }

    @DeleteMapping("/report/{id}")
    public void deleteProduct(@PathVariable("id") String id) {

        reportRepository.deleteById(Integer.valueOf(id));
    }

    @GetMapping("/results")
    public List<ReportEntity> findHours(@RequestParam String idHandyman, @RequestParam int week) throws ParseException{
        List<ReportEntity>  results = findByIdHandyman(idHandyman, week);
        List<ReportEntity> finalList = new ArrayList<>();
        TechnicianDAO finalReport = new TechnicianDAO();

        int sumhorasDomingo= 0;
        int sumhorasTotales = 0;
        int sumhorasNocturnas = 0;
        int sumhorasExtras = 0;
        int sumhorasExtrasDomingo = 0;
        int sumhorasExtrasNocturnas = 0;
        int sumhorasNormales = 0;

        for (ReportEntity rs : results) {
            Date StartDate = TimeWorked.formatDate(rs.getStartDate());
            Date EndDate = TimeWorked.formatDate(rs.getEndDate());
            sumhorasNormales += TimeWorked.calculateHorasComunes(StartDate, EndDate);
            sumhorasTotales += TimeWorked.calculateHourService(StartDate, EndDate);
            sumhorasDomingo += TimeWorked.calculateHorasDominicales(StartDate,EndDate,sumhorasDomingo);
            sumhorasNocturnas += TimeWorked.calculateHorasNocturnas(StartDate, EndDate);
            sumhorasExtras += TimeWorked.calculateHorasExtras(sumhorasNormales);
            sumhorasExtras += TimeWorked.calculateHorasExtrasDomingo(StartDate, EndDate,sumhorasExtrasDomingo);
            sumhorasExtrasNocturnas += TimeWorked.calculateHorasExtrasNocturnas(StartDate,EndDate,sumhorasExtrasNocturnas);

            finalReport.setIdTechnician(idHandyman);
            finalReport.setWeek(TimeWorked.calculateWeek(StartDate));
        }
        finalReport.setHorasNormalesExtra(sumhorasExtras);
        finalReport.setHorasDomingo(sumhorasDomingo);
        finalReport.setHorasDomingoExtra(sumhorasExtrasDomingo);
        finalReport.setHorasNormalesExtra(sumhorasExtrasNocturnas);
        finalReport.setHorasNocturnas(sumhorasNocturnas-sumhorasExtrasNocturnas);
        finalReport.setTotalHoras( sumhorasTotales);
        finalReport.setHorasNormales(sumhorasNormales-sumhorasExtras-sumhorasExtrasDomingo-sumhorasExtrasNocturnas-sumhorasNocturnas);

        finalList.add(finalReport);
        return finalList;
    }
}






