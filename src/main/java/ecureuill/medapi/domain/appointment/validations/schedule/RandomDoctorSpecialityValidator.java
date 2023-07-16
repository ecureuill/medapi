package ecureuill.medapi.domain.appointment.validations.schedule;

import org.springframework.stereotype.Service;

import ecureuill.medapi.domain.ValidationException;
import ecureuill.medapi.domain.appointment.AppointmentPostRecord;

@Service
public class RandomDoctorSpecialityValidator implements AppointmentScheduleValidator {

    public void validate(AppointmentPostRecord record) {
        if(record.idDoctor() == null && record.speciality() == null)
            throw new ValidationException("Speciality is required when doctor was not selected");
    }
}
