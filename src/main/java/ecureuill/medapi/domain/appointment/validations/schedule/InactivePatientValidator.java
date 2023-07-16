package ecureuill.medapi.domain.appointment.validations.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecureuill.medapi.domain.ValidationException;
import ecureuill.medapi.domain.appointment.AppointmentPostRecord;
import ecureuill.medapi.domain.patient.PatientRepository;

@Service
public class InactivePatientValidator implements AppointmentScheduleValidator {

    @Autowired
    private PatientRepository repository;
    
    public void validate(AppointmentPostRecord record){
        var exists = repository.existsByIdAndInactiveFalse(record.idPatient());

         if(!exists)
            throw new ValidationException("Can not schedule appointment to inactive patient");
    }
}
