package hospital.userview;

import hospital.models.Pharmacist;

import java.util.List;

public class PharmacistView {

    public void displayPharmacists(
            List<Pharmacist> pharmacists) {

        if (pharmacists == null
                || pharmacists.isEmpty()) {

            System.out.println(
                    "\nNo pharmacist records found."
            );

            return;
        }

        System.out.println(
                "\n================ PHARMACISTS ================"
        );

        System.out.printf(
                "%-5s %-10s %-30s %-20s%n",
                "ID",
                "Staff ID",
                "Qualification",
                "License Number"
        );

        System.out.println(
                "--------------------------------------------------------------------------"
        );

        for (Pharmacist pharmacist : pharmacists) {

            if (pharmacist == null) {
                continue;
            }

            System.out.printf(
                    "%-5d %-10d %-30s %-20s%n",
                    pharmacist.getPharmacistId(),
                    pharmacist.getStaffId(),
                    safeString(
                            pharmacist.getQualification()
                    ),
                    safeString(
                            pharmacist.getLicenseNumber()
                    )
            );
        }

        System.out.println(
                "=========================================================================="
        );
    }

    public void displayPharmacist(
            Pharmacist pharmacist) {

        if (pharmacist == null) {

            System.out.println(
                    "\nPharmacist record not found."
            );

            return;
        }

        System.out.println(
                "\n============= PHARMACIST DETAILS ============="
        );

        System.out.println(
                "Pharmacist ID      : "
                + pharmacist.getPharmacistId()
        );

        System.out.println(
                "Staff ID           : "
                + pharmacist.getStaffId()
        );

        System.out.println(
                "Qualification      : "
                + safeString(
                        pharmacist.getQualification()
                )
        );

        System.out.println(
                "License Number     : "
                + safeString(
                        pharmacist.getLicenseNumber()
                )
        );

        System.out.println(
                "=============================================="
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