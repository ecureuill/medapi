package ecureuill.medapi.domain.patient;

import ecureuill.medapi.domain.address.AddressRecord;
import jakarta.validation.Valid;

public record PatientUpdateRecord(
    String name,
    String phone, 
    @Valid AddressRecord address
) {

}
