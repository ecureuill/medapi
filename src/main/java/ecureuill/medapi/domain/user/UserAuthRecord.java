package ecureuill.medapi.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UserAuthRecord(
    @NotBlank
    String username, 
    @NotBlank
    String password
) {
    
}
