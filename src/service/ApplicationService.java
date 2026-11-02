
package service;

import java.util.List;

import dao.ApplicationDAO;
import model.Application;

public class ApplicationService {

    private ApplicationDAO applicationDAO = new ApplicationDAO();

    // add application
    public boolean addApplication(Application application) {

        if (application.getCandidateId() <= 0) {
            System.out.println("Enter a valid candidate ID!");
            return false;
        }

        if (application.getJobId() <= 0) {
            System.out.println("Enter a valid job ID!");
            return false;
        }

        boolean result = applicationDAO.addApplication(application);

        if (result) {
            System.out.println("Application added successfully!");
        }

        return result;
    }

    // view all applications
    public List<Application> getAllApplications() {

        return applicationDAO.getAllApplications();
    }
}
