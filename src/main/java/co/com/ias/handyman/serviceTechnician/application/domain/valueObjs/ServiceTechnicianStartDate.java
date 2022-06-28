package co.com.ias.handyman.serviceTechnician.application.domain.valueObjs;

import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

public class ServiceTechnicianStartDate {
    private final LocalDateTime value;
    static Calendar cal = Calendar.getInstance();

    public ServiceTechnicianStartDate(LocalDateTime value) {
        Validate.notNull(value, "The start date can not be null");
        cal.add(Calendar.DATE, -7);
        LocalDateTime auxDate = cal.getTime().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        Validate.isTrue(value.isAfter(auxDate), "The start date can not be before of seven days ago.");
        Validate.isTrue(value.isBefore(LocalDateTime.now()), "The start date can not be after of now.");
        this.value = value;
    }

    public LocalDateTime getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ServiceTechnicianStartDate{" +
                "value=" + value +
                '}';
    }
}
