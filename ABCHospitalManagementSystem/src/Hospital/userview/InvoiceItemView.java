package hospital.userview;

import hospital.models.InvoiceItem;

import java.util.List;

public class InvoiceItemView {

    // =========================================================
    // DISPLAY ALL INVOICE ITEMS
    // =========================================================
    public void displayInvoiceItems(
            List<InvoiceItem> invoiceItems) {

        if (invoiceItems == null || invoiceItems.isEmpty()) {

            System.out.println(
                    "\nNo invoice items found."
            );

            return;
        }

        System.out.println(
                "\n========================== INVOICE ITEMS =========================="
        );

        System.out.printf(
                "%-5s %-10s %-30s %-10s %-15s %-15s%n",
                "ID",
                "Invoice",
                "Description",
                "Quantity",
                "Unit Price",
                "Amount"
        );

        System.out.println(
                "-------------------------------------------------------------------"
        );

        for (InvoiceItem invoiceItem : invoiceItems) {

            if (invoiceItem == null) {
                continue;
            }

            int invoiceId = 0;

            if (invoiceItem.getInvoice() != null) {
                invoiceId = invoiceItem.getInvoice().getId();
            }

            System.out.printf(
                    "%-5d %-10d %-30s %-10d %-15.2f %-15.2f%n",
                    invoiceItem.getId(),
                    invoiceId,
                    safeString(invoiceItem.getDescription()),
                    invoiceItem.getQuantity(),
                    invoiceItem.getUnitPrice(),
                    invoiceItem.getAmount()
            );
        }

        System.out.println(
                "==================================================================="
        );
    }


    // =========================================================
    // DISPLAY SINGLE INVOICE ITEM
    // =========================================================
    public void displayInvoiceItem(
            InvoiceItem invoiceItem) {

        if (invoiceItem == null) {

            System.out.println(
                    "\nInvoice item not found."
            );

            return;
        }

        System.out.println(
                "\n================ INVOICE ITEM DETAILS ================"
        );

        System.out.println(
                "Invoice Item ID : "
                + invoiceItem.getId()
        );

        if (invoiceItem.getInvoice() != null) {

            System.out.println(
                    "Invoice ID      : "
                    + invoiceItem.getInvoice().getId()
            );

        } else {

            System.out.println(
                    "Invoice ID      : N/A"
            );
        }

        System.out.println(
                "Description     : "
                + safeString(invoiceItem.getDescription())
        );

        System.out.println(
                "Quantity        : "
                + invoiceItem.getQuantity()
        );

        System.out.printf(
                "Unit Price      : %.2f%n",
                invoiceItem.getUnitPrice()
        );

        System.out.printf(
                "Amount          : %.2f%n",
                invoiceItem.getAmount()
        );

        System.out.println(
                "======================================================="
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