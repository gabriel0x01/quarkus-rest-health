package quarkus.rest.health;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;
import java.util.UUID;

@Path("/api/v1/patient")
public class PatientRegisterResource {

    @Inject
    private PatientRegisterService patientRegisterService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<List<PatientDTO>> getPatient() {
        List<PatientDTO> patients = patientRegisterService.getPatients();

        return RestResponse.status(Response.Status.OK, patients);
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

        PatientDTO newPatient = patientRegisterService.createPatient(patient);

        return RestResponse.status(Response.Status.CREATED, newPatient);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<PatientDTO> updatePatient(PatientDTO patient) {
        if(patient == null) {
            PatientDTO responseClient = new PatientDTO();
            responseClient.setResponseMessage("Paciente não foi informado");

            return RestResponse.status(RestResponse.Status.BAD_REQUEST, responseClient);
        }

        if(patient.getId() == null) {
            PatientDTO responseClient = new PatientDTO();
            responseClient.setResponseMessage("Id do paciente não informado");
            return RestResponse.status(RestResponse.Status.BAD_REQUEST, responseClient);
        }

        PatientDTO updatedPatient = patientRegisterService.updateAllPatient(patient);

        if(updatedPatient == null) {
            PatientDTO responseClient = new PatientDTO();
            responseClient.setResponseMessage("Paciente não encontrado");

            return RestResponse.status(RestResponse.Status.NOT_FOUND, responseClient);
        }

        return RestResponse.status(RestResponse.Status.OK, updatedPatient);
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{patientId}")
    public RestResponse<PatientDTO> updatePartialPatient(@PathParam("patientId") UUID patientId, PatientDTO patient) {
        if(patient == null) {
            PatientDTO responseClient = new PatientDTO();
            responseClient.setResponseMessage("Paciente não informado");

            return RestResponse.status(RestResponse.Status.BAD_REQUEST, responseClient);
        }

        if(patientId == null) {
            PatientDTO responseClient = new PatientDTO();
            responseClient.setResponseMessage("Id do paciente não informado");

            return RestResponse.status(RestResponse.Status.BAD_REQUEST, responseClient);
        }

        PatientDTO updatedPatient = patientRegisterService.updatePartialPatient(patientId, patient);

        if(updatedPatient == null) {
            PatientDTO responseClient = new PatientDTO();
            responseClient.setResponseMessage("Paciente não encontrado");

            return RestResponse.status(RestResponse.Status.NOT_FOUND, responseClient);
        }

        return RestResponse.status(RestResponse.Status.OK, updatedPatient);
    }
}
