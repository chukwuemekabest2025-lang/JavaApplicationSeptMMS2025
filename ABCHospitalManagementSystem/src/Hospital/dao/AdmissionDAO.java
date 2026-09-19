package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Admission;
import hospital.models.Bed;
import hospital.models.Patient;
import hospital.models.Room;
import hospital.models.Ward;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdmissionDAO {

    // =========================================================
    // CREATE ADMISSION
    // =========================================================

    public boolean addAdmission(Admission admission) {

        String insertSql = """
                INSERT INTO Admission
                (PatientId, BedId, AdmissionDate, DischargeDate, Reason, Status)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        String occupyBedSql = """
                UPDATE Bed
                SET IsOccupied = 1
                WHERE BedId = ?
                AND IsOccupied = 0
                """;

        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            // -------------------------------------------------
            // Make sure the selected bed is still available
            // -------------------------------------------------

            try (PreparedStatement bedStmt =
                         conn.prepareStatement(occupyBedSql)) {

                bedStmt.setInt(1, admission.getBed().getId());

                int bedUpdated = bedStmt.executeUpdate();

                if (bedUpdated == 0) {
                    System.out.println(
                            "The selected bed is already occupied or does not exist."
                    );

                    conn.rollback();
                    return false;
                }
            }

            // -------------------------------------------------
            // Create admission
            // -------------------------------------------------

            try (PreparedStatement stmt =
                         conn.prepareStatement(
                                 insertSql,
                                 Statement.RETURN_GENERATED_KEYS)) {

                stmt.setInt(
                        1,
                        admission.getPatient().getPatientId()
                );

                stmt.setInt(
                        2,
                        admission.getBed().getId()
                );

                stmt.setObject(
                        3,
                        admission.getAdmissionDate()
                );

                if (admission.getDischargeDate() != null) {

                    stmt.setDate(
                            4,
                            Date.valueOf(admission.getDischargeDate())
                    );

                } else {

                    stmt.setNull(
                            4,
                            java.sql.Types.DATE
                    );
                }

                stmt.setString(
                        5,
                        admission.getReason()
                );

                stmt.setString(
                        6,
                        admission.getStatus()
                );

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected == 0) {

                    conn.rollback();
                    return false;
                }

                try (ResultSet generatedKeys =
                             stmt.getGeneratedKeys()) {

                    if (generatedKeys.next()) {

                        admission.setId(
                                generatedKeys.getInt(1)
                        );
                    }
                }
            }

            conn.commit();

            return true;

        } catch (SQLException e) {

            if (conn != null) {

                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }

            System.out.println(
                    "Error creating admission: "
                    + e.getMessage()
            );

            e.printStackTrace();

        } finally {

            if (conn != null) {

                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }


    // =========================================================
    // GET ALL ADMISSIONS
    // =========================================================

    public List<Admission> getAllAdmissions() {

        List<Admission> admissions = new ArrayList<>();

        String sql = """
                SELECT
                    a.AdmissionId,
                    a.PatientId,
                    a.BedId,
                    a.AdmissionDate,
                    a.DischargeDate,
                    a.Reason,
                    a.Status,

                    p.FirstName AS PatientFirstName,
                    p.LastName AS PatientLastName,

                    b.BedNumber,
                    b.IsOccupied,

                    r.RoomId,
                    r.RoomNumber,
                    r.RoomType,

                    w.WardId,
                    w.Name AS WardName,
                    w.WardType

                FROM Admission a

                INNER JOIN Patient pt
                    ON a.PatientId = pt.PatientId

                INNER JOIN Person p
                    ON pt.PersonId = p.PersonId

                INNER JOIN Bed b
                    ON a.BedId = b.BedId

                INNER JOIN Room r
                    ON b.RoomId = r.RoomId

                INNER JOIN Ward w
                    ON r.WardId = w.WardId

                ORDER BY a.AdmissionDate DESC
                """;

        try (Connection conn =
                     DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql);
             ResultSet rs =
                     stmt.executeQuery()) {

            while (rs.next()) {

                admissions.add(
                        mapAdmission(rs)
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error retrieving admissions: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return admissions;
    }


    // =========================================================
    // GET ADMISSION BY ID
    // =========================================================

    public Admission getAdmissionById(int id) {

        String sql = """
                SELECT
                    a.AdmissionId,
                    a.PatientId,
                    a.BedId,
                    a.AdmissionDate,
                    a.DischargeDate,
                    a.Reason,
                    a.Status,

                    p.FirstName AS PatientFirstName,
                    p.LastName AS PatientLastName,

                    b.BedNumber,
                    b.IsOccupied,

                    r.RoomId,
                    r.RoomNumber,
                    r.RoomType,

                    w.WardId,
                    w.Name AS WardName,
                    w.WardType

                FROM Admission a

                INNER JOIN Patient pt
                    ON a.PatientId = pt.PatientId

                INNER JOIN Person p
                    ON pt.PersonId = p.PersonId

                INNER JOIN Bed b
                    ON a.BedId = b.BedId

                INNER JOIN Room r
                    ON b.RoomId = r.RoomId

                INNER JOIN Ward w
                    ON r.WardId = w.WardId

                WHERE a.AdmissionId = ?
                """;

        try (Connection conn =
                     DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs =
                         stmt.executeQuery()) {

                if (rs.next()) {

                    return mapAdmission(rs);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error retrieving admission: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // UPDATE ADMISSION
    // =========================================================

    public boolean updateAdmission(Admission admission) {

        String sql = """
                UPDATE Admission
                SET PatientId = ?,
                    BedId = ?,
                    AdmissionDate = ?,
                    DischargeDate = ?,
                    Reason = ?,
                    Status = ?
                WHERE AdmissionId = ?
                """;

        try (Connection conn =
                     DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    admission.getPatient().getPatientId()
            );

            stmt.setInt(
                    2,
                    admission.getBed().getId()
            );

            stmt.setObject(
                    3,
                    admission.getAdmissionDate()
            );

            if (admission.getDischargeDate() != null) {

                stmt.setDate(
                        4,
                        Date.valueOf(
                                admission.getDischargeDate()
                        )
                );

            } else {

                stmt.setNull(
                        4,
                        java.sql.Types.DATE
                );
            }

            stmt.setString(
                    5,
                    admission.getReason()
            );

            stmt.setString(
                    6,
                    admission.getStatus()
            );

            stmt.setInt(
                    7,
                    admission.getId()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error updating admission: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // DISCHARGE PATIENT
    // =========================================================

    public boolean dischargePatient(
            int admissionId,
            LocalDate dischargeDate) {

        String findBedSql = """
                SELECT BedId
                FROM Admission
                WHERE AdmissionId = ?
                AND Status = 'Admitted'
                """;

        String updateAdmissionSql = """
                UPDATE Admission
                SET DischargeDate = ?,
                    Status = 'Discharged'
                WHERE AdmissionId = ?
                AND Status = 'Admitted'
                """;

        String freeBedSql = """
                UPDATE Bed
                SET IsOccupied = 0
                WHERE BedId = ?
                """;

        Connection conn = null;

        try {

            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            int bedId;

            // -------------------------------------------------
            // Find the bed belonging to this admission
            // -------------------------------------------------

            try (PreparedStatement stmt =
                         conn.prepareStatement(findBedSql)) {

                stmt.setInt(1, admissionId);

                try (ResultSet rs =
                             stmt.executeQuery()) {

                    if (!rs.next()) {

                        System.out.println(
                                "Active admission not found."
                        );

                        conn.rollback();
                        return false;
                    }

                    bedId = rs.getInt("BedId");
                }
            }

            // -------------------------------------------------
            // Discharge patient
            // -------------------------------------------------

            try (PreparedStatement stmt =
                         conn.prepareStatement(updateAdmissionSql)) {

                stmt.setDate(
                        1,
                        Date.valueOf(dischargeDate)
                );

                stmt.setInt(
                        2,
                        admissionId
                );

                if (stmt.executeUpdate() == 0) {

                    conn.rollback();
                    return false;
                }
            }

            // -------------------------------------------------
            // Free the bed
            // -------------------------------------------------

            try (PreparedStatement stmt =
                         conn.prepareStatement(freeBedSql)) {

                stmt.setInt(1, bedId);
                stmt.executeUpdate();
            }

            conn.commit();

            return true;

        } catch (SQLException e) {

            if (conn != null) {

                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }

            System.out.println(
                    "Error discharging patient: "
                    + e.getMessage()
            );

            e.printStackTrace();

        } finally {

            if (conn != null) {

                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }


    // =========================================================
    // DELETE ADMISSION
    // =========================================================

    public boolean deleteAdmission(int id) {

        String findBedSql = """
                SELECT BedId, Status
                FROM Admission
                WHERE AdmissionId = ?
                """;

        String deleteSql = """
                DELETE FROM Admission
                WHERE AdmissionId = ?
                """;

        String freeBedSql = """
                UPDATE Bed
                SET IsOccupied = 0
                WHERE BedId = ?
                """;

        Connection conn = null;

        try {

            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            int bedId = 0;
            String status = null;

            try (PreparedStatement stmt =
                         conn.prepareStatement(findBedSql)) {

                stmt.setInt(1, id);

                try (ResultSet rs =
                             stmt.executeQuery()) {

                    if (!rs.next()) {

                        conn.rollback();
                        return false;
                    }

                    bedId = rs.getInt("BedId");
                    status = rs.getString("Status");
                }
            }

            try (PreparedStatement stmt =
                         conn.prepareStatement(deleteSql)) {

                stmt.setInt(1, id);

                if (stmt.executeUpdate() == 0) {

                    conn.rollback();
                    return false;
                }
            }

            // If an active admission is deleted,
            // make the bed available again.

            if ("Admitted".equalsIgnoreCase(status)) {

                try (PreparedStatement stmt =
                             conn.prepareStatement(freeBedSql)) {

                    stmt.setInt(1, bedId);
                    stmt.executeUpdate();
                }
            }

            conn.commit();

            return true;

        } catch (SQLException e) {

            if (conn != null) {

                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }

            System.out.println(
                    "Error deleting admission: "
                    + e.getMessage()
            );

            e.printStackTrace();

        } finally {

            if (conn != null) {

                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }


    // =========================================================
    // GET ADMISSIONS BY PATIENT
    // =========================================================

    public List<Admission> getAdmissionsByPatient(
            int patientId) {

        List<Admission> admissions = new ArrayList<>();

        String sql = """
                SELECT
                    a.AdmissionId,
                    a.PatientId,
                    a.BedId,
                    a.AdmissionDate,
                    a.DischargeDate,
                    a.Reason,
                    a.Status,

                    p.FirstName AS PatientFirstName,
                    p.LastName AS PatientLastName,

                    b.BedNumber,
                    b.IsOccupied,

                    r.RoomId,
                    r.RoomNumber,
                    r.RoomType,

                    w.WardId,
                    w.Name AS WardName,
                    w.WardType

                FROM Admission a

                INNER JOIN Patient pt
                    ON a.PatientId = pt.PatientId

                INNER JOIN Person p
                    ON pt.PersonId = p.PersonId

                INNER JOIN Bed b
                    ON a.BedId = b.BedId

                INNER JOIN Room r
                    ON b.RoomId = r.RoomId

                INNER JOIN Ward w
                    ON r.WardId = w.WardId

                WHERE a.PatientId = ?

                ORDER BY a.AdmissionDate DESC
                """;

        try (Connection conn =
                     DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, patientId);

            try (ResultSet rs =
                         stmt.executeQuery()) {

                while (rs.next()) {

                    admissions.add(
                            mapAdmission(rs)
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error retrieving patient admissions: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return admissions;
    }


    // =========================================================
    // GET AVAILABLE WARDS
    // =========================================================

    public List<Ward> getAvailableWards() {

        List<Ward> wards = new ArrayList<>();

        String sql = """
                SELECT DISTINCT
                    w.WardId,
                    w.Name,
                    w.WardType,
                    w.Capacity
                FROM Ward w
                INNER JOIN Room r
                    ON w.WardId = r.WardId
                INNER JOIN Bed b
                    ON r.RoomId = b.RoomId
                WHERE b.IsOccupied = 0
                ORDER BY w.WardId
                """;

        try (Connection conn =
                     DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql);
             ResultSet rs =
                     stmt.executeQuery()) {

            while (rs.next()) {

                Ward ward = new Ward();

                ward.setId(
                        rs.getInt("WardId")
                );

                ward.setName(
                        rs.getString("Name")
                );

                ward.setWardType(
                        rs.getString("WardType")
                );

                ward.setCapacity(
                        rs.getInt("Capacity")
                );

                wards.add(ward);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error retrieving wards: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return wards;
    }


    // =========================================================
    // GET ROOMS WITH AVAILABLE BEDS
    // =========================================================

    public List<Room> getAvailableRooms(int wardId) {

        List<Room> rooms = new ArrayList<>();

        String sql = """
                SELECT DISTINCT
                    r.RoomId,
                    r.RoomNumber,
                    r.WardId,
                    r.RoomType,
                    r.Capacity
                FROM Room r
                INNER JOIN Bed b
                    ON r.RoomId = b.RoomId
                WHERE r.WardId = ?
                AND b.IsOccupied = 0
                ORDER BY r.RoomId
                """;

        try (Connection conn =
                     DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, wardId);

            try (ResultSet rs =
                         stmt.executeQuery()) {

                while (rs.next()) {

                    Room room = new Room();

                    room.setId(
                            rs.getInt("RoomId")
                    );

                    room.setRoomNumber(
                            rs.getString("RoomNumber")
                    );

                    room.setRoomType(
                            rs.getString("RoomType")
                    );

                    room.setCapacity(
                            rs.getInt("Capacity")
                    );

                    Ward ward = new Ward();

                    ward.setId(
                            rs.getInt("WardId")
                    );

                    room.setWard(ward);

                    rooms.add(room);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error retrieving rooms: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return rooms;
    }


    // =========================================================
    // GET AVAILABLE BEDS
    // =========================================================

    public List<Bed> getAvailableBeds(int roomId) {

        List<Bed> beds = new ArrayList<>();

        String sql = """
                SELECT
                    BedId,
                    BedNumber,
                    RoomId,
                    IsOccupied
                FROM Bed
                WHERE RoomId = ?
                AND IsOccupied = 0
                ORDER BY BedId
                """;

        try (Connection conn =
                     DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, roomId);

            try (ResultSet rs =
                         stmt.executeQuery()) {

                while (rs.next()) {

                    Bed bed = new Bed();

                    bed.setId(
                            rs.getInt("BedId")
                    );

                    bed.setBedNumber(
                            rs.getString("BedNumber")
                    );

                    bed.setOccupied(
                            rs.getBoolean("IsOccupied")
                    );

                    Room room = new Room();

                    room.setId(
                            rs.getInt("RoomId")
                    );

                    bed.setRoom(room);

                    beds.add(bed);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error retrieving beds: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return beds;
    }


    // =========================================================
    // MAP RESULTSET TO ADMISSION
    // =========================================================

    private Admission mapAdmission(
            ResultSet rs) throws SQLException {

        Admission admission = new Admission();

        admission.setId(
                rs.getInt("AdmissionId")
        );

        // -----------------------------------------------------
        // Patient
        // -----------------------------------------------------

        Patient patient = new Patient();

        patient.setPatientId(
                rs.getInt("PatientId")
        );

        patient.setFirstName(
                rs.getString("PatientFirstName")
        );

        patient.setLastName(
                rs.getString("PatientLastName")
        );

        admission.setPatient(patient);

        // -----------------------------------------------------
        // Ward
        // -----------------------------------------------------

        Ward ward = new Ward();

        ward.setId(
                rs.getInt("WardId")
        );

        ward.setName(
                rs.getString("WardName")
        );

        ward.setWardType(
                rs.getString("WardType")
        );

        // -----------------------------------------------------
        // Room
        // -----------------------------------------------------

        Room room = new Room();

        room.setId(
                rs.getInt("RoomId")
        );

        room.setRoomNumber(
                rs.getString("RoomNumber")
        );

        room.setRoomType(
                rs.getString("RoomType")
        );

        room.setWard(ward);

        // -----------------------------------------------------
        // Bed
        // -----------------------------------------------------

        Bed bed = new Bed();

        bed.setId(
                rs.getInt("BedId")
        );

        bed.setBedNumber(
                rs.getString("BedNumber")
        );

        bed.setOccupied(
                rs.getBoolean("IsOccupied")
        );

        bed.setRoom(room);

        admission.setBed(bed);

        // -----------------------------------------------------
        // Dates
        // -----------------------------------------------------

        Timestamp admissionTimestamp =
                rs.getTimestamp("AdmissionDate");

        if (admissionTimestamp != null) {

            admission.setAdmissionDate(
                    admissionTimestamp.toLocalDateTime()
            );
        }

        Date dischargeSqlDate =
                rs.getDate("DischargeDate");

        if (dischargeSqlDate != null) {

            admission.setDischargeDate(
                    dischargeSqlDate.toLocalDate()
            );
        }

        // -----------------------------------------------------
        // Other information
        // -----------------------------------------------------

        admission.setReason(
                rs.getString("Reason")
        );

        admission.setStatus(
                rs.getString("Status")
        );

        return admission;
    }
}