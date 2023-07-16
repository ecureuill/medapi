package ecureuill.medapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ecureuill.medapi.domain.patient.PatientCreateRecord;
import ecureuill.medapi.domain.patient.PatientDetailRecord;
import ecureuill.medapi.domain.patient.PatientListRecord;
import ecureuill.medapi.domain.patient.PatientUpdateRecord;
import ecureuill.medapi.domain.patient.PatientRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {
    
    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PatientDetailRecord> save(@RequestBody @Valid PatientCreateRecord data, UriComponentsBuilder uriBuilder){
        var patient = repository.save(data.toEntity());
        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        var dto = new PatientDetailRecord(patient);

        return ResponseEntity.created(uri).body(dto);

    }

    @GetMapping
    public ResponseEntity<Page<PatientListRecord>> findAll(@PageableDefault(page=0, size=10, sort={"name"}) Pageable pageable){
        var page = repository.findAllByInactiveFalse(pageable).map(PatientListRecord::new);
        return ResponseEntity.ok(page);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PatientDetailRecord> findById(@PathVariable Long id){
        var patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailRecord(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity delete(@PathVariable Long id){
        var patient = repository.getReferenceById(id);
        patient.inactivate();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PatientDetailRecord> update(@PathVariable Long id, @RequestBody @Valid PatientUpdateRecord data){
        var pacient = repository.getReferenceById(id);
        pacient.update(data);

        return ResponseEntity.ok(new PatientDetailRecord(pacient));
    }
}
