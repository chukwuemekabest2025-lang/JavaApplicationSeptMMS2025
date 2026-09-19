package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Medication;
import hospital.models.Prescription;
import hospital.models.PrescriptionItem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionItemDAO {

    public boolean addPrescriptionItem(
            PrescriptionItem prescriptionItem) {

        String sql = "INSERT INTO PrescriptionItem "
                + "(PrescriptionId, MedicationId, Dosage, "
                + "Frequency, Duration, DurationUnit, Instructions) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(
                    1,
                    prescriptionItem.getPrescription().getId()
            );

            stmt.setInt(
                    2,
                    prescriptionItem.getMedication().getId()
            );

            if (prescriptionItem.getDosage() != null
                    && !prescriptionItem.getDosage()
                            .trim().isEmpty()) {

                stmt.setString(
                        3,
                        prescriptionItem.getDosage()
                );

            } else {

                stmt.setNull(
                        3,
                        java.sql.Types.NVARCHAR
                );
            }

            if (prescriptionItem.getFrequency() != null
                    && !prescriptionItem.getFrequency()
                            .trim().isEmpty()) {

                stmt.setString(
                        4,
                        prescriptionItem.getFrequency()
                );

            } else {

                stmt.setNull(
                        4,
                        java.sql.Types.NVARCHAR
                );
            }

            if (prescriptionItem.getDuration() > 0) {

                stmt.setInt(
                        5,
                        prescriptionItem.getDuration()
                );

            } else {

                stmt.setNull(
                        5,
                        java.sql.Types.INTEGER
                );
            }

            if (prescriptionItem.getDurationUnit() != null
                    && !prescriptionItem.getDurationUnit()
                            .trim().isEmpty()) {

                stmt.setString(
                        6,
                        prescriptionItem.getDurationUnit()
                );

            } else {

                stmt.setNull(
                        6,
                        java.sql.Types.NVARCHAR
                );
            }

            if (prescriptionItem.getInstructions() != null
                    && !prescriptionItem.getInstructions()
                            .trim().isEmpty()) {

                stmt.setString(
                        7,
                        prescriptionItem.getInstructions()
                );

            } else {

                stmt.setNull(
                        7,
                        java.sql.Types.NVARCHAR
                );
            }

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {

                try (ResultSet generatedKeys =
                        stmt.getGeneratedKeys()) {

                    if (generatedKeys.next()) {

                        prescriptionItem.setId(
                                generatedKeys.getInt(1)
                        );
                    }
                }

                return true;
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error adding prescription item:"
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

    public List<PrescriptionItem>
            findAllPrescriptionItems() {

        List<PrescriptionItem> prescriptionItems =
                new ArrayList<>();

        String sql =
                "SELECT PrescriptionItemId, "
                + "PrescriptionId, MedicationId, "
                + "Dosage, Frequency, Duration, "
                + "DurationUnit, Instructions "
                + "FROM PrescriptionItem "
                + "ORDER BY PrescriptionItemId";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql);
             ResultSet rs =
                stmt.executeQuery()) {

            while (rs.next()) {

                PrescriptionItem prescriptionItem =
                        mapPrescriptionItem(rs);

                prescriptionItems.add(
                        prescriptionItem
                );
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error retrieving prescription items:"
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

        return prescriptionItems;
    }

    public PrescriptionItem findPrescriptionItemById(
            int id) {

        String sql =
                "SELECT PrescriptionItemId, "
                + "PrescriptionId, MedicationId, "
                + "Dosage, Frequency, Duration, "
                + "DurationUnit, Instructions "
                + "FROM PrescriptionItem "
                + "WHERE PrescriptionItemId = ?";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs =
                    stmt.executeQuery()) {

                if (rs.next()) {

                    return mapPrescriptionItem(rs);
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding prescription item:"
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

    public List<PrescriptionItem>
            findPrescriptionItemsByPrescriptionId(
                    int prescriptionId) {

        List<PrescriptionItem> prescriptionItems =
                new ArrayList<>();

        String sql =
                "SELECT PrescriptionItemId, "
                + "PrescriptionId, MedicationId, "
                + "Dosage, Frequency, Duration, "
                + "DurationUnit, Instructions "
                + "FROM PrescriptionItem "
                + "WHERE PrescriptionId = ? "
                + "ORDER BY PrescriptionItemId";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql)) {

            stmt.setInt(1, prescriptionId);

            try (ResultSet rs =
                    stmt.executeQuery()) {

                while (rs.next()) {

                    PrescriptionItem prescriptionItem =
                            mapPrescriptionItem(rs);

                    prescriptionItems.add(
                            prescriptionItem
                    );
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding prescription items by prescription:"
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

        return prescriptionItems;
    }

    public List<PrescriptionItem>
            findPrescriptionItemsByMedicationId(
                    int medicationId) {

        List<PrescriptionItem> prescriptionItems =
                new ArrayList<>();

        String sql =
                "SELECT PrescriptionItemId, "
                + "PrescriptionId, MedicationId, "
                + "Dosage, Frequency, Duration, "
                + "DurationUnit, Instructions "
                + "FROM PrescriptionItem "
                + "WHERE MedicationId = ? "
                + "ORDER BY PrescriptionItemId";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql)) {

            stmt.setInt(1, medicationId);

            try (ResultSet rs =
                    stmt.executeQuery()) {

                while (rs.next()) {

                    PrescriptionItem prescriptionItem =
                            mapPrescriptionItem(rs);

                    prescriptionItems.add(
                            prescriptionItem
                    );
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding prescription items by medication:"
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

        return prescriptionItems;
    }

    public boolean updatePrescriptionItem(
            PrescriptionItem prescriptionItem) {

        String sql =
                "UPDATE PrescriptionItem "
                + "SET PrescriptionId = ?, "
                + "MedicationId = ?, "
                + "Dosage = ?, "
                + "Frequency = ?, "
                + "Duration = ?, "
                + "DurationUnit = ?, "
                + "Instructions = ? "
                + "WHERE PrescriptionItemId = ?";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    prescriptionItem.getPrescription()
                            .getId()
            );

            stmt.setInt(
                    2,
                    prescriptionItem.getMedication()
                            .getId()
            );

            if (prescriptionItem.getDosage() != null
                    && !prescriptionItem.getDosage()
                            .trim().isEmpty()) {

                stmt.setString(
                        3,
                        prescriptionItem.getDosage()
                );

            } else {

                stmt.setNull(
                        3,
                        java.sql.Types.NVARCHAR
                );
            }

            if (prescriptionItem.getFrequency() != null
                    && !prescriptionItem.getFrequency()
                            .trim().isEmpty()) {

                stmt.setString(
                        4,
                        prescriptionItem.getFrequency()
                );

            } else {

                stmt.setNull(
                        4,
                        java.sql.Types.NVARCHAR
                );
            }

            if (prescriptionItem.getDuration() > 0) {

                stmt.setInt(
                        5,
                        prescriptionItem.getDuration()
                );

            } else {

                stmt.setNull(
                        5,
                        java.sql.Types.INTEGER
                );
            }

            if (prescriptionItem.getDurationUnit() != null
                    && !prescriptionItem.getDurationUnit()
                            .trim().isEmpty()) {

                stmt.setString(
                        6,
                        prescriptionItem.getDurationUnit()
                );

            } else {

                stmt.setNull(
                        6,
                        java.sql.Types.NVARCHAR
                );
            }

            if (prescriptionItem.getInstructions() != null
                    && !prescriptionItem.getInstructions()
                            .trim().isEmpty()) {

                stmt.setString(
                        7,
                        prescriptionItem.getInstructions()
                );

            } else {

                stmt.setNull(
                        7,
                        java.sql.Types.NVARCHAR
                );
            }

            stmt.setInt(
                    8,
                    prescriptionItem.getId()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error updating prescription item:"
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

    public boolean deletePrescriptionItem(int id) {

        String sql =
                "DELETE FROM PrescriptionItem "
                + "WHERE PrescriptionItemId = ?";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error deleting prescription item:"
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

    private PrescriptionItem mapPrescriptionItem(
            ResultSet rs) throws SQLException {

        PrescriptionItem prescriptionItem =
                new PrescriptionItem();

        prescriptionItem.setId(
                rs.getInt("PrescriptionItemId")
        );

        Prescription prescription =
                new Prescription();

        prescription.setId(
                rs.getInt("PrescriptionId")
        );

        prescriptionItem.setPrescription(
                prescription
        );

        Medication medication =
                new Medication();

        medication.setId(
                rs.getInt("MedicationId")
        );

        prescriptionItem.setMedication(
                medication
        );

        prescriptionItem.setDosage(
                rs.getString("Dosage")
        );

        prescriptionItem.setFrequency(
                rs.getString("Frequency")
        );

        prescriptionItem.setDuration(
                rs.getInt("Duration")
        );

        prescriptionItem.setDurationUnit(
                rs.getString("DurationUnit")
        );

        prescriptionItem.setInstructions(
                rs.getString("Instructions")
        );

        return prescriptionItem;
    }
}