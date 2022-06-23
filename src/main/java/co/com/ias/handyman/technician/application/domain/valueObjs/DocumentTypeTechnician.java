package co.com.ias.handyman.technician.application.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class DocumentTypeTechnician {
    private final String value;

    public DocumentTypeTechnician(String value) {
        Validate.notNull(value, "Technician's document type can not be null");
        Validate.isTrue(value.length() <= 20, "Technician's document type can not be longer than 20 characters");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DocumentTypeTechnician{" +
                "value='" + value + '\'' +
                '}';
    }
}
