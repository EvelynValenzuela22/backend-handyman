package co.com.ias.handyman.infranstructure.controllers;

import co.com.ias.handyman.infranstructure.models.ApplicationError;
import co.com.ias.handyman.infranstructure.models.ServiceTechnicianDTO;
import co.com.ias.handyman.serviceTechnician.application.ports.input.CreateServiceTechnicianUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/service-technician")
@CrossOrigin("*")
public class ServiceTechnicianController {

    private final CreateServiceTechnicianUseCase createServiceTechnicianUseCase;

    public ServiceTechnicianController(CreateServiceTechnicianUseCase createServiceTechnicianUseCase) {
        this.createServiceTechnicianUseCase = createServiceTechnicianUseCase;
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody ServiceTechnicianDTO serviceTechnicianDTO) {
        try {
            ServiceTechnicianDTO output = createServiceTechnicianUseCase.execute(serviceTechnicianDTO);
            return ResponseEntity.status(CREATED).body(output);
        } catch (NullPointerException | IllegalArgumentException e) {
            ApplicationError applicationError = new ApplicationError(
                    "InputDataValidationError",
                    "Bad input data",
                    Map.of("error", e.getMessage())
            );
            return ResponseEntity.status(BAD_REQUEST).body(applicationError);
        } catch (Exception e) {
            ApplicationError applicationError = new ApplicationError(
                    "SystemError",
                    "Try more later",
                    Map.of()
            );
            System.out.println("Error......: " + e.getMessage());
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(applicationError);
        }
    }
}
