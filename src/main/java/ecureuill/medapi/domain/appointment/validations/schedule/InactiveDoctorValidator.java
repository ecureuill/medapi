package ecureuill.medapi.domain.appointment.validations.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecureuill.medapi.domain.ValidationException;
import ecureuill.medapi.domain.appointment.AppointmentPostRecord;
import ecureuill.medapi.domain.doctor.DoctorRepository;

@Service
public class InactiveDoctorValidator implements AppointmentScheduleValidator {
    
    @Autowired
    private DoctorRepository repository;
    
    public void validate(AppointmentPostRecord record){
        if(record.idDoctor() != null){

            var exists = repository.existsByIdAndInactiveFalse(record.idDoctor());
            
            if(!exists)
               throw new ValidationException("Can not schedule appointment to inactive patient");
        }
    }
}
