package ecureuill.medapi.domain.doctor;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByInactiveFalse(Pageable pageable);


    @Query("""
        select d from Doctor d 
        where
            d.inactive = false
            and 
            d.speciality = :speciality 
            and 
            d.id not in (
                select a.doctor.id from Appointment a
                where a.date = :date
            )
        order by rand()
        limit 1
    """)
    Doctor findBySpecialityAndNoAppointmentOnDate(Speciality speciality, LocalDateTime date);


    Boolean existsByIdAndInactiveFalse(Long idPatient);
    
}
