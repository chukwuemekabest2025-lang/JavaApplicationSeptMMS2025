package hospital.services;

import hospital.dao.InvoiceItemDAO;
import hospital.models.InvoiceItem;

import java.util.List;

public class InvoiceItemService {

    private final InvoiceItemDAO invoiceItemDAO;

    public InvoiceItemService() {
        this.invoiceItemDAO = new InvoiceItemDAO();
    }

    // =========================================================
    // ADD INVOICE ITEM
    // =========================================================
    public boolean addInvoiceItem(InvoiceItem invoiceItem) {

        if (invoiceItem == null) {

            System.err.println(
                    "Validation Error: Invoice item cannot be null."
            );

            return false;
        }

        // Validate invoice
        if (invoiceItem.getInvoice() == null
                || invoiceItem.getInvoice().getId() <= 0) {

            System.err.println(
                    "Validation Error: Valid Invoice is required."
            );

            return false;
        }

        // Validate description
        if (invoiceItem.getDescription() == null
                || invoiceItem.getDescription().trim().isEmpty()) {

            System.err.println(
                    "Validation Error: Description is required."
            );

            return false;
        }

        // Validate quantity
        if (invoiceItem.getQuantity() <= 0) {

            System.err.println(
                    "Validation Error: Quantity must be greater than zero."
            );

            return false;
        }

        // Validate unit price
        if (invoiceItem.getUnitPrice() < 0) {

            System.err.println(
                    "Validation Error: Unit price cannot be negative."
            );

            return false;
        }

        return invoiceItemDAO.addInvoiceItem(
                invoiceItem
        );
    }


    // =========================================================
    // GET ALL INVOICE ITEMS
    // =========================================================
    public List<InvoiceItem> getAllInvoiceItems() {

        return invoiceItemDAO.findAllInvoiceItems();
    }


    // =========================================================
    // GET INVOICE ITEM BY ID
    // =========================================================
    public InvoiceItem getInvoiceItemById(
            int invoiceItemId) {

        if (invoiceItemId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Invoice Item ID."
            );

            return null;
        }

        return invoiceItemDAO.findInvoiceItemById(
                invoiceItemId
        );
    }


    // =========================================================
    // GET ITEMS BY INVOICE ID
    // =========================================================
    public List<InvoiceItem> getInvoiceItemsByInvoiceId(
            int invoiceId) {

        if (invoiceId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Invoice ID."
            );

            return List.of();
        }

        return invoiceItemDAO.findInvoiceItemsByInvoiceId(
                invoiceId
        );
    }


    // =========================================================
    // UPDATE INVOICE ITEM
    // =========================================================
    public boolean updateInvoiceItem(
            InvoiceItem invoiceItem) {

        if (invoiceItem == null) {

            System.err.println(
                    "Validation Error: Invoice item cannot be null."
            );

            return false;
        }

        // Validate invoice item ID
        if (invoiceItem.getId() <= 0) {

            System.err.println(
                    "Validation Error: Invalid Invoice Item ID."
            );

            return false;
        }

        // Validate invoice
        if (invoiceItem.getInvoice() == null
                || invoiceItem.getInvoice().getId() <= 0) {

            System.err.println(
                    "Validation Error: Valid Invoice is required."
            );

            return false;
        }

        // Validate description
        if (invoiceItem.getDescription() == null
                || invoiceItem.getDescription().trim().isEmpty()) {

            System.err.println(
                    "Validation Error: Description is required."
            );

            return false;
        }

        // Validate quantity
        if (invoiceItem.getQuantity() <= 0) {

            System.err.println(
                    "Validation Error: Quantity must be greater than zero."
            );

            return false;
        }

        // Validate unit price
        if (invoiceItem.getUnitPrice() < 0) {

            System.err.println(
                    "Validation Error: Unit price cannot be negative."
            );

            return false;
        }

        return invoiceItemDAO.updateInvoiceItem(
                invoiceItem
        );
    }


    // =========================================================
    // DELETE INVOICE ITEM
    // =========================================================
    public boolean removeInvoiceItem(int invoiceItemId) {

        if (invoiceItemId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Invoice Item ID."
            );

            return false;
        }

        return invoiceItemDAO.deleteInvoiceItem(
                invoiceItemId
        );
    }
}