package hospital.userview;

import hospital.models.MedicalRecord;

import java.util.List;

public class MedicalRecordView {

    // =========================================================
    // DISPLAY ALL MEDICAL RECORDS
    // =========================================================
    public void displayMedicalRecords(
            List<MedicalRecord> medicalRecords) {

        if (medicalRecords == null
                || medicalRecords.isEmpty()) {

            System.out.println(
                    "\nNo medical records found."
            );

            return;
        }

        System.out.println(
                "\n==================== MEDICAL RECORDS ===================="
        );

        System.out.printf(
                "%-8s %-12s %-15s%n",
                "ID",
                "Patient ID",
                "Created Date"
        );

        System.out.println(
                "----------------------------------------------------------"
        );

        for (MedicalRecord medicalRecord : medicalRecords) {

            int patientId = 0;

            if (medicalRecord.getPatient() != null) {

                patientId =
                        medicalRecord.getPatient().getPatientId();
            }

            System.out.printf(
                    "%-8d %-12d %-15s%n",
                    medicalRecord.getId(),
                    patientId,
                    medicalRecord.getCreatedDate()
            );
        }

        System.out.println(
                "=========================================================="
        );
    }


    // =========================================================
    // DISPLAY SINGLE MEDICAL RECORD
    // =========================================================
    public void displayMedicalRecord(
            MedicalRecord medicalRecord) {

        if (medicalRecord == null) {

            System.out.println(
                    "\nMedical record not found."
            );

            return;
        }

        System.out.println(
                "\n=============== MEDICAL RECORD DETAILS ==============="
        );

        System.out.println(
                "Medical Record ID : "
                + medicalRecord.getId()
        );

        if (medicalRecord.getPatient() != null) {

            System.out.println(
                    "Patient ID        : "
                    + medicalRecord.getPatient().getPatientId()
            );

        } else {

            System.out.println(
                    "Patient ID        : N/A"
            );
        }

        System.out.println(
                "Created Date      : "
                + medicalRecord.getCreatedDate()
        );

        System.out.println(
                "======================================================="
        );
    }
}