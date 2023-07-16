package ecureuill.medapi.domain.appointment.validations.cancel;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecureuill.medapi.domain.appointment.AppointmentDeleteRecord;
import ecureuill.medapi.domain.appointment.AppointmentRepository;
import ecureuill.medapi.infra.error.ValidationException;

@Service
public class CancelInAdvance implements AppointmentCancellationValidator {
    
    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentDeleteRecord record){
        var appointment = repository.findById(record.id()).get();

        if(appointment != null){
            var duration = Duration.between(appointment.getDate(), LocalDateTime.now());
            
            if(duration.toHours() < 24)
               throw new ValidationException("The appointment can only be canceled 24 hours in advance");
        }
    }

    
}
