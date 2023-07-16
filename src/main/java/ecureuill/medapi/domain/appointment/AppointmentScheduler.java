package ecureuill.medapi.domain.appointment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecureuill.medapi.domain.ValidationException;
import ecureuill.medapi.domain.appointment.validations.cancel.AppointmentCancellationValidator;
import ecureuill.medapi.domain.appointment.validations.schedule.AppointmentScheduleValidator;
import ecureuill.medapi.domain.doctor.Doctor;
import ecureuill.medapi.domain.doctor.DoctorRepository;
import ecureuill.medapi.domain.patient.PatientRepository;
import jakarta.validation.Valid;

@Service
public class AppointmentScheduler {
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private List<AppointmentScheduleValidator> scheduleValidators;
   
    @Autowired
    private List<AppointmentCancellationValidator> cancelValidators;

    public AppointmentDetailRecord to_schedule(AppointmentPostRecord data){

        if(!patientRepository.existsById(data.idPatient()))
            throw new ValidationException("Patient not found");

        if(data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor()))
            throw new ValidationException("Doctor not found");

        scheduleValidators.forEach(validator -> validator.validate(data));
        
        var patient = patientRepository.findById(data.idPatient()).get();
        var doctor = chooseDoctor(data);
        var appointment = new Appointment(null, doctor, patient, data.date(), null);

        appointment = appointmentRepository.save(appointment);
        
        return new AppointmentDetailRecord(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDate());
    }

    private Doctor chooseDoctor(AppointmentPostRecord data) {
        if(data.idDoctor() != null)
            return doctorRepository.getReferenceById(data.idDoctor());

        var doctor = doctorRepository.findBySpecialityAndNoAppointmentOnDate(data.speciality(), data.date());
        
        if(doctor == null)
            throw new ValidationException("There isn´t doctor available for this date and speciality");
            
        return doctor;
    }

    public void to_cancel(@Valid AppointmentDeleteRecord data) {
        
        if(!appointmentRepository.existsById(data.id()))
            throw new ValidationException("Appointment not found");

        cancelValidators.forEach(validator -> validator.validate(data));

        var appointment = appointmentRepository.findById(data.id()).get();
        appointment.cancel(data.reason());

    }

}
