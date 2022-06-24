package co.com.ias.handyman.service.application.domain.valueObjs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceTypeIdTest {

    @Test
    @DisplayName("Null service type id should throw an error")
    void null_service_type_id() {
        Long invalidTypeId = null;
        assertThrows(NullPointerException.class, () -> new ServiceTypeId(invalidTypeId));
    }

    @Test
    @DisplayName("Valid service id should not throw an error")
    void valid_service_type_id() {
        Long validTypeId = 2L;
        assertDoesNotThrow(() -> new ServiceTypeId(validTypeId));
    }

}
