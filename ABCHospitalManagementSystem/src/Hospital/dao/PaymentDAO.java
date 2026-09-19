package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Invoice;
import hospital.models.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    // =========================================================
    // ADD PAYMENT
    // =========================================================
    public boolean addPayment(Payment payment) {

        String sql =
                "INSERT INTO Payment "
                + "(InvoiceId, Amount, PaymentDate, PaymentMethod) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(
                    1,
                    payment.getInvoice().getId()
            );

            stmt.setDouble(
                    2,
                    payment.getAmount()
            );

            stmt.setDate(
                    3,
                    Date.valueOf(payment.getPaymentDate())
            );

            stmt.setString(
                    4,
                    payment.getPaymentMethod()
            );

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                return false;
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {

                if (rs.next()) {

                    payment.setId(
                            rs.getInt(1)
                    );
                }
            }

            return true;

        } catch (SQLException e) {

            System.err.println(
                    "Error adding payment: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =========================================================
    // GET ALL PAYMENTS
    // =========================================================
    public List<Payment> findAllPayments() {

        List<Payment> payments = new ArrayList<>();

        String sql =
                "SELECT PaymentId, InvoiceId, Amount, "
                + "PaymentDate, PaymentMethod "
                + "FROM Payment "
                + "ORDER BY PaymentId";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                payments.add(
                        mapPayment(rs)
                );
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error retrieving payments: "
                    + e.getMessage()
            );
        }

        return payments;
    }


    // =========================================================
    // GET PAYMENT BY ID
    // =========================================================
    public Payment findPaymentById(int paymentId) {

        String sql =
                "SELECT PaymentId, InvoiceId, Amount, "
                + "PaymentDate, PaymentMethod "
                + "FROM Payment "
                + "WHERE PaymentId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    paymentId
            );

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    return mapPayment(rs);
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error retrieving payment: "
                    + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // GET PAYMENTS BY INVOICE ID
    // =========================================================
    public List<Payment> findPaymentsByInvoiceId(
            int invoiceId) {

        List<Payment> payments = new ArrayList<>();

        String sql =
                "SELECT PaymentId, InvoiceId, Amount, "
                + "PaymentDate, PaymentMethod "
                + "FROM Payment "
                + "WHERE InvoiceId = ? "
                + "ORDER BY PaymentId";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    invoiceId
            );

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    payments.add(
                            mapPayment(rs)
                    );
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error retrieving invoice payments: "
                    + e.getMessage()
            );
        }

        return payments;
    }


    // =========================================================
    // UPDATE PAYMENT
    // =========================================================
    public boolean updatePayment(Payment payment) {

        String sql =
                "UPDATE Payment "
                + "SET InvoiceId = ?, "
                + "Amount = ?, "
                + "PaymentDate = ?, "
                + "PaymentMethod = ? "
                + "WHERE PaymentId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    payment.getInvoice().getId()
            );

            stmt.setDouble(
                    2,
                    payment.getAmount()
            );

            stmt.setDate(
                    3,
                    Date.valueOf(payment.getPaymentDate())
            );

            stmt.setString(
                    4,
                    payment.getPaymentMethod()
            );

            stmt.setInt(
                    5,
                    payment.getId()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error updating payment: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =========================================================
    // DELETE PAYMENT
    // =========================================================
    public boolean deletePayment(int paymentId) {

        String sql =
                "DELETE FROM Payment "
                + "WHERE PaymentId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    paymentId
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error deleting payment: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =========================================================
    // MAP PAYMENT
    // =========================================================
    private Payment mapPayment(ResultSet rs)
            throws SQLException {

        Payment payment = new Payment();

        payment.setId(
                rs.getInt("PaymentId")
        );

        Invoice invoice = new Invoice();

        invoice.setId(
                rs.getInt("InvoiceId")
        );

        payment.setInvoice(
                invoice
        );

        payment.setAmount(
                rs.getDouble("Amount")
        );

        payment.setPaymentDate(
                rs.getDate("PaymentDate").toLocalDate()
        );

        payment.setPaymentMethod(
                rs.getString("PaymentMethod")
        );

        return payment;
    }
}