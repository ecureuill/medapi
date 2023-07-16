package ecureuill.medapi.domain.appointment;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Boolean existsByDoctorIdAndDateAndCancellationReasonIsNull(Long idDoctor, LocalDateTime date);

    Boolean existsByPatientIdAndDateBetweenAndCancellationReasonIsNull(Long idPatient, LocalDateTime withInitialHour, LocalDateTime withFinalHour);
    
}
