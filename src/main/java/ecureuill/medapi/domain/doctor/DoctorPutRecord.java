package ecureuill.medapi.domain.doctor;

import ecureuill.medapi.domain.address.AddressRecord;
import jakarta.validation.constraints.NotNull;

public record DoctorPutRecord(
    @NotNull
    Long id,
    String name, 
    String phone, 
    AddressRecord address
) {
    
}
