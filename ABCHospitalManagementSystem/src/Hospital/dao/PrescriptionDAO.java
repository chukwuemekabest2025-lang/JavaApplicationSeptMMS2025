package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Doctor;
import hospital.models.Patient;
import hospital.models.Prescription;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {

    public boolean addPrescription(Prescription prescription) {

        String doctorSql =
                "SELECT DoctorId FROM Doctor WHERE StaffId = ?";

        String sql =
                "INSERT INTO Prescription "
                + "(PatientId, DoctorId, PrescriptionDate) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement doctorStmt =
                     conn.prepareStatement(doctorSql);
             PreparedStatement stmt =
                     conn.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS)) {

            // Convert StaffId to DoctorId
            doctorStmt.setInt(
                    1,
                    prescription.getDoctor().getStaffId()
            );

            int doctorId;

            try (ResultSet rs = doctorStmt.executeQuery()) {

                if (!rs.next()) {
                    System.err.println(
                            "No doctor found with Staff ID: "
                            + prescription.getDoctor().getStaffId()
                    );
                    return false;
                }

                doctorId = rs.getInt("DoctorId");
            }

            stmt.setInt(
                    1,
                    prescription.getPatient().getPatientId()
            );

            stmt.setInt(
                    2,
                    doctorId
            );

            stmt.setDate(
                    3,
                    Date.valueOf(
                            prescription.getPrescriptionDate()
                    )
            );

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {

                try (ResultSet generatedKeys =
                             stmt.getGeneratedKeys()) {

                    if (generatedKeys.next()) {

                        prescription.setId(
                                generatedKeys.getInt(1)
                        );
                    }
                }

                return true;
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error adding prescription:"
            );

            System.err.println(
                    "Message: " + e.getMessage()
            );

            System.err.println(
                    "SQL State: " + e.getSQLState()
            );

            System.err.println(
                    "Error Code: " + e.getErrorCode()
            );

            e.printStackTrace();
        }

        return false;
    }

    public List<Prescription> findAllPrescriptions() {

        List<Prescription> prescriptions =
                new ArrayList<>();

        String sql =
                "SELECT p.PrescriptionId, "
                + "p.PatientId, "
                + "p.DoctorId, "
                + "d.StaffId, "
                + "p.PrescriptionDate "
                + "FROM Prescription p "
                + "JOIN Doctor d ON p.DoctorId = d.DoctorId "
                + "ORDER BY p.PrescriptionId";

        try (Connection conn =
                     DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql);
             ResultSet rs =
                     stmt.executeQuery()) {

            while (rs.next()) {

                Prescription prescription =
                        mapPrescription(rs);

                prescriptions.add(prescription);
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error retrieving prescriptions:"
            );

            System.err.println(
                    "Message: " + e.getMessage()
            );

            System.err.println(
                    "SQL State: " + e.getSQLState()
            );

            System.err.println(
                    "Error Code: " + e.getErrorCode()
            );

            e.printStackTrace();
        }

        return prescriptions;
    }

    public Prescription findPrescriptionById(int id) {

        String sql =
                "SELECT p.PrescriptionId, "
                + "p.PatientId, "
                + "p.DoctorId, "
                + "d.StaffId, "
                + "p.PrescriptionDate "
                + "FROM Prescription p "
                + "JOIN Doctor d ON p.DoctorId = d.DoctorId "
                + "WHERE p.PrescriptionId = ?";

        try (Connection conn =
                     DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs =
                         stmt.executeQuery()) {

                if (rs.next()) {

                    return mapPrescription(rs);
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding prescription:"
            );

            System.err.println(
                    "Message: " + e.getMessage()
            );

            System.err.println(
                    "SQL State: " + e.getSQLState()
            );

            System.err.println(
                    "Error Code: " + e.getErrorCode()
            );

            e.printStackTrace();
        }

        return null;
    }

    public List<Prescription> findPrescriptionsByPatientId(
            int patientId) {

        List<Prescription> prescriptions =
                new ArrayList<>();

        String sql =
                "SELECT p.PrescriptionId, "
                + "p.PatientId, "
                + "p.DoctorId, "
                + "d.StaffId, "
                + "p.PrescriptionDate "
                + "FROM Prescription p "
                + "JOIN Doctor d ON p.DoctorId = d.DoctorId "
                + "WHERE p.PatientId = ? "
                + "ORDER BY p.PrescriptionId";

        try (Connection conn =
                     DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, patientId);

            try (ResultSet rs =
                         stmt.executeQuery()) {

                while (rs.next()) {

                    Prescription prescription =
                            mapPrescription(rs);

                    prescriptions.add(prescription);
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding prescriptions by patient:"
            );

            System.err.println(
                    "Message: " + e.getMessage()
            );

            System.err.println(
                    "SQL State: " + e.getSQLState()
            );

            System.err.println(
                    "Error Code: " + e.getErrorCode()
            );

            e.printStackTrace();
        }

        return prescriptions;
    }

    public List<Prescription> findPrescriptionsByDoctorId(
            int doctorId) {

        List<Prescription> prescriptions =
                new ArrayList<>();

        String sql =
                "SELECT p.PrescriptionId, "
                + "p.PatientId, "
                + "p.DoctorId, "
                + "d.StaffId, "
                + "p.PrescriptionDate "
                + "FROM Prescription p "
                + "JOIN Doctor d ON p.DoctorId = d.DoctorId "
                + "WHERE p.DoctorId = ? "
                + "ORDER BY p.PrescriptionId";

        try (Connection conn =
                     DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, doctorId);

            try (ResultSet rs =
                         stmt.executeQuery()) {

                while (rs.next()) {

                    Prescription prescription =
                            mapPrescription(rs);

                    prescriptions.add(prescription);
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding prescriptions by doctor:"
            );

            System.err.println(
                    "Message: " + e.getMessage()
            );

            System.err.println(
                    "SQL State: " + e.getSQLState()
            );

            System.err.println(
                    "Error Code: " + e.getErrorCode()
            );

            e.printStackTrace();
        }

        return prescriptions;
    }

    public boolean updatePrescription(
            Prescription prescription) {

        String doctorSql =
                "SELECT DoctorId FROM Doctor WHERE StaffId = ?";

        String sql =
                "UPDATE Prescription "
                + "SET PatientId = ?, "
                + "DoctorId = ?, "
                + "PrescriptionDate = ? "
                + "WHERE PrescriptionId = ?";

        try (Connection conn =
                     DatabaseConnection.getConnection();
             PreparedStatement doctorStmt =
                     conn.prepareStatement(doctorSql);
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            // Convert StaffId to DoctorId
            doctorStmt.setInt(
                    1,
                    prescription.getDoctor().getStaffId()
            );

            int doctorId;

            try (ResultSet rs =
                         doctorStmt.executeQuery()) {

                if (!rs.next()) {

                    System.err.println(
                            "No doctor found with Staff ID: "
                            + prescription.getDoctor().getStaffId()
                    );

                    return false;
                }

                doctorId = rs.getInt("DoctorId");
            }

            stmt.setInt(
                    1,
                    prescription.getPatient().getPatientId()
            );

            stmt.setInt(
                    2,
                    doctorId
            );

            stmt.setDate(
                    3,
                    Date.valueOf(
                            prescription.getPrescriptionDate()
                    )
            );

            stmt.setInt(
                    4,
                    prescription.getId()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error updating prescription:"
            );

            System.err.println(
                    "Message: " + e.getMessage()
            );

            System.err.println(
                    "SQL State: " + e.getSQLState()
            );

            System.err.println(
                    "Error Code: " + e.getErrorCode()
            );

            e.printStackTrace();
        }

        return false;
    }

    public boolean deletePrescription(int id) {

        String sql =
                "DELETE FROM Prescription "
                + "WHERE PrescriptionId = ?";

        try (Connection conn =
                     DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error deleting prescription:"
            );

            System.err.println(
                    "Message: " + e.getMessage()
            );

            System.err.println(
                    "SQL State: " + e.getSQLState()
            );

            System.err.println(
                    "Error Code: " + e.getErrorCode()
            );

            e.printStackTrace();
        }

        return false;
    }

    private Prescription mapPrescription(
            ResultSet rs) throws SQLException {

        Prescription prescription =
                new Prescription();

        prescription.setId(
                rs.getInt("PrescriptionId")
        );

        Patient patient =
                new Patient();

        patient.setPatientId(
                rs.getInt("PatientId")
        );

        prescription.setPatient(patient);

        Doctor doctor =
                new Doctor();

        // Use the actual StaffId from Doctor table
        doctor.setStaffId(
                rs.getInt("StaffId")
        );

        prescription.setDoctor(doctor);

        prescription.setPrescriptionDate(
                rs.getDate("PrescriptionDate")
                        .toLocalDate()
        );

        return prescription;
    }
}