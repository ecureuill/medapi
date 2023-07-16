package ecureuill.medapi.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    
    public Address(AddressRecord address) {
        this.street = address.street();
        this.neighborhood = address.neighborhood();
        this.city = address.city();
        this.state = address.state();
        this.zip = address.zip();
        this.number = address.number();
        this.complement = address.complement();
    }
  
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String zip;
    private String number;
    private String complement;

    public void update(AddressRecord record) {
        if(record.street() != null)
            this.street = record.street();
        if(record.neighborhood() != null)
            this.neighborhood = record.neighborhood();
        if(record.city() != null)
            this.city = record.city();
        if(record.state() != null)
            this.state = record.state();
        if(record.zip() != null)
            this.zip = record.zip();
        if(record.number() != null)
            this.number = record.number();
        if(record.complement() != null)
            this.complement = record.complement();
        
    }
}
