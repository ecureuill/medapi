package ecureuill.medapi.domain.doctor;

import ecureuill.medapi.domain.address.AddressRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorCreateRecord(
    @NotBlank(message = "{name.required}")
    String name,
    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalid}")
    String email,
    @NotBlank(message = "{crm.required}")
    @Pattern(regexp = "\\d{4,6}", message = "{crm.invalid}")
    String crm,
    @NotNull(message = "{speciality.required}")
    Speciality speciality,
    @NotNull(message = "{address.required}")
    @Valid
    AddressRecord address,
    @NotBlank(message = "{phone.required}")
    String phone
) {
    
    public Doctor toEntity() {
        return new Doctor(this);
    }
}
