package ecureuill.medapi.domain.patient;

import ecureuill.medapi.domain.address.AddressRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatientCreateRecord(
    @NotBlank
    String name,
    @Email
    @NotBlank
    String email,
    @NotBlank
    @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}" )
    String cpf,
    @NotBlank
    String phone,
    @NotNull
    @Valid
    AddressRecord address
) {

    public Patient toEntity() {
        return new Patient(this);
    }
    
}
