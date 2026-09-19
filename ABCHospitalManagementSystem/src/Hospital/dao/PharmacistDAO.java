package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Pharmacist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PharmacistDAO {

    public boolean addPharmacist(Pharmacist pharmacist) {

        String sql =
                "INSERT INTO Pharmacist "
                + "(StaffId, Qualification, LicenseNumber) "
                + "VALUES (?, ?, ?)";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(
                    1,
                    pharmacist.getStaffId()
            );

            stmt.setString(
                    2,
                    pharmacist.getQualification()
            );

            stmt.setString(
                    3,
                    pharmacist.getLicenseNumber()
            );

            int rowsAffected =
                    stmt.executeUpdate();

            if (rowsAffected > 0) {

                try (ResultSet generatedKeys =
                        stmt.getGeneratedKeys()) {

                    if (generatedKeys.next()) {

                        pharmacist.setPharmacistId(
                                generatedKeys.getInt(1)
                        );
                    }
                }

                return true;
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error adding pharmacist:"
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

    public List<Pharmacist> findAllPharmacists() {

        List<Pharmacist> pharmacists =
                new ArrayList<>();

        String sql =
                "SELECT PharmacistId, StaffId, "
                + "Qualification, LicenseNumber "
                + "FROM Pharmacist "
                + "ORDER BY PharmacistId";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql);
             ResultSet rs =
                stmt.executeQuery()) {

            while (rs.next()) {

                Pharmacist pharmacist =
                        mapPharmacist(rs);

                pharmacists.add(pharmacist);
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error retrieving pharmacists:"
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

        return pharmacists;
    }

    public Pharmacist findPharmacistById(int pharmacistId) {

        String sql =
                "SELECT PharmacistId, StaffId, "
                + "Qualification, LicenseNumber "
                + "FROM Pharmacist "
                + "WHERE PharmacistId = ?";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql)) {

            stmt.setInt(1, pharmacistId);

            try (ResultSet rs =
                    stmt.executeQuery()) {

                if (rs.next()) {

                    return mapPharmacist(rs);
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding pharmacist:"
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

    public Pharmacist findPharmacistByStaffId(int staffId) {

        String sql =
                "SELECT PharmacistId, StaffId, "
                + "Qualification, LicenseNumber "
                + "FROM Pharmacist "
                + "WHERE StaffId = ?";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql)) {

            stmt.setInt(1, staffId);

            try (ResultSet rs =
                    stmt.executeQuery()) {

                if (rs.next()) {

                    return mapPharmacist(rs);
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding pharmacist by Staff ID:"
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

    public boolean updatePharmacist(
            Pharmacist pharmacist) {

        String sql =
                "UPDATE Pharmacist "
                + "SET StaffId = ?, "
                + "Qualification = ?, "
                + "LicenseNumber = ? "
                + "WHERE PharmacistId = ?";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    pharmacist.getStaffId()
            );

            stmt.setString(
                    2,
                    pharmacist.getQualification()
            );

            stmt.setString(
                    3,
                    pharmacist.getLicenseNumber()
            );

            stmt.setInt(
                    4,
                    pharmacist.getPharmacistId()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error updating pharmacist:"
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

    public boolean deletePharmacist(int pharmacistId) {

        String sql =
                "DELETE FROM Pharmacist "
                + "WHERE PharmacistId = ?";

        try (Connection conn =
                DatabaseConnection.getConnection();
             PreparedStatement stmt =
                conn.prepareStatement(sql)) {

            stmt.setInt(1, pharmacistId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error deleting pharmacist:"
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

    private Pharmacist mapPharmacist(
            ResultSet rs) throws SQLException {

        Pharmacist pharmacist =
                new Pharmacist();

        pharmacist.setPharmacistId(
                rs.getInt("PharmacistId")
        );

        pharmacist.setStaffId(
                rs.getInt("StaffId")
        );

        pharmacist.setQualification(
                rs.getString("Qualification")
        );

        pharmacist.setLicenseNumber(
                rs.getString("LicenseNumber")
        );

        return pharmacist;
    }
}