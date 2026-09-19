package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.MedicalRecord;
import hospital.models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {

    // =========================================================
    // ADD MEDICAL RECORD
    // =========================================================
    public boolean addMedicalRecord(MedicalRecord medicalRecord) {

        String sql = "INSERT INTO MedicalRecord "
                + "(PatientId, CreatedDate) "
                + "VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            // Patient ID
            stmt.setInt(
                    1,
                    medicalRecord.getPatient().getPatientId()
            );

            // Created date
            stmt.setDate(
                    2,
                    Date.valueOf(medicalRecord.getCreatedDate())
            );

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {

                    if (generatedKeys.next()) {

                        medicalRecord.setId(
                                generatedKeys.getInt(1)
                        );
                    }
                }

                return true;
            }

        } catch (SQLException e) {

            System.err.println("Error adding medical record:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // GET ALL MEDICAL RECORDS
    // =========================================================
    public List<MedicalRecord> findAllMedicalRecords() {

        List<MedicalRecord> records = new ArrayList<>();

        String sql = "SELECT MedicalRecordId, PatientId, CreatedDate "
                + "FROM MedicalRecord "
                + "ORDER BY MedicalRecordId";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                MedicalRecord record = mapMedicalRecord(rs);

                records.add(record);
            }

        } catch (SQLException e) {

            System.err.println("Error retrieving medical records:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return records;
    }


    // =========================================================
    // FIND MEDICAL RECORD BY ID
    // =========================================================
    public MedicalRecord findMedicalRecordById(int id) {

        String sql = "SELECT MedicalRecordId, PatientId, CreatedDate "
                + "FROM MedicalRecord "
                + "WHERE MedicalRecordId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    return mapMedicalRecord(rs);
                }
            }

        } catch (SQLException e) {

            System.err.println("Error finding medical record:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // FIND MEDICAL RECORD BY PATIENT
    // =========================================================
    public MedicalRecord findMedicalRecordByPatientId(int patientId) {

        String sql = "SELECT MedicalRecordId, PatientId, CreatedDate "
                + "FROM MedicalRecord "
                + "WHERE PatientId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    return mapMedicalRecord(rs);
                }
            }

        } catch (SQLException e) {

            System.err.println("Error finding medical record by patient:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // UPDATE MEDICAL RECORD
    // =========================================================
    public boolean updateMedicalRecord(MedicalRecord medicalRecord) {

        String sql = "UPDATE MedicalRecord "
                + "SET PatientId = ?, CreatedDate = ? "
                + "WHERE MedicalRecordId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    medicalRecord.getPatient().getPatientId()
            );

            stmt.setDate(
                    2,
                    Date.valueOf(medicalRecord.getCreatedDate())
            );

            stmt.setInt(
                    3,
                    medicalRecord.getId()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println("Error updating medical record:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // DELETE MEDICAL RECORD
    // =========================================================
    public boolean deleteMedicalRecord(int id) {

        String sql = "DELETE FROM MedicalRecord "
                + "WHERE MedicalRecordId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println("Error deleting medical record:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // MAP DATABASE RECORD TO MEDICAL RECORD
    // =========================================================
    private MedicalRecord mapMedicalRecord(ResultSet rs)
            throws SQLException {

        MedicalRecord medicalRecord = new MedicalRecord();

        medicalRecord.setId(
                rs.getInt("MedicalRecordId")
        );

        Patient patient = new Patient();

        patient.setPatientId(
                rs.getInt("PatientId")
        );

        medicalRecord.setPatient(patient);

        medicalRecord.setCreatedDate(
                rs.getDate("CreatedDate").toLocalDate()
        );

        return medicalRecord;
    }
}