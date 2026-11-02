
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.Interview;
import util.DBConnection;

public class InterviewDAO {

    // add a new interview round
    public boolean addInterview(Interview interview) {

        String sql = "INSERT INTO interview_rounds "
                + "(application_id, round_number, round_type, "
                + "scheduled_date, scheduled_time, mode, "
                + "interviewer_name, meeting_or_location, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, interview.getApplicationId());
            ps.setInt(2, interview.getRoundNumber());
            ps.setString(3, interview.getRoundType());
            ps.setDate(4, Date.valueOf(interview.getInterviewDate()));
            ps.setTime(5, Time.valueOf(interview.getInterviewTime()));
            ps.setString(6, interview.getMode());
            ps.setString(7, interview.getInterviewer());
            ps.setString(8, interview.getMeetingLocation());
            ps.setString(9, interview.getStatus());

            int result = ps.executeUpdate();

            return result > 0;

        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("Error adding interview: " + e.getMessage());
            return false;
        }
    }

    // display all interview rounds
    public List<Interview> getAllInterviews() {

        List<Interview> interviews = new ArrayList<>();

        String sql = "SELECT * FROM interview_rounds";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Interview interview = new Interview();

                interview.setInterviewId(rs.getInt("interview_id"));
                interview.setApplicationId(rs.getInt("application_id"));
                interview.setRoundNumber(rs.getInt("round_number"));
                interview.setRoundType(rs.getString("round_type"));

                interview.setInterviewDate(
                        rs.getDate("scheduled_date").toString());

                interview.setInterviewTime(
                        rs.getTime("scheduled_time").toString());

                interview.setMode(rs.getString("mode"));
                interview.setInterviewer(rs.getString("interviewer_name"));
                interview.setMeetingLocation(
                        rs.getString("meeting_or_location"));
                interview.setStatus(rs.getString("status"));

                interviews.add(interview);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching interviews: " + e.getMessage());
        }

        return interviews;
    }
}
