package hospital.services;

import hospital.dao.PharmacistDAO;
import hospital.models.Pharmacist;

import java.util.List;

public class PharmacistService {

    private final PharmacistDAO pharmacistDAO;

    public PharmacistService() {
        this.pharmacistDAO =
                new PharmacistDAO();
    }

    public boolean addPharmacist(
            Pharmacist pharmacist) {

        if (pharmacist == null) {

            System.err.println(
                    "Validation Error: Pharmacist object cannot be null."
            );
            return false;
        }

        if (pharmacist.getStaffId() <= 0) {

            System.err.println(
                    "Validation Error: A valid Staff ID must be assigned."
            );
            return false;
        }

        if (pharmacist.getQualification() == null
                || pharmacist.getQualification().trim().isEmpty()) {

            System.err.println(
                    "Validation Error: Pharmacist qualification cannot be empty."
            );
            return false;
        }

        if (pharmacist.getLicenseNumber() == null
                || pharmacist.getLicenseNumber().trim().isEmpty()) {

            System.err.println(
                    "Validation Error: Pharmacist license number cannot be empty."
            );
            return false;
        }

        return pharmacistDAO.addPharmacist(pharmacist);
    }

    public List<Pharmacist> getAllPharmacists() {

        return pharmacistDAO.findAllPharmacists();
    }

    public Pharmacist getPharmacistById(
            int pharmacistId) {

        if (pharmacistId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Pharmacist ID."
            );
            return null;
        }

        return pharmacistDAO.findPharmacistById(
                pharmacistId
        );
    }

    public Pharmacist getPharmacistByStaffId(
            int staffId) {

        if (staffId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Staff ID."
            );
            return null;
        }

        return pharmacistDAO.findPharmacistByStaffId(
                staffId
        );
    }

    public boolean updatePharmacist(
            Pharmacist pharmacist) {

        if (pharmacist == null) {

            System.err.println(
                    "Validation Error: Pharmacist object cannot be null."
            );
            return false;
        }

        if (pharmacist.getPharmacistId() <= 0) {

            System.err.println(
                    "Validation Error: Invalid Pharmacist ID."
            );
            return false;
        }

        if (pharmacist.getStaffId() <= 0) {

            System.err.println(
                    "Validation Error: A valid Staff ID must be assigned."
            );
            return false;
        }

        if (pharmacist.getQualification() == null
                || pharmacist.getQualification().trim().isEmpty()) {

            System.err.println(
                    "Validation Error: Pharmacist qualification cannot be empty."
            );
            return false;
        }

        if (pharmacist.getLicenseNumber() == null
                || pharmacist.getLicenseNumber().trim().isEmpty()) {

            System.err.println(
                    "Validation Error: Pharmacist license number cannot be empty."
            );
            return false;
        }

        return pharmacistDAO.updatePharmacist(
                pharmacist
        );
    }

    public boolean removePharmacist(
            int pharmacistId) {

        if (pharmacistId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Pharmacist ID."
            );
            return false;
        }

        return pharmacistDAO.deletePharmacist(
                pharmacistId
        );
    }
}