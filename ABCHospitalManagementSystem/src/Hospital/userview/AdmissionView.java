package hospital.userview;

import hospital.models.Admission;
import hospital.models.Bed;
import hospital.models.Room;
import hospital.models.Ward;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdmissionView {

    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern(
                    "dd-MM-yyyy HH:mm"
            );

    private final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern(
                    "dd-MM-yyyy"
            );


    // =========================================================
    // DISPLAY ALL ADMISSIONS
    // =========================================================

    public void displayAdmissions(
            List<Admission> admissions) {

        if (admissions == null ||
                admissions.isEmpty()) {

            System.out.println();
            System.out.println(
                    "No admissions found."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "================================================================================================================================================================"
        );

        System.out.printf(
                "%-6s %-22s %-18s %-12s %-16s %-16s %-22s %-15s%n",
                "ID",
                "PATIENT",
                "WARD",
                "ROOM",
                "BED",
                "ADMIT DATE",
                "REASON",
                "STATUS"
        );

        System.out.println(
                "================================================================================================================================================================"
        );

        for (Admission admission : admissions) {

            String patientName = "N/A";

            if (admission.getPatient() != null) {

                patientName =
                        admission.getPatient().getFirstName()
                                + " "
                                + admission.getPatient().getLastName();
            }

            String wardName = "N/A";
            String roomNumber = "N/A";
            String bedNumber = "N/A";

            if (admission.getBed() != null) {

                Bed bed = admission.getBed();

                bedNumber =
                        bed.getBedNumber() != null
                                ? bed.getBedNumber()
                                : "N/A";

                Room room = bed.getRoom();

                if (room != null) {

                    roomNumber =
                            room.getRoomNumber() != null
                                    ? room.getRoomNumber()
                                    : "N/A";

                    Ward ward = room.getWard();

                    if (ward != null) {

                        wardName =
                                ward.getName() != null
                                        ? ward.getName()
                                        : "N/A";
                    }
                }
            }

            String admissionDate = "N/A";

            if (admission.getAdmissionDate() != null) {

                admissionDate =
                        admission.getAdmissionDate()
                                .format(dateTimeFormatter);
            }

            String reason =
                    admission.getReason() != null
                            ? admission.getReason()
                            : "";

            String status =
                    admission.getStatus() != null
                            ? admission.getStatus()
                            : "";

            System.out.printf(
                    "%-6d %-22s %-18s %-12s %-16s %-16s %-22s %-15s%n",
                    admission.getId(),
                    patientName,
                    wardName,
                    roomNumber,
                    bedNumber,
                    admissionDate,
                    reason,
                    status
            );
        }

        System.out.println(
                "================================================================================================================================================================"
        );
    }


    // =========================================================
    // DISPLAY SINGLE ADMISSION
    // =========================================================

    public void displayAdmission(
            Admission admission) {

        if (admission == null) {

            System.out.println(
                    "Admission record not found."
            );

            return;
        }

        String patientName = "N/A";

        if (admission.getPatient() != null) {

            patientName =
                    admission.getPatient().getFirstName()
                            + " "
                            + admission.getPatient().getLastName();
        }

        String wardName = "N/A";
        String roomNumber = "N/A";
        String bedNumber = "N/A";
        String bedId = "N/A";

        if (admission.getBed() != null) {

            Bed bed = admission.getBed();

            bedId = String.valueOf(
                    bed.getId()
            );

            bedNumber =
                    bed.getBedNumber() != null
                            ? bed.getBedNumber()
                            : "N/A";

            Room room = bed.getRoom();

            if (room != null) {

                roomNumber =
                        room.getRoomNumber() != null
                                ? room.getRoomNumber()
                                : "N/A";

                Ward ward = room.getWard();

                if (ward != null) {

                    wardName =
                            ward.getName() != null
                                    ? ward.getName()
                                    : "N/A";
                }
            }
        }

        System.out.println();

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "              ADMISSION DETAILS"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Admission ID   : "
                        + admission.getId()
        );

        System.out.println(
                "Patient        : "
                        + patientName
        );

        System.out.println(
                "Ward           : "
                        + wardName
        );

        System.out.println(
                "Room           : "
                        + roomNumber
        );

        System.out.println(
                "Bed            : "
                        + bedNumber
        );

        System.out.println(
                "Bed ID         : "
                        + bedId
        );

        System.out.println(
                "Admit Date     : "
                        + (
                        admission.getAdmissionDate() != null
                                ? admission.getAdmissionDate()
                                .format(dateTimeFormatter)
                                : "N/A"
                )
        );

        System.out.println(
                "Discharge Date : "
                        + (
                        admission.getDischargeDate() != null
                                ? admission.getDischargeDate()
                                .format(dateFormatter)
                                : "N/A"
                )
        );

        System.out.println(
                "Reason         : "
                        + admission.getReason()
        );

        System.out.println(
                "Status         : "
                        + admission.getStatus()
        );

        System.out.println(
                "=============================================="
        );
    }


    // =========================================================
    // DISPLAY WARDS
    // =========================================================

    public void displayAvailableWards(
            List<Ward> wards) {

        if (wards == null ||
                wards.isEmpty()) {

            System.out.println(
                    "No wards with available beds."
            );

            return;
        }

        System.out.println();
        System.out.println(
                "AVAILABLE WARDS"
        );

        System.out.println(
                "------------------------------------------------"
        );

        for (int i = 0; i < wards.size(); i++) {

            Ward ward = wards.get(i);

            System.out.printf(
                    "%d. %s (%s)%n",
                    i + 1,
                    ward.getName(),
                    ward.getWardType()
            );
        }

        System.out.println(
                "------------------------------------------------"
        );
    }


    // =========================================================
    // DISPLAY ROOMS
    // =========================================================

    public void displayAvailableRooms(
            List<Room> rooms) {

        if (rooms == null ||
                rooms.isEmpty()) {

            System.out.println(
                    "No rooms with available beds."
            );

            return;
        }

        System.out.println();
        System.out.println(
                "AVAILABLE ROOMS"
        );

        System.out.println(
                "------------------------------------------------"
        );

        for (int i = 0; i < rooms.size(); i++) {

            Room room = rooms.get(i);

            System.out.printf(
                    "%d. %s (%s)%n",
                    i + 1,
                    room.getRoomNumber(),
                    room.getRoomType()
            );
        }

        System.out.println(
                "------------------------------------------------"
        );
    }


    // =========================================================
    // DISPLAY BEDS
    // =========================================================

    public void displayAvailableBeds(
            List<Bed> beds) {

        if (beds == null ||
                beds.isEmpty()) {

            System.out.println(
                    "No available beds in this room."
            );

            return;
        }

        System.out.println();
        System.out.println(
                "AVAILABLE BEDS"
        );

        System.out.println(
                "------------------------------------------------"
        );

        for (int i = 0; i < beds.size(); i++) {

            Bed bed = beds.get(i);

            System.out.printf(
                    "%d. %s%n",
                    i + 1,
                    bed.getBedNumber()
            );
        }

        System.out.println(
                "------------------------------------------------"
        );
    }


    // =========================================================
    // MESSAGES
    // =========================================================

    public void displayAdmissionCreated() {

        System.out.println();

        System.out.println(
                "Patient admitted successfully."
        );
    }


    public void displayAdmissionUpdated() {

        System.out.println();

        System.out.println(
                "Admission record updated successfully."
        );
    }


    public void displayAdmissionDeleted() {

        System.out.println();

        System.out.println(
                "Admission record deleted successfully."
        );
    }


    public void displayPatientDischarged() {

        System.out.println();

        System.out.println(
                "Patient discharged successfully."
        );
    }
}