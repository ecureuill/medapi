package ecureuill.medapi.domain.appointment.validations.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecureuill.medapi.domain.appointment.AppointmentCreateRecord;
import ecureuill.medapi.domain.doctor.DoctorRepository;
import ecureuill.medapi.infra.error.ValidationException;

@Service
public class InactiveDoctorValidator implements AppointmentScheduleValidator {
    
    @Autowired
    private DoctorRepository repository;
    
    public void validate(AppointmentCreateRecord record){
        if(record.idDoctor() != null){

            var exists = repository.existsByIdAndInactiveFalse(record.idDoctor());
            
            if(!exists)
               throw new ValidationException("Can not schedule appointment to inactive patient");
        }
    }
}
