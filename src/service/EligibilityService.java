package service;

import dao.EligibilityDAO;

public class EligibilityService {

    private EligibilityDAO eligibilityDAO = new EligibilityDAO();

    // Used by the existing console application
    public boolean checkEligibility(int applicationId) {

        if (applicationId <= 0) {
            System.out.println("Invalid Application ID!");
            return false;
        }

        String result = eligibilityDAO.checkEligibility(applicationId);

        return result.startsWith("ELIGIBLE|")
                || result.startsWith("NOT_ELIGIBLE|");
    }

    // Used by the website
    public String checkEligibilityResult(int applicationId) {

        if (applicationId <= 0) {
            return "ERROR|Invalid Application ID!";
        }

        return eligibilityDAO.checkEligibility(applicationId);
    }
}