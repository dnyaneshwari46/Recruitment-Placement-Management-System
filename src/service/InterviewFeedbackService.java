
package service;

import java.util.List;

import dao.InterviewFeedbackDAO;
import model.InterviewFeedback;

public class InterviewFeedbackService {

    private InterviewFeedbackDAO feedbackDAO = new InterviewFeedbackDAO();

    // add interview feedback
    public boolean addFeedback(InterviewFeedback feedback) {

        if (feedback.getInterviewId() <= 0) {
            System.out.println("Invalid interview ID!");
            return false;
        }

        if (feedback.getSubmittedBy() <= 0) {
            System.out.println("Invalid submitted by user ID!");
            return false;
        }

        if (feedback.getTechnicalRating() < 0 ||
            feedback.getTechnicalRating() > 5) {
            System.out.println("Technical rating must be between 0 and 5!");
            return false;
        }

        if (feedback.getCommunicationRating() < 0 ||
            feedback.getCommunicationRating() > 5) {
            System.out.println("Communication rating must be between 0 and 5!");
            return false;
        }

        if (feedback.getProblemSolvingRating() < 0 ||
            feedback.getProblemSolvingRating() > 5) {
            System.out.println("Problem solving rating must be between 0 and 5!");
            return false;
        }

        if (feedback.getOverallRating() < 0 ||
            feedback.getOverallRating() > 5) {
            System.out.println("Overall rating must be between 0 and 5!");
            return false;
        }

        if (feedback.getRecommendation() == null ||
            feedback.getRecommendation().trim().isEmpty()) {
            System.out.println("Recommendation is required!");
            return false;
        }

        return feedbackDAO.addFeedback(feedback);
    }

    // view all interview feedback
    public List<InterviewFeedback> getAllFeedback() {
        return feedbackDAO.getAllFeedback();
    }
}
