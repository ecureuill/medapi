package ecureuill.medapi.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import ecureuill.medapi.domain.appointment.AppointmentDetailRecord;
import ecureuill.medapi.domain.appointment.AppointmentPostRecord;
import ecureuill.medapi.domain.appointment.AppointmentScheduler;
import ecureuill.medapi.domain.doctor.Speciality;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AppointmentControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AppointmentPostRecord> jsonRequest;

    @Autowired
    private JacksonTester<AppointmentDetailRecord> jsonResult;

    @MockBean
    private AppointmentScheduler appointmentScheduler;

    @Test
    @DisplayName("Should return HTTP 403 when no JWT header was provided")
    void testScheduleScenary403() throws Exception {
        var response = mockMvc.perform(post("/appointments"))
            .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
        
    }

    @Test
    @WithMockUser
    @DisplayName("Should return HTTP 400 when request has invalid data")
    void testScheduleScenary400() throws Exception {
        var response = mockMvc
            .perform(post("/appointments"))
            .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        
    }

    @Test
    @WithMockUser
    @DisplayName("Should return HTTP 200 when request has valid data")
    void testScheduleScenary200() throws Exception {
        
        var date =  LocalDateTime.now().plusHours(1);

        var detailRecord = new AppointmentDetailRecord(null, 2l, 5l, date);

        when(appointmentScheduler.to_schedule(any())).thenReturn(detailRecord);
        
        var response = mockMvc
            .perform(
                post("/appointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest.write(
                    new AppointmentPostRecord(2l, 5l, date, Speciality.CARDIOLOGIA)
                ).getJson())
            )
            .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        
        var expectedJson = jsonResult.write(detailRecord).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}
