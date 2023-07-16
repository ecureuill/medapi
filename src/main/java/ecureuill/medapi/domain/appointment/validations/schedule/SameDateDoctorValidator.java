package ecureuill.medapi.domain.appointment.validations.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecureuill.medapi.domain.appointment.AppointmentCreateRecord;
import ecureuill.medapi.domain.appointment.AppointmentRepository;
import ecureuill.medapi.infra.error.ValidationException;

@Service
public class SameDateDoctorValidator implements AppointmentScheduleValidator {

    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentCreateRecord record) {
        if(record.idDoctor() != null){
            Boolean exists = repository.existsByDoctorIdAndDateAndCancellationReasonIsNull(record.idDoctor(), record.date());
            
            if(exists)
               throw new ValidationException("The doctor already has an appointment for this date");
        }
    }
}
