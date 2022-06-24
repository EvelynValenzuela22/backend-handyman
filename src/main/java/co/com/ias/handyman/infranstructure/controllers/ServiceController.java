package co.com.ias.handyman.infranstructure.controllers;

import co.com.ias.handyman.infranstructure.models.ApplicationError;
import co.com.ias.handyman.infranstructure.models.ServiceDTO;
import co.com.ias.handyman.service.application.services.QueryServiceByIdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final QueryServiceByIdService queryServiceByIdService;

    public ServiceController(QueryServiceByIdService queryServiceByIdService) {
        this.queryServiceByIdService = queryServiceByIdService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            Optional<ServiceDTO> serviceDTO = queryServiceByIdService.execute(id);
            if (serviceDTO.isPresent()) {
                return ResponseEntity.ok(serviceDTO.get());
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No exist service with this id");
            }
        } catch (NullPointerException | IllegalArgumentException exception) {
            ApplicationError applicationError = new ApplicationError("InputDataValidationError", "Bad input data",
                    Map.of("error", exception.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception exception) {
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }
    }
}
