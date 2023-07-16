package ecureuill.medapi.domain.appointment.validations.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ecureuill.medapi.domain.appointment.AppointmentCreateRecord;
import ecureuill.medapi.domain.appointment.AppointmentRepository;
import ecureuill.medapi.infra.error.ValidationException;

@Service
public class SameDayPatientValidator implements AppointmentScheduleValidator {
    
    @Autowired
    AppointmentRepository repository;

    public void validate(AppointmentCreateRecord record){

        Boolean exists = repository.existsByPatientIdAndDateBetweenAndCancellationReasonIsNull(record.idPatient(), record.date().withHour(7), record.date().withHour(0));

        if(exists)
            throw new ValidationException("The patient already has an appointment for this day");

    }
}
