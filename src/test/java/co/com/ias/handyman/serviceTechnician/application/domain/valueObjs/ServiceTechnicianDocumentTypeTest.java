package co.com.ias.handyman.serviceTechnician.application.domain.valueObjs;

import co.com.ias.handyman.technician.application.domain.valueObjs.TechnicianDocumentType;
import co.com.ias.handyman.technician.application.domain.valueObjs.TechnicianFirstName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceTechnicianDocumentTypeTest {
    @Test
    @DisplayName("Null DocumentType  should throw an error")
    void null_documentType() {
        String invalidateDocumentType = null;
        assertThrows(NullPointerException.class, () -> {
            new TechnicianDocumentType((invalidateDocumentType));
        });
    }
    @Test
    @DisplayName("Can't be no longer to 20 characters")
    void longerTechnicianDocumentType(){
        String longDocumentType = "this is a test where the DocumentType can't be long than 20 characters";
        assertThrows(IllegalArgumentException.class, () ->{
            new TechnicianDocumentType(longDocumentType);
        });
    }
}
