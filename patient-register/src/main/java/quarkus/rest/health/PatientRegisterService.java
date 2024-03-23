package quarkus.rest.health;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PatientRegisterService {

    List<PatientDTO> patients = new ArrayList<>();

    public List<PatientDTO> getPatients() {
        return patients;
    }

    public PatientDTO createPatient(PatientDTO patient) {
        patient.setId(UUID.randomUUID());
        patients.add(patient);
        return patient;
    }

    public PatientDTO updateAllPatient(PatientDTO patient) {
        for (PatientDTO p : patients) {
            if (p.getId().equals(patient.getId())) {
                p.setName(patient.getName());
                p.setEmail(patient.getEmail());
                p.setHealthInsurance(patient.getHealthInsurance());

                return p;
            }
        }
        return null;
    }

    public PatientDTO updatePartialPatient(UUID idpatient, PatientDTO patient) {
        for (PatientDTO p : patients) {
            if (p.getId().equals(idpatient)) {
                if (patient.getName() != null) {
                    p.setName(patient.getName());
                }

                if (patient.getEmail() != null) {
                    p.setEmail(patient.getEmail());
                }

                if (patient.getHealthInsurance() != null) {
                    p.setHealthInsurance(patient.getHealthInsurance());
                }

                return p;
            }
        }
        return null;
    }
}
