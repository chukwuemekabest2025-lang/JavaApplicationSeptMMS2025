package hospital.services;

import hospital.dao.AdmissionDAO;
import hospital.models.Admission;
import hospital.models.Bed;
import hospital.models.Room;
import hospital.models.Ward;

import java.time.LocalDate;
import java.util.List;

public class AdmissionService {

    private final AdmissionDAO admissionDAO;

    public AdmissionService() {
        admissionDAO = new AdmissionDAO();
    }


    // =========================================================
    // CREATE ADMISSION
    // =========================================================

    public boolean addAdmission(Admission admission) {

        if (admission == null) {
            System.out.println("Admission cannot be null.");
            return false;
        }

        if (admission.getPatient() == null ||
                admission.getPatient().getPatientId() <= 0) {

            System.out.println("Valid patient is required.");
            return false;
        }

        if (admission.getBed() == null ||
                admission.getBed().getId() <= 0) {

            System.out.println("Valid bed assignment is required.");
            return false;
        }

        if (admission.getAdmissionDate() == null) {

            System.out.println("Admission date is required.");
            return false;
        }

        if (admission.getReason() == null ||
                admission.getReason().trim().isEmpty()) {

            System.out.println("Admission reason is required.");
            return false;
        }

        if (admission.getStatus() == null ||
                admission.getStatus().trim().isEmpty()) {

            admission.setStatus("Admitted");
        }

        return admissionDAO.addAdmission(admission);
    }


    // =========================================================
    // GET ALL ADMISSIONS
    // =========================================================

    public List<Admission> getAllAdmissions() {

        return admissionDAO.getAllAdmissions();
    }


    // =========================================================
    // GET ADMISSION BY ID
    // =========================================================

    public Admission getAdmissionById(int admissionId) {

        if (admissionId <= 0) {

            System.out.println("Invalid admission ID.");
            return null;
        }

        return admissionDAO.getAdmissionById(admissionId);
    }


    // =========================================================
    // UPDATE ADMISSION
    // =========================================================

    public boolean updateAdmission(
            Admission admission) {

        if (admission == null) {

            System.out.println("Admission cannot be null.");
            return false;
        }

        if (admission.getId() <= 0) {

            System.out.println("Invalid admission ID.");
            return false;
        }

        if (admission.getPatient() == null ||
                admission.getPatient().getPatientId() <= 0) {

            System.out.println("Valid patient is required.");
            return false;
        }

        if (admission.getBed() == null ||
                admission.getBed().getId() <= 0) {

            System.out.println("Valid bed assignment is required.");
            return false;
        }

        if (admission.getAdmissionDate() == null) {

            System.out.println("Admission date is required.");
            return false;
        }

        if (admission.getReason() == null ||
                admission.getReason().trim().isEmpty()) {

            System.out.println("Admission reason is required.");
            return false;
        }

        return admissionDAO.updateAdmission(admission);
    }


    // =========================================================
    // DISCHARGE PATIENT
    // =========================================================

    public boolean dischargePatient(
            int admissionId,
            LocalDate dischargeDate) {

        if (admissionId <= 0) {

            System.out.println("Invalid admission ID.");
            return false;
        }

        if (dischargeDate == null) {

            System.out.println(
                    "Discharge date is required."
            );

            return false;
        }

        return admissionDAO.dischargePatient(
                admissionId,
                dischargeDate
        );
    }


    // =========================================================
    // DELETE ADMISSION
    // =========================================================

    public boolean deleteAdmission(int admissionId) {

        if (admissionId <= 0) {

            System.out.println("Invalid admission ID.");
            return false;
        }

        return admissionDAO.deleteAdmission(admissionId);
    }


    // =========================================================
    // GET ADMISSIONS BY PATIENT
    // =========================================================

    public List<Admission> getAdmissionsByPatient(
            int patientId) {

        if (patientId <= 0) {

            System.out.println("Invalid patient ID.");
            return List.of();
        }

        return admissionDAO.getAdmissionsByPatient(
                patientId
        );
    }


    // =========================================================
    // GET AVAILABLE WARDS
    // =========================================================

    public List<Ward> getAvailableWards() {

        return admissionDAO.getAvailableWards();
    }


    // =========================================================
    // GET AVAILABLE ROOMS
    // =========================================================

    public List<Room> getAvailableRooms(
            int wardId) {

        if (wardId <= 0) {

            return List.of();
        }

        return admissionDAO.getAvailableRooms(
                wardId
        );
    }


    // =========================================================
    // GET AVAILABLE BEDS
    // =========================================================

    public List<Bed> getAvailableBeds(
            int roomId) {

        if (roomId <= 0) {

            return List.of();
        }

        return admissionDAO.getAvailableBeds(
                roomId
        );
    }
}