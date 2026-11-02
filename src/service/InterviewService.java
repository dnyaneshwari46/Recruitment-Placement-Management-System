
package service;

import java.util.List;

import dao.InterviewDAO;
import model.Interview;

public class InterviewService {

    private InterviewDAO interviewDAO = new InterviewDAO();

    // add interview
    public boolean addInterview(Interview interview) {

        if (interview.getApplicationId() <= 0) {
            System.out.println("Invalid application ID!");
            return false;
        }

        if (interview.getRoundNumber() <= 0) {
            System.out.println("Invalid interview round!");
            return false;
        }

        if (interview.getRoundType() == null ||
                interview.getRoundType().trim().isEmpty()) {
            System.out.println("Interview round type is required!");
            return false;
        }

        if (interview.getInterviewDate() == null ||
                interview.getInterviewDate().trim().isEmpty()) {
            System.out.println("Interview date is required!");
            return false;
        }

        if (interview.getInterviewTime() == null ||
                interview.getInterviewTime().trim().isEmpty()) {
            System.out.println("Interview time is required!");
            return false;
        }

        if (interview.getMode() == null ||
                interview.getMode().trim().isEmpty()) {
            System.out.println("Interview mode is required!");
            return false;
        }

        if (interview.getStatus() == null ||
                interview.getStatus().trim().isEmpty()) {
            System.out.println("Interview status is required!");
            return false;
        }

        boolean result = interviewDAO.addInterview(interview);

        if (result) {
            System.out.println("Interview added successfully!");
        }

        return result;
    }

    // view all interviews
    public List<Interview> getAllInterviews() {

        return interviewDAO.getAllInterviews();
    }
}
