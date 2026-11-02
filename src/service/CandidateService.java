
package service;

import java.util.List;

import dao.CandidateDAO;
import model.Candidate;

public class CandidateService {

    private CandidateDAO candidateDAO = new CandidateDAO();

    // add candidate
    public boolean addCandidate(Candidate candidate) {

        if (candidate.getName() == null ||
                candidate.getName().trim().isEmpty()) {
            System.out.println("Candidate name is required!");
            return false;
        }

        if (candidate.getEmail() == null ||
                !candidate.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            System.out.println("Enter a valid email!");
            return false;
        }

        if (candidate.getMobile() == null ||
                !candidate.getMobile().matches("[0-9]{10,20}")) {
            System.out.println("Enter a valid mobile number!");
            return false;
        }

        if (candidate.getQualification() == null ||
                candidate.getQualification().trim().isEmpty()) {
            System.out.println("Qualification is required!");
            return false;
        }

        if (candidate.getPassingYear() < 2000 ||
                candidate.getPassingYear() > 2100) {
            System.out.println("Enter a valid passing year!");
            return false;
        }

        if (candidate.getPercentage() < 0 ||
                candidate.getPercentage() > 100) {
            System.out.println("Percentage must be between 0 and 100!");
            return false;
        }

        if (candidate.getBacklogCount() < 0) {
            System.out.println("Backlog count cannot be negative!");
            return false;
        }

        boolean result = candidateDAO.addCandidate(candidate);

        if (result) {
            System.out.println("Candidate added successfully!");
        }

        return result;
    }

    // view all candidates
    public List<Candidate> getAllCandidates() {
        return candidateDAO.getAllCandidates();
    }
}
