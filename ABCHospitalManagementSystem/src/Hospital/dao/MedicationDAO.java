package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Medication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class MedicationDAO {

    // =========================================================
    // ADD MEDICATION
    // =========================================================
    public boolean addMedication(Medication medication) {

        String sql = "INSERT INTO Medication "
                + "(Name, Description, DosageForm, Price, QuantityInStock) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, medication.getName());

            if (medication.getDescription() != null
                    && !medication.getDescription().trim().isEmpty()) {

                stmt.setString(2, medication.getDescription());

            } else {
                stmt.setNull(2, Types.NVARCHAR);
            }

            if (medication.getDosageForm() != null
                    && !medication.getDosageForm().trim().isEmpty()) {

                stmt.setString(3, medication.getDosageForm());

            } else {
                stmt.setNull(3, Types.NVARCHAR);
            }

            // Your Medication model uses double
            stmt.setDouble(4, medication.getPrice());

            stmt.setInt(5, medication.getQuantityInStock());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {

                    if (generatedKeys.next()) {
                        medication.setId(generatedKeys.getInt(1));
                    }
                }

                return true;
            }

        } catch (SQLException e) {

            System.err.println("Error adding medication:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return false;
    }

    // =========================================================
    // GET ALL MEDICATIONS
    // =========================================================
    public List<Medication> findAllMedications() {

        List<Medication> medications = new ArrayList<>();

        String sql = "SELECT MedicationId, Name, Description, "
                + "DosageForm, Price, QuantityInStock "
                + "FROM Medication "
                + "ORDER BY MedicationId";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Medication medication = mapMedication(rs);

                medications.add(medication);
            }

        } catch (SQLException e) {

            System.err.println("Error retrieving medications:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return medications;
    }

    // =========================================================
    // FIND MEDICATION BY ID
    // =========================================================
    public Medication findMedicationById(int id) {

        String sql = "SELECT MedicationId, Name, Description, "
                + "DosageForm, Price, QuantityInStock "
                + "FROM Medication "
                + "WHERE MedicationId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapMedication(rs);
                }
            }

        } catch (SQLException e) {

            System.err.println("Error finding medication:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // UPDATE MEDICATION
    // =========================================================
    public boolean updateMedication(Medication medication) {

        String sql = "UPDATE Medication "
                + "SET Name = ?, "
                + "Description = ?, "
                + "DosageForm = ?, "
                + "Price = ?, "
                + "QuantityInStock = ? "
                + "WHERE MedicationId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medication.getName());

            if (medication.getDescription() != null
                    && !medication.getDescription().trim().isEmpty()) {

                stmt.setString(2, medication.getDescription());

            } else {
                stmt.setNull(2, Types.NVARCHAR);
            }

            if (medication.getDosageForm() != null
                    && !medication.getDosageForm().trim().isEmpty()) {

                stmt.setString(3, medication.getDosageForm());

            } else {
                stmt.setNull(3, Types.NVARCHAR);
            }

            // Your Medication model uses double
            stmt.setDouble(4, medication.getPrice());

            stmt.setInt(5, medication.getQuantityInStock());

            stmt.setInt(6, medication.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println("Error updating medication:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return false;
    }

    // =========================================================
    // UPDATE MEDICATION STOCK
    // =========================================================
    public boolean updateStock(int medicationId, int quantity) {

        String sql = "UPDATE Medication "
                + "SET QuantityInStock = ? "
                + "WHERE MedicationId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantity);
            stmt.setInt(2, medicationId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println("Error updating medication stock:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return false;
    }

    // =========================================================
    // DELETE MEDICATION
    // =========================================================
    public boolean deleteMedication(int id) {

        String sql = "DELETE FROM Medication "
                + "WHERE MedicationId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println("Error deleting medication:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());

            e.printStackTrace();
        }

        return false;
    }

    // =========================================================
    // MAP DATABASE RECORD TO MEDICATION
    // =========================================================
    private Medication mapMedication(ResultSet rs)
            throws SQLException {

        Medication medication = new Medication();

        medication.setId(
                rs.getInt("MedicationId")
        );

        medication.setName(
                rs.getString("Name")
        );

        medication.setDescription(
                rs.getString("Description")
        );

        medication.setDosageForm(
                rs.getString("DosageForm")
        );

        medication.setPrice(
                rs.getDouble("Price")
        );

        medication.setQuantityInStock(
                rs.getInt("QuantityInStock")
        );

        return medication;
    }
}