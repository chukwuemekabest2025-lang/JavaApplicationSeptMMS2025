package hospital.services;

import hospital.dao.MedicalRecordDAO;
import hospital.models.MedicalRecord;

import java.time.LocalDate;
import java.util.List;

public class MedicalRecordService {

    private final MedicalRecordDAO medicalRecordDAO;

    public MedicalRecordService() {
        this.medicalRecordDAO = new MedicalRecordDAO();
    }

    // =========================================================
    // ADD MEDICAL RECORD
    // =========================================================
    public boolean addMedicalRecord(MedicalRecord medicalRecord) {

        if (medicalRecord == null) {

            System.err.println(
                    "Validation Error: Medical record object cannot be null."
            );

            return false;
        }

        // Validate patient
        if (medicalRecord.getPatient() == null
                || medicalRecord.getPatient().getPatientId() <= 0) {

            System.err.println(
                    "Validation Error: A valid patient must be assigned to the medical record."
            );

            return false;
        }

        // Set current date if none was provided
        if (medicalRecord.getCreatedDate() == null) {

            medicalRecord.setCreatedDate(
                    LocalDate.now()
            );
        }

        return medicalRecordDAO.addMedicalRecord(
                medicalRecord
        );
    }


    // =========================================================
    // GET ALL MEDICAL RECORDS
    // =========================================================
    public List<MedicalRecord> getAllMedicalRecords() {

        return medicalRecordDAO.findAllMedicalRecords();
    }


    // =========================================================
    // GET MEDICAL RECORD BY ID
    // =========================================================
    public MedicalRecord getMedicalRecordById(int medicalRecordId) {

        if (medicalRecordId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Medical Record ID."
            );

            return null;
        }

        return medicalRecordDAO.findMedicalRecordById(
                medicalRecordId
        );
    }


    // =========================================================
    // GET MEDICAL RECORD BY PATIENT
    // =========================================================
    public MedicalRecord getMedicalRecordByPatientId(int patientId) {

        if (patientId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Patient ID."
            );

            return null;
        }

        return medicalRecordDAO.findMedicalRecordByPatientId(
                patientId
        );
    }


    // =========================================================
    // UPDATE MEDICAL RECORD
    // =========================================================
    public boolean updateMedicalRecord(
            MedicalRecord medicalRecord) {

        if (medicalRecord == null) {

            System.err.println(
                    "Validation Error: Medical record object cannot be null."
            );

            return false;
        }

        // Validate medical record ID
        if (medicalRecord.getId() <= 0) {

            System.err.println(
                    "Validation Error: Invalid Medical Record ID."
            );

            return false;
        }

        // Validate patient
        if (medicalRecord.getPatient() == null
                || medicalRecord.getPatient().getPatientId() <= 0) {

            System.err.println(
                    "Validation Error: A valid patient must be assigned to the medical record."
            );

            return false;
        }

        // Set current date if none was provided
        if (medicalRecord.getCreatedDate() == null) {

            medicalRecord.setCreatedDate(
                    LocalDate.now()
            );
        }

        return medicalRecordDAO.updateMedicalRecord(
                medicalRecord
        );
    }


    // =========================================================
    // DELETE MEDICAL RECORD
    // =========================================================
    public boolean removeMedicalRecord(int medicalRecordId) {

        if (medicalRecordId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Medical Record ID."
            );

            return false;
        }

        return medicalRecordDAO.deleteMedicalRecord(
                medicalRecordId
        );
    }
}