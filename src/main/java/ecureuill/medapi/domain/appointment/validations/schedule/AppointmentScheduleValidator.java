package ecureuill.medapi.domain.appointment.validations.schedule;

import ecureuill.medapi.domain.appointment.AppointmentPostRecord;

public interface AppointmentScheduleValidator {
    
    void validate(AppointmentPostRecord record);
}
