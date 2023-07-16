package ecureuill.medapi.domain.appointment;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import ecureuill.medapi.domain.doctor.Speciality;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record AppointmentCreateRecord(
    Long idDoctor,

    @NotNull
    Long idPatient,

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime date,
    
    Speciality speciality
) {
    
}
