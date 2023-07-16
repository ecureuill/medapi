package ecureuill.medapi.domain.appointment.validations.cancel;

import ecureuill.medapi.domain.appointment.AppointmentDeleteRecord;

public interface AppointmentCancellationValidator {
    void validate(AppointmentDeleteRecord record);
}
