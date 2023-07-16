package ecureuill.medapi.domain.appointment.validations.schedule;

import org.springframework.stereotype.Service;

import ecureuill.medapi.domain.appointment.AppointmentCreateRecord;
import ecureuill.medapi.infra.error.ValidationException;

@Service
public class RandomDoctorSpecialityValidator implements AppointmentScheduleValidator {

    public void validate(AppointmentCreateRecord record) {
        if(record.idDoctor() == null && record.speciality() == null)
            throw new ValidationException("Speciality is required when doctor was not selected");
    }
}
