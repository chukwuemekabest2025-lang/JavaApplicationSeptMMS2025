package hospital.userview;

import hospital.models.Payment;

import java.util.List;

public class PaymentView {

    // =========================================================
    // DISPLAY ALL PAYMENTS
    // =========================================================
    public void displayPayments(
            List<Payment> payments) {

        if (payments == null || payments.isEmpty()) {

            System.out.println(
                    "\nNo payments found."
            );

            return;
        }

        System.out.println(
                "\n========================== PAYMENTS =========================="
        );

        System.out.printf(
                "%-5s %-10s %-15s %-15s %-20s%n",
                "ID",
                "Invoice",
                "Amount",
                "Payment Date",
                "Payment Method"
        );

        System.out.println(
                "---------------------------------------------------------------"
        );

        for (Payment payment : payments) {

            if (payment == null) {
                continue;
            }

            int invoiceId = 0;

            if (payment.getInvoice() != null) {
                invoiceId = payment.getInvoice().getId();
            }

            System.out.printf(
                    "%-5d %-10d %-15.2f %-15s %-20s%n",
                    payment.getId(),
                    invoiceId,
                    payment.getAmount(),
                    payment.getPaymentDate(),
                    safeString(payment.getPaymentMethod())
            );
        }

        System.out.println(
                "==============================================================="
        );
    }


    // =========================================================
    // DISPLAY SINGLE PAYMENT
    // =========================================================
    public void displayPayment(
            Payment payment) {

        if (payment == null) {

            System.out.println(
                    "\nPayment not found."
            );

            return;
        }

        System.out.println(
                "\n================ PAYMENT DETAILS ================"
        );

        System.out.println(
                "Payment ID     : "
                + payment.getId()
        );

        if (payment.getInvoice() != null) {

            System.out.println(
                    "Invoice ID     : "
                    + payment.getInvoice().getId()
            );

        } else {

            System.out.println(
                    "Invoice ID     : N/A"
            );
        }

        System.out.printf(
                "Amount         : %.2f%n",
                payment.getAmount()
        );

        System.out.println(
                "Payment Date   : "
                + payment.getPaymentDate()
        );

        System.out.println(
                "Payment Method : "
                + safeString(payment.getPaymentMethod())
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