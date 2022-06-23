package co.com.ias.handyman.technician.application.domain;

import co.com.ias.handyman.technician.application.domain.valueObjs.*;

public class Technician {
    private final TechnicianId id;
    private final DocumentTypeTechnician documentType;
    private final DocumentNumberTechnician documentNumber;
    private final FirstNameTechnician firstName;
    private final LastNameTechnician lastName;

    public Technician(TechnicianId id, DocumentTypeTechnician documentType, DocumentNumberTechnician documentNumber, FirstNameTechnician firstName, LastNameTechnician lastName) {
        this.id = id;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public TechnicianId getId() {
        return id;
    }

    public DocumentTypeTechnician getDocumentType() {
        return documentType;
    }

    public DocumentNumberTechnician getDocumentNumber() {
        return documentNumber;
    }

    public FirstNameTechnician getFirstName() {
        return firstName;
    }

    public LastNameTechnician getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Technician{" +
                "id=" + id +
                ", documentType=" + documentType +
                ", documentNumber=" + documentNumber +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                '}';
    }
}
