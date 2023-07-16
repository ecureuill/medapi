package ecureuill.medapi.domain.appointment.validations.schedule;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import ecureuill.medapi.domain.ValidationException;
import ecureuill.medapi.domain.appointment.AppointmentPostRecord;

@Service
public class ScheduleInAdvanceValidator implements AppointmentScheduleValidator {
    
    public void validate(AppointmentPostRecord record){
        if(Duration.between(LocalDateTime.now(), record.date()).toMinutes() < 30)
            throw new ValidationException("The appointment must be scheduled at least 30 minutes in advance");
    }
}
