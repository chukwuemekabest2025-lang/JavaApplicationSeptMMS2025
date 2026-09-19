package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Invoice;
import hospital.models.InvoiceItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceItemDAO {

    // =========================================================
    // ADD INVOICE ITEM
    // =========================================================
    public boolean addInvoiceItem(InvoiceItem invoiceItem) {

        String sql =
                "INSERT INTO InvoiceItem "
                + "(InvoiceId, Description, Quantity, UnitPrice) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(
                    1,
                    invoiceItem.getInvoice().getId()
            );

            stmt.setString(
                    2,
                    invoiceItem.getDescription()
            );

            stmt.setInt(
                    3,
                    invoiceItem.getQuantity()
            );

            stmt.setDouble(
                    4,
                    invoiceItem.getUnitPrice()
            );

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                return false;
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {

                if (rs.next()) {

                    invoiceItem.setId(
                            rs.getInt(1)
                    );
                }
            }

            return true;

        } catch (SQLException e) {

            System.err.println(
                    "Error adding invoice item: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =========================================================
    // GET ALL INVOICE ITEMS
    // =========================================================
    public List<InvoiceItem> findAllInvoiceItems() {

        List<InvoiceItem> invoiceItems = new ArrayList<>();

        String sql =
                "SELECT InvoiceItemId, InvoiceId, Description, "
                + "Quantity, UnitPrice, Amount "
                + "FROM InvoiceItem "
                + "ORDER BY InvoiceItemId";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                invoiceItems.add(
                        mapInvoiceItem(rs)
                );
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error retrieving invoice items: "
                    + e.getMessage()
            );
        }

        return invoiceItems;
    }


    // =========================================================
    // GET INVOICE ITEM BY ID
    // =========================================================
    public InvoiceItem findInvoiceItemById(int invoiceItemId) {

        String sql =
                "SELECT InvoiceItemId, InvoiceId, Description, "
                + "Quantity, UnitPrice, Amount "
                + "FROM InvoiceItem "
                + "WHERE InvoiceItemId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    invoiceItemId
            );

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    return mapInvoiceItem(rs);
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error retrieving invoice item: "
                    + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // GET ITEMS BY INVOICE ID
    // =========================================================
    public List<InvoiceItem> findInvoiceItemsByInvoiceId(
            int invoiceId) {

        List<InvoiceItem> invoiceItems = new ArrayList<>();

        String sql =
                "SELECT InvoiceItemId, InvoiceId, Description, "
                + "Quantity, UnitPrice, Amount "
                + "FROM InvoiceItem "
                + "WHERE InvoiceId = ? "
                + "ORDER BY InvoiceItemId";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    invoiceId
            );

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    invoiceItems.add(
                            mapInvoiceItem(rs)
                    );
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error retrieving invoice items: "
                    + e.getMessage()
            );
        }

        return invoiceItems;
    }


    // =========================================================
    // UPDATE INVOICE ITEM
    // =========================================================
    public boolean updateInvoiceItem(
            InvoiceItem invoiceItem) {

        String sql =
                "UPDATE InvoiceItem "
                + "SET InvoiceId = ?, "
                + "Description = ?, "
                + "Quantity = ?, "
                + "UnitPrice = ? "
                + "WHERE InvoiceItemId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    invoiceItem.getInvoice().getId()
            );

            stmt.setString(
                    2,
                    invoiceItem.getDescription()
            );

            stmt.setInt(
                    3,
                    invoiceItem.getQuantity()
            );

            stmt.setDouble(
                    4,
                    invoiceItem.getUnitPrice()
            );

            stmt.setInt(
                    5,
                    invoiceItem.getId()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error updating invoice item: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =========================================================
    // DELETE INVOICE ITEM
    // =========================================================
    public boolean deleteInvoiceItem(int invoiceItemId) {

        String sql =
                "DELETE FROM InvoiceItem "
                + "WHERE InvoiceItemId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    invoiceItemId
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println(
                    "Error deleting invoice item: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =========================================================
    // MAP INVOICE ITEM
    // =========================================================
    private InvoiceItem mapInvoiceItem(ResultSet rs)
            throws SQLException {

        InvoiceItem invoiceItem = new InvoiceItem();

        invoiceItem.setId(
                rs.getInt("InvoiceItemId")
        );

        Invoice invoice = new Invoice();

        invoice.setId(
                rs.getInt("InvoiceId")
        );

        invoiceItem.setInvoice(
                invoice
        );

        invoiceItem.setDescription(
                rs.getString("Description")
        );

        invoiceItem.setQuantity(
                rs.getInt("Quantity")
        );

        invoiceItem.setUnitPrice(
                rs.getDouble("UnitPrice")
        );

        invoiceItem.setAmount(
                rs.getDouble("Amount")
        );

        return invoiceItem;
    }
}