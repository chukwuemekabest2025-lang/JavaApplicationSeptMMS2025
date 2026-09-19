package hospital.userview;

import hospital.models.Medication;

import java.util.List;

public class MedicationView {

    // =========================================================
    // DISPLAY ALL MEDICATIONS
    // =========================================================
    public void displayMedications(List<Medication> medications) {

        if (medications == null || medications.isEmpty()) {

            System.out.println(
                    "\nNo medications found."
            );

            return;
        }

        System.out.println("\n==================== MEDICATIONS ====================");

        System.out.printf(
                "%-5s %-20s %-25s %-15s %-12s %-15s%n",
                "ID",
                "Name",
                "Description",
                "Dosage Form",
                "Price",
                "Stock"
        );

        System.out.println(
                "--------------------------------------------------------------------------------------------"
        );

        for (Medication medication : medications) {

            System.out.printf(
                    "%-5d %-20s %-25s %-15s %-12.2f %-15d%n",
                    medication.getId(),
                    safeString(medication.getName()),
                    safeString(medication.getDescription()),
                    safeString(medication.getDosageForm()),
                    medication.getPrice(),
                    medication.getQuantityInStock()
            );
        }

        System.out.println(
                "============================================================================================"
        );
    }


    // =========================================================
    // DISPLAY SINGLE MEDICATION
    // =========================================================
    public void displayMedication(Medication medication) {

        if (medication == null) {

            System.out.println(
                    "\nMedication not found."
            );

            return;
        }

        System.out.println(
                "\n================ MEDICATION DETAILS ================"
        );

        System.out.println(
                "Medication ID    : " + medication.getId()
        );

        System.out.println(
                "Name             : " + safeString(medication.getName())
        );

        System.out.println(
                "Description      : " + safeString(medication.getDescription())
        );

        System.out.println(
                "Dosage Form      : " + safeString(medication.getDosageForm())
        );

        System.out.printf(
                "Price            : %.2f%n",
                medication.getPrice()
        );

        System.out.println(
                "Quantity In Stock: " + medication.getQuantityInStock()
        );

        System.out.println(
                "====================================================="
        );
    }


    // =========================================================
    // SAFE STRING
    // =========================================================
    private String safeString(String value) {

        if (value == null || value.trim().isEmpty()) {
            return "N/A";
        }

        return value;
    }
}