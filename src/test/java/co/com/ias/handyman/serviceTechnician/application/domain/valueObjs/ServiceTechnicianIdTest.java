package co.com.ias.handyman.serviceTechnician.application.domain.valueObjs;

import co.com.ias.handyman.service.application.domain.valueObjs.ServiceUserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceTechnicianIdTest {
    @Test
    @DisplayName("Null user id service should throw an error")
    void null_service_user_id() {
        Long invalidUserId = null;
        assertThrows(NullPointerException.class, () -> new ServiceUserId(invalidUserId));
    }

    @Test
    @DisplayName("Valid user id service should not throw an error")
    void valid_service_user_id() {
        Long validUserdId = 2L;
        assertDoesNotThrow(() -> new ServiceUserId(validUserdId));
    }
}
