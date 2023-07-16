package ecureuill.medapi.domain.patient;

import ecureuill.medapi.domain.address.AddressRecord;

public record PatientDetailRecord( String name, String email, String phone, String cpf, AddressRecord address) {

    public PatientDetailRecord(Patient patient) {
        this(
            patient.getName(),
            patient.getEmail(),
            patient.getPhone(),
            patient.getCpf(),
            new AddressRecord(patient.getAddress())
        );
    }
    
}
