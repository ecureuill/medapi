package ecureuill.medapi.domain.appointment.validations.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecureuill.medapi.domain.ValidationException;
import ecureuill.medapi.domain.appointment.AppointmentPostRecord;
import ecureuill.medapi.domain.appointment.AppointmentRepository;

@Service
public class SameDateDoctorValidator implements AppointmentScheduleValidator {

    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentPostRecord record) {
        if(record.idDoctor() != null){
            Boolean exists = repository.existsByDoctorIdAndDateAndCancellationReasonIsNull(record.idDoctor(), record.date());
            
            if(exists)
               throw new ValidationException("The doctor already has an appointment for this date");
        }
    }
}
