package ecureuill.medapi.domain.appointment.validations.schedule;

import ecureuill.medapi.domain.appointment.AppointmentCreateRecord;

public interface AppointmentScheduleValidator {
    
    void validate(AppointmentCreateRecord record);
}
