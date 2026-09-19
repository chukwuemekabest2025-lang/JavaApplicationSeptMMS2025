package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.MedicationDispensing;
import hospital.models.Prescription;
import hospital.models.PrescriptionItem;
import hospital.models.Pharmacist;
import hospital.models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class MedicationDispensingDAO {

    public boolean addMedicationDispensing(
            MedicationDispensing dispensing) {

        String sql = "INSERT INTO MedicationDispensing "
                + "(PrescriptionId, PrescriptionItemId, PharmacistId, "
                + "PatientId, DispensingDate, Quantity, Status, Notes) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(
                    1,
                    dispensing.getPrescription().getId()
            );

            stmt.setInt(
                    2,
                    dispensing.getPrescriptionItem().getId()
            );

            stmt.setInt(
                    3,
                    dispensing.getPharmacist().getPharmacistId()
            );

            stmt.setInt(
                    4,
                    dispensing.getPatient().getPatientId()
            );

            stmt.setTimestamp(
                    5,
                    Timestamp.valueOf(
                            dispensing.getDispensingDate()
                    )
            );

            stmt.setInt(
                    6,
                    dispensing.getQuantity()
            );

            if (dispensing.getStatus() != null
                    && !dispensing.getStatus().trim().isEmpty()) {

                stmt.setString(
                        7,
                        dispensing.getStatus()
                );

            } else {

                stmt.setNull(
                        7,
                        Types.NVARCHAR
                );
            }

            if (dispensing.getNotes() != null
                    && !dispensing.getNotes().trim().isEmpty()) {

                stmt.setString(
                        8,
                        dispensing.getNotes()
                );

            } else {

                stmt.setNull(
                        8,
                        Types.NVARCHAR
                );
            }

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {

                try (ResultSet generatedKeys =
                        stmt.getGeneratedKeys()) {

                    if (generatedKeys.next()) {

                        dispensing.setId(
                                generatedKeys.getInt(1)
                        );
                    }
                }

                return true;
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error adding medication dispensing:"
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

    public List<MedicationDispensing>
            findAllMedicationDispensings() {

        List<MedicationDispensing> dispensings =
                new ArrayList<>();

        String sql =
                "SELECT MedicationDispensingId, "
                + "PrescriptionId, PrescriptionItemId, "
                + "PharmacistId, PatientId, DispensingDate, "
                + "Quantity, Status, Notes "
                + "FROM MedicationDispensing "
                + "ORDER BY MedicationDispensingId";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql);
             ResultSet rs =
                stmt.executeQuery()) {

            while (rs.next()) {

                MedicationDispensing dispensing =
                        mapMedicationDispensing(rs);

                dispensings.add(dispensing);
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error retrieving medication dispensings:"
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

        return dispensings;
    }

    public MedicationDispensing findMedicationDispensingById(
            int id) {

        String sql =
                "SELECT MedicationDispensingId, "
                + "PrescriptionId, PrescriptionItemId, "
                + "PharmacistId, PatientId, DispensingDate, "
                + "Quantity, Status, Notes "
                + "FROM MedicationDispensing "
                + "WHERE MedicationDispensingId = ?";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs =
                    stmt.executeQuery()) {

                if (rs.next()) {

                    return mapMedicationDispensing(rs);
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding medication dispensing:"
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

    public List<MedicationDispensing>
            findMedicationDispensingsByPatientId(
                    int patientId) {

        List<MedicationDispensing> dispensings =
                new ArrayList<>();

        String sql =
                "SELECT MedicationDispensingId, "
                + "PrescriptionId, PrescriptionItemId, "
                + "PharmacistId, PatientId, DispensingDate, "
                + "Quantity, Status, Notes "
                + "FROM MedicationDispensing "
                + "WHERE PatientId = ? "
                + "ORDER BY MedicationDispensingId";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql)) {

            stmt.setInt(1, patientId);

            try (ResultSet rs =
                    stmt.executeQuery()) {

                while (rs.next()) {

                    MedicationDispensing dispensing =
                            mapMedicationDispensing(rs);

                    dispensings.add(dispensing);
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding dispensings by patient:"
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

        return dispensings;
    }

    public boolean updateMedicationDispensing(
            MedicationDispensing dispensing) {

        String sql =
                "UPDATE MedicationDispensing "
                + "SET PrescriptionId = ?, "
                + "PrescriptionItemId = ?, "
                + "PharmacistId = ?, "
                + "PatientId = ?, "
                + "DispensingDate = ?, "
                + "Quantity = ?, "
                + "Status = ?, "
                + "Notes = ? "
                + "WHERE MedicationDispensingId = ?";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    dispensing.getPrescription().getId()
            );

            stmt.setInt(
                    2,
                    dispensing.getPrescriptionItem().getId()
            );

            stmt.setInt(
                    3,
                    dispensing.getPharmacist().getPharmacistId()
            );

            stmt.setInt(
                    4,
                    dispensing.getPatient().getPatientId()
            );

            stmt.setTimestamp(
                    5,
                    Timestamp.valueOf(
                            dispensing.getDispensingDate()
                    )
            );

            stmt.setInt(
                    6,
                    dispensing.getQuantity()
            );

            if (dispensing.getStatus() != null
                    && !dispensing.getStatus().trim().isEmpty()) {

                stmt.setString(
                        7,
                        dispensing.getStatus()
                );

            } else {

                stmt.setNull(
                        7,
                        Types.NVARCHAR
                );
            }

            if (dispensing.getNotes() != null
                    && !dispensing.getNotes().trim().isEmpty()) {

                stmt.setString(
                        8,
                        dispensing.getNotes()
                );

            } else {

                stmt.setNull(
                        8,
                        Types.NVARCHAR
                );
            }

            stmt.setInt(
                    9,
                    dispensing.getId()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error updating medication dispensing:"
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

    public boolean deleteMedicationDispensing(int id) {

        String sql =
                "DELETE FROM MedicationDispensing "
                + "WHERE MedicationDispensingId = ?";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error deleting medication dispensing:"
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

    private MedicationDispensing mapMedicationDispensing(
            ResultSet rs) throws SQLException {

        MedicationDispensing dispensing =
                new MedicationDispensing();

        dispensing.setId(
                rs.getInt("MedicationDispensingId")
        );

        Prescription prescription =
                new Prescription();

        prescription.setId(
                rs.getInt("PrescriptionId")
        );

        dispensing.setPrescription(
                prescription
        );

        PrescriptionItem prescriptionItem =
                new PrescriptionItem();

        prescriptionItem.setId(
                rs.getInt("PrescriptionItemId")
        );

        dispensing.setPrescriptionItem(
                prescriptionItem
        );

        Pharmacist pharmacist =
                new Pharmacist();

        pharmacist.setPharmacistId(
                rs.getInt("PharmacistId")
        );

        dispensing.setPharmacist(
                pharmacist
        );

        Patient patient = new Patient();

        patient.setPatientId(
                rs.getInt("PatientId")
        );

        dispensing.setPatient(
                patient
        );

        dispensing.setDispensingDate(
                rs.getTimestamp(
                        "DispensingDate"
                ).toLocalDateTime()
        );

        dispensing.setQuantity(
                rs.getInt("Quantity")
        );

        dispensing.setStatus(
                rs.getString("Status")
        );

        dispensing.setNotes(
                rs.getString("Notes")
        );

        return dispensing;
    }
}
