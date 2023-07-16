package ecureuill.medapi.domain.doctor;

import ecureuill.medapi.domain.address.AddressRecord;

public record DoctorDetailRecord(
    String name,
    String email,
    String crm,
    Speciality speciality,
    AddressRecord address,
    String phone
) {

    public DoctorDetailRecord(Doctor doctor) {
        this(
            doctor.getName(),
            doctor.getEmail(),
            doctor.getCrm(),
            doctor.getSpeciality(),
            new AddressRecord(doctor.getAddress()),
            doctor.getPhone()
        );
    }
    
}
