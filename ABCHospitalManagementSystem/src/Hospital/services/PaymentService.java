package hospital.services;

import hospital.dao.PaymentDAO;
import hospital.models.Payment;

import java.time.LocalDate;
import java.util.List;

public class PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAO();
    }

    // =========================================================
    // ADD PAYMENT
    // =========================================================
    public boolean addPayment(Payment payment) {

        if (payment == null) {

            System.err.println(
                    "Validation Error: Payment object cannot be null."
            );

            return false;
        }

        // Validate invoice
        if (payment.getInvoice() == null
                || payment.getInvoice().getId() <= 0) {

            System.err.println(
                    "Validation Error: Valid Invoice is required."
            );

            return false;
        }

        // Validate payment amount
        if (payment.getAmount() <= 0) {

            System.err.println(
                    "Validation Error: Payment amount must be greater than zero."
            );

            return false;
        }

        // Validate payment date
        if (payment.getPaymentDate() == null) {

            payment.setPaymentDate(
                    LocalDate.now()
            );
        }

        return paymentDAO.addPayment(
                payment
        );
    }


    // =========================================================
    // GET ALL PAYMENTS
    // =========================================================
    public List<Payment> getAllPayments() {

        return paymentDAO.findAllPayments();
    }


    // =========================================================
    // GET PAYMENT BY ID
    // =========================================================
    public Payment getPaymentById(int paymentId) {

        if (paymentId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Payment ID."
            );

            return null;
        }

        return paymentDAO.findPaymentById(
                paymentId
        );
    }


    // =========================================================
    // GET PAYMENTS BY INVOICE ID
    // =========================================================
    public List<Payment> getPaymentsByInvoiceId(
            int invoiceId) {

        if (invoiceId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Invoice ID."
            );

            return List.of();
        }

        return paymentDAO.findPaymentsByInvoiceId(
                invoiceId
        );
    }


    // =========================================================
    // UPDATE PAYMENT
    // =========================================================
    public boolean updatePayment(Payment payment) {

        if (payment == null) {

            System.err.println(
                    "Validation Error: Payment object cannot be null."
            );

            return false;
        }

        // Validate payment ID
        if (payment.getId() <= 0) {

            System.err.println(
                    "Validation Error: Invalid Payment ID."
            );

            return false;
        }

        // Validate invoice
        if (payment.getInvoice() == null
                || payment.getInvoice().getId() <= 0) {

            System.err.println(
                    "Validation Error: Valid Invoice is required."
            );

            return false;
        }

        // Validate payment amount
        if (payment.getAmount() <= 0) {

            System.err.println(
                    "Validation Error: Payment amount must be greater than zero."
            );

            return false;
        }

        // Validate payment date
        if (payment.getPaymentDate() == null) {

            System.err.println(
                    "Validation Error: Payment date is required."
            );

            return false;
        }

        return paymentDAO.updatePayment(
                payment
        );
    }


    // =========================================================
    // DELETE PAYMENT
    // =========================================================
    public boolean removePayment(int paymentId) {

        if (paymentId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Payment ID."
            );

            return false;
        }

        return paymentDAO.deletePayment(
                paymentId
        );
    }
}