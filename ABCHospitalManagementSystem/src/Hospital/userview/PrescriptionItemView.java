package hospital.userview;

import hospital.models.PrescriptionItem;

import java.util.List;

public class PrescriptionItemView {

    public void displayPrescriptionItems(
            List<PrescriptionItem> prescriptionItems) {

        if (prescriptionItems == null
                || prescriptionItems.isEmpty()) {

            System.out.println(
                    "\nNo prescription item records found."
            );

            return;
        }

        System.out.println(
                "\n================ PRESCRIPTION ITEMS ================"
        );

        System.out.printf(
                "%-5s %-12s %-12s %-15s %-15s %-10s %-15s%n",
                "ID",
                "Prescription",
                "Medication",
                "Dosage",
                "Frequency",
                "Duration",
                "Duration Unit"
        );

        System.out.println(
                "--------------------------------------------------------------------------------"
        );

        for (PrescriptionItem item : prescriptionItems) {

            int prescriptionId = 0;
            int medicationId = 0;

            if (item.getPrescription() != null) {

                prescriptionId =
                        item.getPrescription().getId();
            }

            if (item.getMedication() != null) {

                medicationId =
                        item.getMedication().getId();
            }

            System.out.printf(
                    "%-5d %-12d %-12d %-15s %-15s %-10d %-15s%n",
                    item.getId(),
                    prescriptionId,
                    medicationId,
                    safeString(item.getDosage()),
                    safeString(item.getFrequency()),
                    item.getDuration(),
                    safeString(item.getDurationUnit())
            );
        }

        System.out.println(
                "================================================================================"
        );
    }

    public void displayPrescriptionItem(
            PrescriptionItem item) {

        if (item == null) {

            System.out.println(
                    "\nPrescription item record not found."
            );

            return;
        }

        System.out.println(
                "\n============= PRESCRIPTION ITEM DETAILS ============="
        );

        System.out.println(
                "Prescription Item ID : "
                + item.getId()
        );

        if (item.getPrescription() != null) {

            System.out.println(
                    "Prescription ID      : "
                    + item.getPrescription().getId()
            );

        } else {

            System.out.println(
                    "Prescription ID      : N/A"
            );
        }

        if (item.getMedication() != null) {

            System.out.println(
                    "Medication ID        : "
                    + item.getMedication().getId()
            );

        } else {

            System.out.println(
                    "Medication ID        : N/A"
            );
        }

        System.out.println(
                "Dosage               : "
                + safeString(item.getDosage())
        );

        System.out.println(
                "Frequency            : "
                + safeString(item.getFrequency())
        );

        System.out.println(
                "Duration             : "
                + item.getDuration()
        );

        System.out.println(
                "Duration Unit        : "
                + safeString(item.getDurationUnit())
        );

        System.out.println(
                "Instructions         : "
                + safeString(item.getInstructions())
        );

        System.out.println(
                "======================================================"
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