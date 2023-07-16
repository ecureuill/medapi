package ecureuill.medapi.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailRecord(
    Long id,
    Long idDoctor,
    Long idPatient,
    LocalDateTime date
){}
