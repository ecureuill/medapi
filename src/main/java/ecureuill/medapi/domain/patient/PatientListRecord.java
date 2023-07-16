package ecureuill.medapi.domain.patient;

public record PatientListRecord(Long id, String name, String email, String cpf) {

    public PatientListRecord(Patient pacient) {
        this(
            pacient.getId(),
            pacient.getName(),
            pacient.getEmail(),
            pacient.getCpf()
        );
    }
    
}
