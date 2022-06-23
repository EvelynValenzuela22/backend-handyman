package co.com.ias.handyman.infranstructure.models;

import co.com.ias.handyman.technician.application.domain.Technician;
import co.com.ias.handyman.technician.application.domain.valueObjs.*;

public class TechnicianDTO {
    private Long id;
    private String document_type;
    private String document_number;
    private String first_name;
    private String last_name;

    public TechnicianDTO(Long id, String document_type, String document_number, String first_name, String last_name) {
        this.id = id;
        this.document_type = document_type;
        this.document_number = document_number;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public TechnicianDTO() {
    }

    public Technician toDomain() {
        return new Technician(
                new TechnicianId(id),
                new DocumentTypeTechnician(document_type),
                new DocumentNumberTechnician(document_number),
                new FirstNameTechnician(first_name),
                new LastNameTechnician(last_name)
        );
    }

    public TechnicianDTO fromDomain(Technician technician) {
        TechnicianDTO technicianDTO = new TechnicianDTO();
        technicianDTO.setId(technician.getId().getValue());
        technicianDTO.setDocument_type(technician.getDocumentType().getValue());
        technicianDTO.setDocument_number(technician.getDocumentNumber().getValue());
        technicianDTO.setFirst_name(technician.getFirstName().getValue());
        technicianDTO.setLast_name(technician.getLastName().getValue());
        return  technicianDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public String getDocument_number() {
        return document_number;
    }

    public void setDocument_number(String document_number) {
        this.document_number = document_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "TechnicianDTO{" +
                "id=" + id +
                ", document_type='" + document_type + '\'' +
                ", document_number='" + document_number + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
