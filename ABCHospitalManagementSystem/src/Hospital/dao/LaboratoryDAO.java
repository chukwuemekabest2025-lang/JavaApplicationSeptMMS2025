package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.LaboratoryTechnician;
import hospital.models.LaboratoryTest;
import hospital.models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LaboratoryDAO {

    // =========================================================
    // ADD LABORATORY TEST
    // =========================================================
    public boolean addLaboratoryTest(LaboratoryTest test) {

        String sql = "INSERT INTO LaboratoryTest "
                + "(PatientId, LaboratoryTechnicianId, TestName, "
                + "TestDate, Result, ReferenceRange, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            // Patient ID
            if (test.getPatient() != null) {
                stmt.setInt(1, test.getPatient().getPatientId());
            } else {
                stmt.setNull(1, Types.INTEGER);
            }

            // Laboratory Technician ID
            if (test.getTechnician() != null) {
                stmt.setInt(2, test.getTechnician().getStaffId());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            // Test name
            stmt.setString(3, test.getTestName());

            // Test date
            if (test.getTestDate() != null) {
                stmt.setTimestamp(
                        4,
                        Timestamp.valueOf(test.getTestDate())
                );
            } else {
                stmt.setTimestamp(
                        4,
                        Timestamp.valueOf(LocalDateTime.now())
                );
            }

            // Result
            if (test.getResult() != null
                    && !test.getResult().trim().isEmpty()) {

                stmt.setString(5, test.getResult());

            } else {
                stmt.setNull(5, Types.NVARCHAR);
            }

            // Reference range
            if (test.getReferenceRange() != null
                    && !test.getReferenceRange().trim().isEmpty()) {

                stmt.setString(6, test.getReferenceRange());

            } else {
                stmt.setNull(6, Types.NVARCHAR);
            }

            // Status
            String status = test.getStatus();

            if (status == null || status.trim().isEmpty()) {
                status = "Pending";
            }

            stmt.setString(7, status);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {

                    if (generatedKeys.next()) {
                        test.setId(generatedKeys.getInt(1));
                    }
                }

                return true;
            }

        } catch (SQLException e) {

            System.err.println("Error adding laboratory test:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // GET ALL LABORATORY TESTS
    // =========================================================
    public List<LaboratoryTest> findAllLaboratoryTests() {

        List<LaboratoryTest> tests = new ArrayList<>();

        String sql = "SELECT LaboratoryTestId, PatientId, "
                + "LaboratoryTechnicianId, TestName, TestDate, "
                + "Result, ReferenceRange, Status "
                + "FROM LaboratoryTest "
                + "ORDER BY LaboratoryTestId";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                LaboratoryTest test = mapLaboratoryTest(rs);

                tests.add(test);
            }

        } catch (SQLException e) {

            System.err.println("Error retrieving laboratory tests:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return tests;
    }


    // =========================================================
    // FIND LABORATORY TEST BY ID
    // =========================================================
    public LaboratoryTest findLaboratoryTestById(int id) {

        String sql = "SELECT LaboratoryTestId, PatientId, "
                + "LaboratoryTechnicianId, TestName, TestDate, "
                + "Result, ReferenceRange, Status "
                + "FROM LaboratoryTest "
                + "WHERE LaboratoryTestId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    return mapLaboratoryTest(rs);
                }
            }

        } catch (SQLException e) {

            System.err.println("Error finding laboratory test:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // UPDATE TEST RESULT
    // =========================================================
    public boolean updateTestResult(
            int testId,
            int technicianId,
            String result,
            String referenceRange,
            String status) {

        String sql = "UPDATE LaboratoryTest "
                + "SET LaboratoryTechnicianId = ?, "
                + "Result = ?, "
                + "ReferenceRange = ?, "
                + "Status = ? "
                + "WHERE LaboratoryTestId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Technician ID
            stmt.setInt(1, technicianId);

            // Result
            stmt.setString(2, result);

            // Reference range
            if (referenceRange != null
                    && !referenceRange.trim().isEmpty()) {

                stmt.setString(3, referenceRange);

            } else {
                stmt.setNull(3, Types.NVARCHAR);
            }

            // Status
            if (status == null || status.trim().isEmpty()) {

                stmt.setString(4, "Completed");

            } else {
                stmt.setString(4, status);
            }

            // Test ID
            stmt.setInt(5, testId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println("Error updating laboratory test result:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // DELETE LABORATORY TEST
    // =========================================================
    public boolean deleteLaboratoryTest(int id) {

        String sql = "DELETE FROM LaboratoryTest "
                + "WHERE LaboratoryTestId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println("Error deleting laboratory test:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // MAP DATABASE RECORD TO LABORATORY TEST
    // =========================================================
    private LaboratoryTest mapLaboratoryTest(ResultSet rs)
            throws SQLException {

        LaboratoryTest test = new LaboratoryTest();

        // Test ID
        test.setId(
                rs.getInt("LaboratoryTestId")
        );

        // Patient
        int patientId = rs.getInt("PatientId");

        if (!rs.wasNull()) {

            Patient patient = new Patient();

            patient.setPatientId(patientId);

            test.setPatient(patient);
        }

        // Laboratory Technician
        int technicianId =
                rs.getInt("LaboratoryTechnicianId");

        if (!rs.wasNull()) {

            LaboratoryTechnician technician =
                    new LaboratoryTechnician();

            technician.setStaffId(technicianId);

            test.setTechnician(technician);
        }

        // Test name
        test.setTestName(
                rs.getString("TestName")
        );

        // Test date
        Timestamp timestamp =
                rs.getTimestamp("TestDate");

        if (timestamp != null) {

            test.setTestDate(
                    timestamp.toLocalDateTime()
            );
        }

        // Result
        test.setResult(
                rs.getString("Result")
        );

        // Reference range
        test.setReferenceRange(
                rs.getString("ReferenceRange")
        );

        // Status
        test.setStatus(
                rs.getString("Status")
        );

        return test;
    }
}
