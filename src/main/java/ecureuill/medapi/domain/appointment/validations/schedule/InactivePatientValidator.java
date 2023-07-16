package ecureuill.medapi.domain.appointment.validations.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecureuill.medapi.domain.appointment.AppointmentCreateRecord;
import ecureuill.medapi.domain.patient.PatientRepository;
import ecureuill.medapi.infra.error.ValidationException;

@Service
public class InactivePatientValidator implements AppointmentScheduleValidator {

    @Autowired
    private PatientRepository repository;
    
    public void validate(AppointmentCreateRecord record){
        var exists = repository.existsByIdAndInactiveFalse(record.idPatient());

         if(!exists)
            throw new ValidationException("Can not schedule appointment to inactive patient");
    }
}
