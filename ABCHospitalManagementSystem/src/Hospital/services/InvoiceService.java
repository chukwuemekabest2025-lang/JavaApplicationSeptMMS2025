package hospital.services;

import hospital.dao.InvoiceDAO;
import hospital.models.Invoice;

import java.time.LocalDate;
import java.util.List;

public class InvoiceService {

    private final InvoiceDAO invoiceDAO;

    public InvoiceService() {
        this.invoiceDAO = new InvoiceDAO();
    }

    // =========================================================
    // ADD INVOICE
    // =========================================================
    public boolean addInvoice(Invoice invoice) {

        if (invoice == null) {

            System.err.println(
                    "Validation Error: Invoice object cannot be null."
            );

            return false;
        }

        // Validate patient
        if (invoice.getPatient() == null
                || invoice.getPatient().getPatientId() <= 0) {

            System.err.println(
                    "Validation Error: Valid Patient is required."
            );

            return false;
        }

        // Validate invoice date
        if (invoice.getInvoiceDate() == null) {

            invoice.setInvoiceDate(
                    LocalDate.now()
            );
        }

        // Validate total amount
        if (invoice.getTotalAmount() < 0) {

            System.err.println(
                    "Validation Error: Total amount cannot be negative."
            );

            return false;
        }

        return invoiceDAO.addInvoice(invoice);
    }


    // =========================================================
    // GET ALL INVOICES
    // =========================================================
    public List<Invoice> getAllInvoices() {

        return invoiceDAO.findAllInvoices();
    }


    // =========================================================
    // GET INVOICE BY ID
    // =========================================================
    public Invoice getInvoiceById(int invoiceId) {

        if (invoiceId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Invoice ID."
            );

            return null;
        }

        return invoiceDAO.findInvoiceById(invoiceId);
    }


    // =========================================================
    // GET INVOICES BY PATIENT ID
    // =========================================================
    public List<Invoice> getInvoicesByPatientId(int patientId) {

        if (patientId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Patient ID."
            );

            return List.of();
        }

        return invoiceDAO.findInvoicesByPatientId(
                patientId
        );
    }


    // =========================================================
    // UPDATE INVOICE
    // =========================================================
    public boolean updateInvoice(Invoice invoice) {

        if (invoice == null) {

            System.err.println(
                    "Validation Error: Invoice object cannot be null."
            );

            return false;
        }

        // Validate invoice ID
        if (invoice.getId() <= 0) {

            System.err.println(
                    "Validation Error: Invalid Invoice ID."
            );

            return false;
        }

        // Validate patient
        if (invoice.getPatient() == null
                || invoice.getPatient().getPatientId() <= 0) {

            System.err.println(
                    "Validation Error: Valid Patient is required."
            );

            return false;
        }

        // Validate invoice date
        if (invoice.getInvoiceDate() == null) {

            System.err.println(
                    "Validation Error: Invoice date is required."
            );

            return false;
        }

        // Validate total amount
        if (invoice.getTotalAmount() < 0) {

            System.err.println(
                    "Validation Error: Total amount cannot be negative."
            );

            return false;
        }

        return invoiceDAO.updateInvoice(invoice);
    }


    // =========================================================
    // DELETE INVOICE
    // =========================================================
    public boolean removeInvoice(int invoiceId) {

        if (invoiceId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Invoice ID."
            );

            return false;
        }

        return invoiceDAO.deleteInvoice(invoiceId);
    }
}
