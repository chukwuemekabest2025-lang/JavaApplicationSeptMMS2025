package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Invoice;
import hospital.models.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {

    // =========================================================
    // ADD INVOICE
    // =========================================================
    public boolean addInvoice(Invoice invoice) {

        String sql =
                "INSERT INTO Invoice "
                + "(PatientId, InvoiceDate, TotalAmount, Status) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(
                    1,
                    invoice.getPatient().getPatientId()
            );

            stmt.setDate(
                    2,
                    Date.valueOf(invoice.getInvoiceDate())
            );

            stmt.setDouble(
                    3,
                    invoice.getTotalAmount()
            );

            stmt.setString(
                    4,
                    invoice.getStatus()
            );

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                return false;
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {

                if (rs.next()) {

                    invoice.setId(
                            rs.getInt(1)
                    );
                }
            }

            return true;

        } catch (SQLException e) {

            System.err.println(
                    "Error adding invoice: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =========================================================
    // GET ALL INVOICES
    // =========================================================
    public List<Invoice> findAllInvoices() {

        List<Invoice> invoices = new ArrayList<>();

        String sql =
                "SELECT InvoiceId, PatientId, InvoiceDate, "
                + "TotalAmount, Status "
                + "FROM Invoice "
                + "ORDER BY InvoiceId";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                invoices.add(
                        mapInvoice(rs)
                );
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error retrieving invoices: "
                    + e.getMessage()
            );
        }

        return invoices;
    }


    // =========================================================
    // GET INVOICE BY ID
    // =========================================================
    public Invoice findInvoiceById(int invoiceId) {

        String sql =
                "SELECT InvoiceId, PatientId, InvoiceDate, "
                + "TotalAmount, Status "
                + "FROM Invoice "
                + "WHERE InvoiceId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    invoiceId
            );

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    return mapInvoice(rs);
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error retrieving invoice: "
                    + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // GET INVOICES BY PATIENT ID
    // =========================================================
    public List<Invoice> findInvoicesByPatientId(int patientId) {

        List<Invoice> invoices = new ArrayList<>();

        String sql =
                "SELECT InvoiceId, PatientId, InvoiceDate, "
                + "TotalAmount, Status "
                + "FROM Invoice "
                + "WHERE PatientId = ? "
                + "ORDER BY InvoiceId";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    patientId
            );

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    invoices.add(
                            mapInvoice(rs)
                    );
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error retrieving patient invoices: "
                    + e.getMessage()
            );
        }

        return invoices;
    }


    // =========================================================
    // UPDATE INVOICE
    // =========================================================
    public boolean updateInvoice(Invoice invoice) {

        String sql =
                "UPDATE Invoice "
                + "SET PatientId = ?, "
                + "InvoiceDate = ?, "
                + "TotalAmount = ?, "
                + "Status = ? "
                + "WHERE InvoiceId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    invoice.getPatient().getPatientId()
            );

            stmt.setDate(
                    2,
                    Date.valueOf(invoice.getInvoiceDate())
            );

            stmt.setDouble(
                    3,
                    invoice.getTotalAmount()
            );

            stmt.setString(
                    4,
                    invoice.getStatus()
            );

            stmt.setInt(
                    5,
                    invoice.getId()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error updating invoice: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =========================================================
    // DELETE INVOICE
    // =========================================================
    public boolean deleteInvoice(int invoiceId) {

        String sql =
                "DELETE FROM Invoice "
                + "WHERE InvoiceId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    invoiceId
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error deleting invoice: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =========================================================
    // MAP INVOICE
    // =========================================================
    private Invoice mapInvoice(ResultSet rs)
            throws SQLException {

        Invoice invoice = new Invoice();

        invoice.setId(
                rs.getInt("InvoiceId")
        );

        Patient patient = new Patient();

        patient.setPatientId(
                rs.getInt("PatientId")
        );

        invoice.setPatient(
                patient
        );

        invoice.setInvoiceDate(
                rs.getDate("InvoiceDate").toLocalDate()
        );

        invoice.setTotalAmount(
                rs.getDouble("TotalAmount")
        );

        invoice.setStatus(
                rs.getString("Status")
        );

        return invoice;
    }
}