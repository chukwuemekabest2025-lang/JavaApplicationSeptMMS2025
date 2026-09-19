package hospital.services;

import hospital.dao.MedicationDispensingDAO;
import hospital.models.MedicationDispensing;

import java.time.LocalDateTime;
import java.util.List;

public class MedicationDispensingService {

    private final MedicationDispensingDAO medicationDispensingDAO;

    public MedicationDispensingService() {
        this.medicationDispensingDAO =
                new MedicationDispensingDAO();
    }

    public boolean dispenseMedication(
            MedicationDispensing dispensing) {

        if (dispensing == null) {
            System.err.println(
                    "Validation Error: Medication dispensing object cannot be null."
            );
            return false;
        }

        if (dispensing.getPrescription() == null
                || dispensing.getPrescription().getId() <= 0) {

            System.err.println(
                    "Validation Error: A valid prescription must be assigned."
            );
            return false;
        }

        if (dispensing.getPrescriptionItem() == null
                || dispensing.getPrescriptionItem().getId() <= 0) {

            System.err.println(
                    "Validation Error: A valid prescription item must be assigned."
            );
            return false;
        }

        if (dispensing.getPharmacist() == null
                || dispensing.getPharmacist().getPharmacistId() <= 0) {

            System.err.println(
                    "Validation Error: A valid pharmacist must be assigned."
            );
            return false;
        }

        if (dispensing.getPatient() == null
                || dispensing.getPatient().getPatientId() <= 0) {

            System.err.println(
                    "Validation Error: A valid patient must be assigned."
            );
            return false;
        }

        if (dispensing.getDispensingDate() == null) {
            dispensing.setDispensingDate(
                    LocalDateTime.now()
            );
        }

        if (dispensing.getQuantity() <= 0) {

            System.err.println(
                    "Validation Error: Dispensing quantity must be greater than zero."
            );
            return false;
        }

        if (dispensing.getStatus() == null
                || dispensing.getStatus().trim().isEmpty()) {

            dispensing.setStatus("Dispensed");
        }

        return medicationDispensingDAO
                .addMedicationDispensing(dispensing);
    }

    public List<MedicationDispensing>
            getAllMedicationDispensings() {

        return medicationDispensingDAO
                .findAllMedicationDispensings();
    }

    public MedicationDispensing
            getMedicationDispensingById(int dispensingId) {

        if (dispensingId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Medication Dispensing ID."
            );
            return null;
        }

        return medicationDispensingDAO
                .findMedicationDispensingById(dispensingId);
    }

    public List<MedicationDispensing>
            getMedicationDispensingsByPatientId(
                    int patientId) {

        if (patientId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Patient ID."
            );
            return null;
        }

        return medicationDispensingDAO
                .findMedicationDispensingsByPatientId(
                        patientId
                );
    }

    public boolean updateMedicationDispensing(
            MedicationDispensing dispensing) {

        if (dispensing == null) {

            System.err.println(
                    "Validation Error: Medication dispensing object cannot be null."
            );
            return false;
        }

        if (dispensing.getId() <= 0) {

            System.err.println(
                    "Validation Error: Invalid Medication Dispensing ID."
            );
            return false;
        }

        if (dispensing.getPrescription() == null
                || dispensing.getPrescription().getId() <= 0) {

            System.err.println(
                    "Validation Error: A valid prescription must be assigned."
            );
            return false;
        }

        if (dispensing.getPrescriptionItem() == null
                || dispensing.getPrescriptionItem().getId() <= 0) {

            System.err.println(
                    "Validation Error: A valid prescription item must be assigned."
            );
            return false;
        }

        if (dispensing.getPharmacist() == null
                || dispensing.getPharmacist().getPharmacistId() <= 0) {

            System.err.println(
                    "Validation Error: A valid pharmacist must be assigned."
            );
            return false;
        }

        if (dispensing.getPatient() == null
                || dispensing.getPatient().getPatientId() <= 0) {

            System.err.println(
                    "Validation Error: A valid patient must be assigned."
            );
            return false;
        }

        if (dispensing.getDispensingDate() == null) {

            dispensing.setDispensingDate(
                    LocalDateTime.now()
            );
        }

        if (dispensing.getQuantity() <= 0) {

            System.err.println(
                    "Validation Error: Dispensing quantity must be greater than zero."
            );
            return false;
        }

        return medicationDispensingDAO
                .updateMedicationDispensing(dispensing);
    }

    public boolean removeMedicationDispensing(
            int dispensingId) {

        if (dispensingId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Medication Dispensing ID."
            );
            return false;
        }

        return medicationDispensingDAO
                .deleteMedicationDispensing(dispensingId);
    }
}