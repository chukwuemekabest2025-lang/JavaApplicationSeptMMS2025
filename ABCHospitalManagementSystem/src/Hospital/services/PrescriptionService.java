package hospital.services;

import hospital.dao.PrescriptionDAO;
import hospital.models.Prescription;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionService {

    private final PrescriptionDAO prescriptionDAO;

    public PrescriptionService() {
        this.prescriptionDAO =
                new PrescriptionDAO();
    }

    public boolean addPrescription(
            Prescription prescription) {

        if (prescription == null) {

            System.err.println(
                    "Validation Error: Prescription object cannot be null."
            );
            return false;
        }

        if (prescription.getPatient() == null
                || prescription.getPatient().getPatientId() <= 0) {

            System.err.println(
                    "Validation Error: A valid patient must be assigned."
            );
            return false;
        }

        if (prescription.getDoctor() == null
                || prescription.getDoctor().getStaffId() <= 0) {

            System.err.println(
                    "Validation Error: A valid doctor must be assigned."
            );
            return false;
        }

        if (prescription.getPrescriptionDate() == null) {

            prescription.setPrescriptionDate(
                    LocalDate.now()
            );
        }

        return prescriptionDAO
                .addPrescription(prescription);
    }

    public List<Prescription> getAllPrescriptions() {

        return prescriptionDAO
                .findAllPrescriptions();
    }

    public Prescription getPrescriptionById(
            int prescriptionId) {

        if (prescriptionId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Prescription ID."
            );
            return null;
        }

        return prescriptionDAO
                .findPrescriptionById(prescriptionId);
    }

    public List<Prescription>
            getPrescriptionsByPatientId(
                    int patientId) {

        if (patientId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Patient ID."
            );
            return null;
        }

        return prescriptionDAO
                .findPrescriptionsByPatientId(
                        patientId
                );
    }

    public List<Prescription>
            getPrescriptionsByDoctorId(
                    int doctorId) {

        if (doctorId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Doctor ID."
            );
            return null;
        }

        return prescriptionDAO
                .findPrescriptionsByDoctorId(
                        doctorId
                );
    }

    public boolean updatePrescription(
            Prescription prescription) {

        if (prescription == null) {

            System.err.println(
                    "Validation Error: Prescription object cannot be null."
            );
            return false;
        }

        if (prescription.getId() <= 0) {

            System.err.println(
                    "Validation Error: Invalid Prescription ID."
            );
            return false;
        }

        if (prescription.getPatient() == null
                || prescription.getPatient().getPatientId() <= 0) {

            System.err.println(
                    "Validation Error: A valid patient must be assigned."
            );
            return false;
        }

        if (prescription.getDoctor() == null
                || prescription.getDoctor().getStaffId() <= 0) {

            System.err.println(
                    "Validation Error: A valid doctor must be assigned."
            );
            return false;
        }

        if (prescription.getPrescriptionDate() == null) {

            prescription.setPrescriptionDate(
                    LocalDate.now()
            );
        }

        return prescriptionDAO
                .updatePrescription(prescription);
    }

    public boolean removePrescription(
            int prescriptionId) {

        if (prescriptionId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Prescription ID."
            );
            return false;
        }

        return prescriptionDAO
                .deletePrescription(prescriptionId);
    }
}
