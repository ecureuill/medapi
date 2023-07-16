package ecureuill.medapi.domain.doctor;

public record DoctorListRecord(Long id, String name, String email, String crm, Speciality speciality) {
    
    public DoctorListRecord(Doctor doctor) {
        this(
            doctor.getId(),
            doctor.getName(),
            doctor.getEmail(),
            doctor.getCrm(),
            doctor.getSpeciality()
        );
    }
}
