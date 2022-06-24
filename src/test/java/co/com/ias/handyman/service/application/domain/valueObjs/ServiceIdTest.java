package co.com.ias.handyman.service.application.domain.valueObjs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceIdTest {

    @Test
    @DisplayName("Null service id should throw an error")
    void null_service_id() {
        Long invalidId = null;
        assertThrows(NullPointerException.class, () -> new ServiceId(invalidId));
    }

    @Test
    @DisplayName("Valid service id should not throw an error")
    void valid_service_id() {
        Long validId = 2L;
        assertDoesNotThrow(() -> new ServiceId(validId));
    }


}
