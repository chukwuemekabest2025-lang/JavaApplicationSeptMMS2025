package hospital.userview;

import hospital.models.MedicationDispensing;

import java.util.List;

public class MedicationDispensingView {

    public void displayMedicationDispensings(
            List<MedicationDispensing> dispensings) {

        if (dispensings == null
                || dispensings.isEmpty()) {

            System.out.println(
                    "\nNo medication dispensing records found."
            );

            return;
        }

        System.out.println(
                "\n================ MEDICATION DISPENSINGS ================"
        );

        System.out.printf(
                "%-5s %-12s %-15s %-12s %-12s %-20s %-10s %-15s%n",
                "ID",
                "Prescription",
                "Prescription Item",
                "Pharmacist",
                "Patient",
                "Dispensing Date",
                "Quantity",
                "Status"
        );

        System.out.println(
                "----------------------------------------------------------------------------------------------------------------"
        );

        for (MedicationDispensing dispensing : dispensings) {

            int prescriptionId = 0;
            int prescriptionItemId = 0;
            int pharmacistId = 0;
            int patientId = 0;

            if (dispensing.getPrescription() != null) {

                prescriptionId =
                        dispensing.getPrescription().getId();
            }

            if (dispensing.getPrescriptionItem() != null) {

                prescriptionItemId =
                        dispensing.getPrescriptionItem().getId();
            }

            if (dispensing.getPharmacist() != null) {

                pharmacistId =
                        dispensing.getPharmacist().getPharmacistId();
            }

            if (dispensing.getPatient() != null) {

                patientId =
                        dispensing.getPatient().getPatientId();
            }

            System.out.printf(
                    "%-5d %-12d %-15d %-12d %-12d %-20s %-10d %-15s%n",
                    dispensing.getId(),
                    prescriptionId,
                    prescriptionItemId,
                    pharmacistId,
                    patientId,
                    dispensing.getDispensingDate(),
                    dispensing.getQuantity(),
                    safeString(dispensing.getStatus())
            );
        }

        System.out.println(
                "================================================================================================================"
        );
    }

    public void displayMedicationDispensing(
            MedicationDispensing dispensing) {

        if (dispensing == null) {

            System.out.println(
                    "\nMedication dispensing record not found."
            );

            return;
        }

        System.out.println(
                "\n============= MEDICATION DISPENSING DETAILS ============="
        );

        System.out.println(
                "Dispensing ID      : "
                + dispensing.getId()
        );

        if (dispensing.getPrescription() != null) {

            System.out.println(
                    "Prescription ID    : "
                    + dispensing.getPrescription().getId()
            );

        } else {

            System.out.println(
                    "Prescription ID    : N/A"
            );
        }

        if (dispensing.getPrescriptionItem() != null) {

            System.out.println(
                    "Prescription Item  : "
                    + dispensing.getPrescriptionItem().getId()
            );

        } else {

            System.out.println(
                    "Prescription Item  : N/A"
            );
        }

        if (dispensing.getPharmacist() != null) {

            System.out.println(
                    "Pharmacist ID      : "
                    + dispensing.getPharmacist().getPharmacistId()
            );

        } else {

            System.out.println(
                    "Pharmacist ID      : N/A"
            );
        }

        if (dispensing.getPatient() != null) {

            System.out.println(
                    "Patient ID         : "
                    + dispensing.getPatient().getPatientId()
            );

        } else {

            System.out.println(
                    "Patient ID         : N/A"
            );
        }

        System.out.println(
                "Dispensing Date    : "
                + dispensing.getDispensingDate()
        );

        System.out.println(
                "Quantity           : "
                + dispensing.getQuantity()
        );

        System.out.println(
                "Status             : "
                + safeString(dispensing.getStatus())
        );

        System.out.println(
                "Notes              : "
                + safeString(dispensing.getNotes())
        );

        System.out.println(
                "========================================================="
        );
    }

    private String safeString(String value) {

        if (value == null
                || value.trim().isEmpty()) {

            return "N/A";
        }

        return value;
    }
}