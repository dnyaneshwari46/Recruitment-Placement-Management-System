
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.InterviewFeedback;
import util.DBConnection;

public class InterviewFeedbackDAO {

    // add interview feedback
    public boolean addFeedback(InterviewFeedback feedback) {

        String sql = "INSERT INTO interview_feedback "
                + "(interview_id, technical_rating, communication_rating, "
                + "problem_solving_rating, overall_rating, recommendation, "
                + "remarks, submitted_by) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, feedback.getInterviewId());
            ps.setDouble(2, feedback.getTechnicalRating());
            ps.setDouble(3, feedback.getCommunicationRating());
            ps.setDouble(4, feedback.getProblemSolvingRating());
            ps.setDouble(5, feedback.getOverallRating());
            ps.setString(6, feedback.getRecommendation());
            ps.setString(7, feedback.getRemarks());
            ps.setInt(8, feedback.getSubmittedBy());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding feedback: " + e.getMessage());
            return false;
        }
    }

    // display all interview feedback
    public List<InterviewFeedback> getAllFeedback() {

        List<InterviewFeedback> feedbackList = new ArrayList<>();

        String sql = "SELECT * FROM interview_feedback";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                InterviewFeedback feedback = new InterviewFeedback();

                feedback.setFeedbackId(rs.getInt("feedback_id"));
                feedback.setInterviewId(rs.getInt("interview_id"));
                feedback.setTechnicalRating(rs.getDouble("technical_rating"));
                feedback.setCommunicationRating(
                        rs.getDouble("communication_rating"));
                feedback.setProblemSolvingRating(
                        rs.getDouble("problem_solving_rating"));
                feedback.setOverallRating(rs.getDouble("overall_rating"));
                feedback.setRecommendation(rs.getString("recommendation"));
                feedback.setRemarks(rs.getString("remarks"));
                feedback.setSubmittedBy(rs.getInt("submitted_by"));

                feedbackList.add(feedback);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching feedback: " + e.getMessage());
        }

        return feedbackList;
    }
}
