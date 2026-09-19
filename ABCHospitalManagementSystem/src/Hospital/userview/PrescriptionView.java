package hospital.userview;

import hospital.models.Prescription;

import java.util.List;

public class PrescriptionView {

    public void displayPrescriptions(
            List<Prescription> prescriptions) {

        if (prescriptions == null
                || prescriptions.isEmpty()) {

            System.out.println(
                    "\nNo prescription records found."
            );

            return;
        }

        System.out.println(
                "\n================ PRESCRIPTIONS ================"
        );

        System.out.printf(
                "%-5s %-12s %-12s %-20s%n",
                "ID",
                "Patient",
                "Doctor",
                "Prescription Date"
        );

        System.out.println(
                "---------------------------------------------------------------"
        );

        for (Prescription prescription : prescriptions) {

            int patientId = 0;
            int doctorId = 0;

            if (prescription.getPatient() != null) {

                patientId =
                        prescription.getPatient()
                                .getPatientId();
            }

            if (prescription.getDoctor() != null) {

                doctorId =
                        prescription.getDoctor()
                                .getStaffId();
            }

            System.out.printf(
                    "%-5d %-12d %-12d %-20s%n",
                    prescription.getId(),
                    patientId,
                    doctorId,
                    prescription.getPrescriptionDate()
            );
        }

        System.out.println(
                "==============================================================="
        );
    }

    public void displayPrescription(
            Prescription prescription) {

        if (prescription == null) {

            System.out.println(
                    "\nPrescription record not found."
            );

            return;
        }

        System.out.println(
                "\n============= PRESCRIPTION DETAILS ============="
        );

        System.out.println(
                "Prescription ID   : "
                + prescription.getId()
        );

        if (prescription.getPatient() != null) {

            System.out.println(
                    "Patient ID        : "
                    + prescription.getPatient()
                            .getPatientId()
            );

        } else {

            System.out.println(
                    "Patient ID        : N/A"
            );
        }

        if (prescription.getDoctor() != null) {

            System.out.println(
                    "Doctor ID         : "
                    + prescription.getDoctor()
                            .getStaffId()
            );

        } else {

            System.out.println(
                    "Doctor ID         : N/A"
            );
        }

        System.out.println(
                "Prescription Date : "
                + prescription.getPrescriptionDate()
        );

        System.out.println(
                "================================================="
        );
    }
}
