package ecureuill.medapi.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentDeleteRecord(
    @NotNull
    Long id,
    @NotNull
    CancellationReason reason
) {
    
}
