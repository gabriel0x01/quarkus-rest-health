package quarkus.rest.health;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jboss.resteasy.reactive.RestResponse;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/patient")
public class PatientRegisterResource {
    List<PatientDTO> patients = new ArrayList<>();
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<PatientDTO> createPatient(PatientDTO patient) {
        if (patient == null) {
            PatientDTO responseClient = new PatientDTO();
            responseClient.setResponseMessage("Paciente não foi informado");
            return RestResponse.status(RestResponse.Status.BAD_REQUEST, responseClient);

        }

        patient.setId(UUID.randomUUID());
        patients.add(patient);

        return RestResponse.ok(patient);
    }

}
