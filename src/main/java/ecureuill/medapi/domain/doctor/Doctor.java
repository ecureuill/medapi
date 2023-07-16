package ecureuill.medapi.domain.doctor;

import org.springframework.boot.context.properties.bind.DefaultValue;

import ecureuill.medapi.domain.address.Address;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String crm;
    private String phone;
    private Boolean inactive;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Embedded
    private Address address;

    public Doctor(DoctorCreateRecord data) {
        this.inactive = false;
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.crm = data.crm();
        this.speciality = data.speciality();
        this.address = new Address(data.address());
    }

    public void update(DoctorPutRecord record) {
        if(record.name() != null)
            this.name = record.name();
        if(record.phone()!= null)
            this.phone = record.phone();
        if(record.address()!= null)
            this.address.update(record.address());
    }

    public void delete() {
        this.inactive = true;
    }
}
