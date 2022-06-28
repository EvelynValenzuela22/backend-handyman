package co.com.ias.handyman.serviceTechnician.application.domain.valueObjs;

import co.com.ias.handyman.technician.application.domain.valueObjs.TechnicianFirstName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceTechnicianFirstNameTest {

    @Test
    @DisplayName("Null FirstName  should throw an error")
    void null_firsName() {
        String invalidateFirstName = null;
        assertThrows(NullPointerException.class, () -> {
            new TechnicianFirstName((invalidateFirstName));
        });
    }
    @Test
    @DisplayName("Can't be no longer to 20 characters")
    void longerLastName(){
        String longFirstName = "this is a test where the firstName can't be long than 20 characters";
        assertThrows(IllegalArgumentException.class, () ->{
            new TechnicianFirstName(longFirstName);
        });
    }
}
