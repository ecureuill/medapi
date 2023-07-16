package ecureuill.medapi.domain.patient;

import ecureuill.medapi.domain.address.Address;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String cpf;
    private String phone;
    private boolean inactive;

    @Embedded
    private Address address;

    public Patient(PatientCreateRecord data) {
        this.inactive = false;
        this.name = data.name();
        this.email = data.email();
        this.cpf = data.cpf();
        this.phone = data.phone();
        this.address = new Address(data.address());
    }

    public void inactivate() {
        this.inactive = true;
    }

    public void update(@Valid PatientUpdateRecord data) {
        this.name = data.name();
        this.phone = data.phone();
        this.address.update(data.address());
    }
}
