package hospital.services;

import hospital.dao.MedicationDAO;
import hospital.models.Medication;

import java.util.List;

public class MedicationService {

    private final MedicationDAO medicationDAO;

    public MedicationService() {
        this.medicationDAO = new MedicationDAO();
    }

    // =========================================================
    // ADD MEDICATION
    // =========================================================
    public boolean addMedication(Medication medication) {

        if (medication == null) {

            System.err.println(
                    "Validation Error: Medication object cannot be null."
            );

            return false;
        }

        // Validate medication name
        if (medication.getName() == null
                || medication.getName().trim().isEmpty()) {

            System.err.println(
                    "Validation Error: Medication name is required."
            );

            return false;
        }

        // Validate dosage form
        if (medication.getDosageForm() == null
                || medication.getDosageForm().trim().isEmpty()) {

            System.err.println(
                    "Validation Error: Dosage form is required."
            );

            return false;
        }

        // Validate price
        if (medication.getPrice() < 0) {

            System.err.println(
                    "Validation Error: Medication price cannot be negative."
            );

            return false;
        }

        // Validate quantity
        if (medication.getQuantityInStock() < 0) {

            System.err.println(
                    "Validation Error: Quantity in stock cannot be negative."
            );

            return false;
        }

        return medicationDAO.addMedication(medication);
    }


    // =========================================================
    // GET ALL MEDICATIONS
    // =========================================================
    public List<Medication> getAllMedications() {

        return medicationDAO.findAllMedications();
    }


    // =========================================================
    // GET MEDICATION BY ID
    // =========================================================
    public Medication getMedicationById(int medicationId) {

        if (medicationId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Medication ID."
            );

            return null;
        }

        return medicationDAO.findMedicationById(medicationId);
    }


    // =========================================================
    // UPDATE MEDICATION
    // =========================================================
    public boolean updateMedication(Medication medication) {

        if (medication == null) {

            System.err.println(
                    "Validation Error: Medication object cannot be null."
            );

            return false;
        }

        // Validate medication ID
        if (medication.getId() <= 0) {

            System.err.println(
                    "Validation Error: Invalid Medication ID."
            );

            return false;
        }

        // Validate medication name
        if (medication.getName() == null
                || medication.getName().trim().isEmpty()) {

            System.err.println(
                    "Validation Error: Medication name is required."
            );

            return false;
        }

        // Validate dosage form
        if (medication.getDosageForm() == null
                || medication.getDosageForm().trim().isEmpty()) {

            System.err.println(
                    "Validation Error: Dosage form is required."
            );

            return false;
        }

        // Validate price
        if (medication.getPrice() < 0) {

            System.err.println(
                    "Validation Error: Medication price cannot be negative."
            );

            return false;
        }

        // Validate quantity
        if (medication.getQuantityInStock() < 0) {

            System.err.println(
                    "Validation Error: Quantity in stock cannot be negative."
            );

            return false;
        }

        return medicationDAO.updateMedication(medication);
    }


    // =========================================================
    // UPDATE MEDICATION STOCK
    // =========================================================
    public boolean updateStock(int medicationId, int quantity) {

        if (medicationId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Medication ID."
            );

            return false;
        }

        if (quantity < 0) {

            System.err.println(
                    "Validation Error: Quantity cannot be negative."
            );

            return false;
        }

        return medicationDAO.updateStock(
                medicationId,
                quantity
        );
    }


    // =========================================================
    // DELETE MEDICATION
    // =========================================================
    public boolean removeMedication(int medicationId) {

        if (medicationId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Medication ID."
            );

            return false;
        }

        return medicationDAO.deleteMedication(medicationId);
    }
}