
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Candidate;
import util.DBConnection;

public class CandidateDAO {

    // add a new candidate
    public boolean addCandidate(Candidate candidate) {

        String sql = "INSERT INTO candidates "
                + "(candidate_code, full_name, email, mobile, "
                + "qualification, passing_year, percentage, backlog_count) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // generate unique candidate code
        String candidateCode = "CAN" +
                UUID.randomUUID().toString()
                .replace("-", "").substring(0, 27);

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, candidateCode);
            ps.setString(2, candidate.getName());
            ps.setString(3, candidate.getEmail());
            ps.setString(4, candidate.getMobile());
            ps.setString(5, candidate.getQualification());
            ps.setInt(6, candidate.getPassingYear());
            ps.setDouble(7, candidate.getPercentage());
            ps.setInt(8, candidate.getBacklogCount());

            int result = ps.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            System.out.println("Error adding candidate: " + e.getMessage());
            return false;
        }
    }

    // display all candidates
    public List<Candidate> getAllCandidates() {

        List<Candidate> candidates = new ArrayList<>();

        String sql = "SELECT candidate_id, candidate_code, full_name, "
                + "email, mobile, qualification, passing_year, "
                + "percentage, backlog_count FROM candidates";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Candidate candidate = new Candidate();

                candidate.setCandidateId(rs.getInt("candidate_id"));
                candidate.setCandidateCode(rs.getString("candidate_code"));
                candidate.setName(rs.getString("full_name"));
                candidate.setEmail(rs.getString("email"));
                candidate.setMobile(rs.getString("mobile"));
                candidate.setQualification(rs.getString("qualification"));
                candidate.setPassingYear(rs.getInt("passing_year"));
                candidate.setPercentage(rs.getDouble("percentage"));
                candidate.setBacklogCount(rs.getInt("backlog_count"));

                candidates.add(candidate);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching candidates: " + e.getMessage());
        }

        return candidates;
    }
}
