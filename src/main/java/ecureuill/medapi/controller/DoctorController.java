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

import ecureuill.medapi.domain.doctor.DoctorListRecord;
import ecureuill.medapi.domain.doctor.DoctorUpdateRecord;
import ecureuill.medapi.domain.doctor.DoctorCreateRecord;
import ecureuill.medapi.domain.doctor.DoctorDetailRecord;
import ecureuill.medapi.domain.doctor.DoctorRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DoctorDetailRecord> save(@RequestBody @Valid DoctorCreateRecord data, UriComponentsBuilder uriBuilder) {
        var doctor = repository.save(data.toEntity());
        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        var dto = new DoctorDetailRecord(doctor);

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListRecord>> findAll(@PageableDefault(size=10, page=0, sort={"name"}) Pageable pageable) {
        var page = repository.findAllByInactiveFalse(pageable).map(DoctorListRecord::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailRecord> findById(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailRecord(doctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDetailRecord> update(@RequestBody @Valid DoctorUpdateRecord data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.update(data);
        return ResponseEntity.ok(new DoctorDetailRecord(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();
        return ResponseEntity.noContent().build();
    }

}
