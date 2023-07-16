package ecureuill.medapi.domain.appointment.validations.schedule;

import java.time.DayOfWeek;

import org.springframework.stereotype.Service;

import ecureuill.medapi.domain.appointment.AppointmentCreateRecord;
import ecureuill.medapi.infra.error.ValidationException;

@Service
public class TimetableValidator implements AppointmentScheduleValidator {

    public void validate(AppointmentCreateRecord record){

        var sunday = record.date().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var initialHour = record.date().getHour() < 7;
        var finalHour = record.date().getHour() > 18;
            
        if(sunday || initialHour || finalHour)
            throw new ValidationException("Appointment must be schedulled during business hours (7am-7pm Sun-Sat)");
    }
    
}
