package ecureuill.medapi.domain.appointment.validations.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ecureuill.medapi.domain.ValidationException;
import ecureuill.medapi.domain.appointment.AppointmentPostRecord;
import ecureuill.medapi.domain.appointment.AppointmentRepository;

@Service
public class SameDayPatientValidator implements AppointmentScheduleValidator {
    
    @Autowired
    AppointmentRepository repository;

    public void validate(AppointmentPostRecord record){

        Boolean exists = repository.existsByPatientIdAndDateBetweenAndCancellationReasonIsNull(record.idPatient(), record.date().withHour(7), record.date().withHour(0));

        if(exists)
            throw new ValidationException("The patient already has an appointment for this day");

    }
}
