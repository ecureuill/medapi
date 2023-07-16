package ecureuill.medapi.domain.doctor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ecureuill.medapi.AbstractSpringTest;
import ecureuill.medapi.domain.address.Address;
import ecureuill.medapi.domain.address.AddressRecord;
import ecureuill.medapi.domain.appointment.Appointment;
import ecureuill.medapi.domain.patient.Patient;
import ecureuill.medapi.domain.patient.PatientRecord;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DoctorRepositoryTest extends AbstractSpringTest {
    
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;
    
    @Test
    @DisplayName("Should return null when the only doctor in database is not available in the date")
    void testFindBySpecialityAndNoAppointmentOnDateScenary1() {

        var nextMondayOn10am = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var medico = createDoctor("Medico", "medico@voll.med", "123456", Speciality.CARDIOLOGIA);
        var paciente = createPatient("Paciente", "paciente@email.com", "00000000000");
        createAppointment(medico, paciente, nextMondayOn10am);

        var result = doctorRepository.findBySpecialityAndNoAppointmentOnDate(Speciality.CARDIOLOGIA, nextMondayOn10am);

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Should return doctor when he is available in the date")
    void testFindBySpecialityAndNoAppointmentOnDateScenary2() {

        var nextMondayOn10am = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var doctor = createDoctor("Medico", "medico@voll.med", "123456", Speciality.CARDIOLOGIA);
        createPatient("Paciente", "paciente@email.com", "00000000000");

        var result = doctorRepository.findBySpecialityAndNoAppointmentOnDate(Speciality.CARDIOLOGIA, nextMondayOn10am);

        assertThat(result).isEqualTo(doctor);
    }

 
    private void createAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(null, doctor, patient, date, null));
    }

    private Doctor createDoctor(String name, String email, String crm, Speciality speciality) {
        var doctor = new Doctor(doctorRecord(name, email, crm, speciality));
        em.persist(doctor);
        return doctor;
    }

    private Patient createPatient(String name, String email, String cpf) {
        var patient = patientRecord(name, email, cpf).toEntity();
        em.persist(patient);
        return patient;
    }

    private DoctorCreateRecord doctorRecord(String name, String email, String crm, Speciality speciality) {
        return new DoctorCreateRecord(
                name,
                email,
                crm,
                speciality,
                addressRecord(),
                "61999999999"
        );
    }

    private PatientRecord patientRecord(String name, String email, String cpf) {
        return new PatientRecord(
                name,
                email,
                cpf,
                "61999999999",
                addressRecord()
        );
    }

    private AddressRecord addressRecord() {
        return new AddressRecord(
                "rua xpto",
                "bairro",
                "Brasilia",
                "DF",
                "00000000",
                null,
                null
        );
    }
}
