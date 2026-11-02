
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Placement;
import util.DBConnection;

public class PlacementDAO {

    // Add a new placement
    public boolean addPlacement(Placement placement) {

        String sql = "INSERT INTO placements "
                + "(candidate_id, company_id, job_id, offer_id, designation, "
                + "final_package_lpa, expected_joining_date, actual_joining_date, "
                + "joining_status, placement_status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, placement.getCandidateId());
            ps.setInt(2, placement.getCompanyId());
            ps.setInt(3, placement.getJobId());
            ps.setInt(4, placement.getOfferId());

            ps.setString(5, placement.getDesignation());
            ps.setDouble(6, placement.getFinalPackage());

            // Expected joining date
            ps.setDate(7, Date.valueOf(
                    placement.getExpectedJoiningDate().trim()));

            // Actual joining date is optional
            if (placement.getActualJoiningDate() == null
                    || placement.getActualJoiningDate().trim().isEmpty()) {

                ps.setNull(8, Types.DATE);

            } else {

                ps.setDate(8, Date.valueOf(
                        placement.getActualJoiningDate().trim()));
            }

            ps.setString(9, placement.getJoiningStatus());
            ps.setString(10, placement.getPlacementStatus());

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Placement added successfully!");
                return true;
            }

            return false;

        } catch (SQLException e) {

            System.out.println("Database error while adding placement:");
            System.out.println("Error code: " + e.getErrorCode());
            System.out.println("SQL state: " + e.getSQLState());
            System.out.println("Error message: " + e.getMessage());

            e.printStackTrace();

            return false;

        } catch (IllegalArgumentException e) {

            System.out.println("Invalid placement date:");
            System.out.println(e.getMessage());

            e.printStackTrace();

            return false;

        } catch (Exception e) {

            System.out.println("Unexpected error while adding placement:");
            System.out.println(e.getMessage());

            e.printStackTrace();

            return false;
        }
    }

    // Get all placements
    public List<Placement> getAllPlacements() {

        List<Placement> placements = new ArrayList<>();

        String sql = "SELECT * FROM placements";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Placement placement = new Placement();

                placement.setPlacementId(
                        rs.getInt("placement_id"));

                placement.setCandidateId(
                        rs.getInt("candidate_id"));

                placement.setCompanyId(
                        rs.getInt("company_id"));

                placement.setJobId(
                        rs.getInt("job_id"));

                placement.setOfferId(
                        rs.getInt("offer_id"));

                placement.setDesignation(
                        rs.getString("designation"));

                placement.setFinalPackage(
                        rs.getDouble("final_package_lpa"));

                Date expectedDate =
                        rs.getDate("expected_joining_date");

                Date actualDate =
                        rs.getDate("actual_joining_date");

                placement.setExpectedJoiningDate(
                        expectedDate == null
                                ? null
                                : expectedDate.toString());

                placement.setActualJoiningDate(
                        actualDate == null
                                ? null
                                : actualDate.toString());

                placement.setJoiningStatus(
                        rs.getString("joining_status"));

                placement.setPlacementStatus(
                        rs.getString("placement_status"));

                placements.add(placement);
            }

        } catch (SQLException e) {

            System.out.println("Error fetching placements:");
            System.out.println(e.getMessage());

            e.printStackTrace();
        }

        return placements;
    }
}
