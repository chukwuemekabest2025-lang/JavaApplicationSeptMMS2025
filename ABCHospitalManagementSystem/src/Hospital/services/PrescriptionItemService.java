
package hospital.services;

import hospital.dao.PrescriptionItemDAO;
import hospital.models.PrescriptionItem;

import java.util.List;

public class PrescriptionItemService {

    private final PrescriptionItemDAO prescriptionItemDAO;

    public PrescriptionItemService() {
        this.prescriptionItemDAO =
                new PrescriptionItemDAO();
    }

    public boolean addPrescriptionItem(
            PrescriptionItem prescriptionItem) {

        if (prescriptionItem == null) {

            System.err.println(
                    "Validation Error: Prescription item object cannot be null."
            );
            return false;
        }

        if (prescriptionItem.getPrescription() == null
                || prescriptionItem.getPrescription().getId() <= 0) {

            System.err.println(
                    "Validation Error: A valid prescription must be assigned."
            );
            return false;
        }

        if (prescriptionItem.getMedication() == null
                || prescriptionItem.getMedication().getId() <= 0) {

            System.err.println(
                    "Validation Error: A valid medication must be assigned."
            );
            return false;
        }

        if (prescriptionItem.getDuration() < 0) {

            System.err.println(
                    "Validation Error: Duration cannot be negative."
            );
            return false;
        }

        return prescriptionItemDAO
                .addPrescriptionItem(prescriptionItem);
    }

    public List<PrescriptionItem>
            getAllPrescriptionItems() {

        return prescriptionItemDAO
                .findAllPrescriptionItems();
    }

    public PrescriptionItem getPrescriptionItemById(
            int prescriptionItemId) {

        if (prescriptionItemId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Prescription Item ID."
            );
            return null;
        }

        return prescriptionItemDAO
                .findPrescriptionItemById(
                        prescriptionItemId
                );
    }

    public List<PrescriptionItem>
            getPrescriptionItemsByPrescriptionId(
                    int prescriptionId) {

        if (prescriptionId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Prescription ID."
            );
            return null;
        }

        return prescriptionItemDAO
                .findPrescriptionItemsByPrescriptionId(
                        prescriptionId
                );
    }

    public List<PrescriptionItem>
            getPrescriptionItemsByMedicationId(
                    int medicationId) {

        if (medicationId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Medication ID."
            );
            return null;
        }

        return prescriptionItemDAO
                .findPrescriptionItemsByMedicationId(
                        medicationId
                );
    }

    public boolean updatePrescriptionItem(
            PrescriptionItem prescriptionItem) {

        if (prescriptionItem == null) {

            System.err.println(
                    "Validation Error: Prescription item object cannot be null."
            );
            return false;
        }

        if (prescriptionItem.getId() <= 0) {

            System.err.println(
                    "Validation Error: Invalid Prescription Item ID."
            );
            return false;
        }

        if (prescriptionItem.getPrescription() == null
                || prescriptionItem.getPrescription().getId() <= 0) {

            System.err.println(
                    "Validation Error: A valid prescription must be assigned."
            );
            return false;
        }

        if (prescriptionItem.getMedication() == null
                || prescriptionItem.getMedication().getId() <= 0) {

            System.err.println(
                    "Validation Error: A valid medication must be assigned."
            );
            return false;
        }

        if (prescriptionItem.getDuration() < 0) {

            System.err.println(
                    "Validation Error: Duration cannot be negative."
            );
            return false;
        }

        return prescriptionItemDAO
                .updatePrescriptionItem(
                        prescriptionItem
                );
    }

    public boolean removePrescriptionItem(
            int prescriptionItemId) {

        if (prescriptionItemId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Prescription Item ID."
            );
            return false;
        }

        return prescriptionItemDAO
                .deletePrescriptionItem(
                        prescriptionItemId
                );
    }
}
