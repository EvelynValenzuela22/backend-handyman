package co.com.ias.handyman.serviceTechnician.application.domain.valueObjs;

import co.com.ias.handyman.technician.application.domain.valueObjs.TechnicianDocumentNumber;
import co.com.ias.handyman.technician.application.domain.valueObjs.TechnicianFirstName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceTechnicianDocumentNumberTest {
    @Test
    @DisplayName("Null DocumentNumber  should throw an error")
    void null_documentNumber() {
        String invalidateDocumentNumber = null;
        assertThrows(NullPointerException.class, () -> {
            new TechnicianDocumentNumber((invalidateDocumentNumber));
        });
    }
    @Test
    @DisplayName("Can't be no longer to 20 characters")
    void longerDocumentNumber(){
        String longDocumentNumber = "this is a test where the DocumentNumber can't be long than 20 characters";
        assertThrows(IllegalArgumentException.class, () ->{
            new TechnicianDocumentNumber(longDocumentNumber);
        });
    }
}
