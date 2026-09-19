package hospital.userview;

import hospital.models.Invoice;

import java.util.List;

public class InvoiceView {

    // =========================================================
    // DISPLAY ALL INVOICES
    // =========================================================
    public void displayInvoices(List<Invoice> invoices) {

        if (invoices == null || invoices.isEmpty()) {

            System.out.println(
                    "\nNo invoices found."
            );

            return;
        }

        System.out.println(
                "\n======================== INVOICES ========================"
        );

        System.out.printf(
                "%-5s %-12s %-15s %-15s %-15s%n",
                "ID",
                "Patient ID",
                "Invoice Date",
                "Total Amount",
                "Status"
        );

        System.out.println(
                "------------------------------------------------------------"
        );

        for (Invoice invoice : invoices) {

            if (invoice == null) {
                continue;
            }

            int patientId = 0;

            if (invoice.getPatient() != null) {
                patientId = invoice.getPatient().getPatientId();
            }

            System.out.printf(
                    "%-5d %-12d %-15s %-15.2f %-15s%n",
                    invoice.getId(),
                    patientId,
                    invoice.getInvoiceDate(),
                    invoice.getTotalAmount(),
                    safeString(invoice.getStatus())
            );
        }

        System.out.println(
                "============================================================"
        );
    }


    // =========================================================
    // DISPLAY SINGLE INVOICE
    // =========================================================
    public void displayInvoice(Invoice invoice) {

        if (invoice == null) {

            System.out.println(
                    "\nInvoice not found."
            );

            return;
        }

        System.out.println(
                "\n================ INVOICE DETAILS ================"
        );

        System.out.println(
                "Invoice ID     : " + invoice.getId()
        );

        if (invoice.getPatient() != null) {

            System.out.println(
                    "Patient ID     : "
                    + invoice.getPatient().getPatientId()
            );

        } else {

            System.out.println(
                    "Patient ID     : N/A"
            );
        }

        System.out.println(
                "Invoice Date   : " + invoice.getInvoiceDate()
        );

        System.out.printf(
                "Total Amount   : %.2f%n",
                invoice.getTotalAmount()
        );

        System.out.println(
                "Status         : "
                + safeString(invoice.getStatus())
        );

        System.out.println(
                "================================================="
        );
    }


    // =========================================================
    // SAFE STRING
    // =========================================================
    private String safeString(String value) {

        if (value == null || value.trim().isEmpty()) {
            return "N/A";
        }

        return value;
    }
}