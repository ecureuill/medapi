package ecureuill.medapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ecureuill.medapi.domain.appointment.AppointmentDeleteRecord;
import ecureuill.medapi.domain.appointment.AppointmentDetailRecord;
import ecureuill.medapi.domain.appointment.AppointmentPostRecord;
import ecureuill.medapi.domain.appointment.AppointmentScheduler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {
    
    @Autowired
    private AppointmentScheduler scheduler;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AppointmentDetailRecord>  schedule(@RequestBody @Valid AppointmentPostRecord data) {
        var appointment = scheduler.to_schedule(data);
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity cancel(@RequestBody @Valid AppointmentDeleteRecord data) {
        scheduler.to_cancel(data);
        return ResponseEntity.noContent().build();
    }
}
