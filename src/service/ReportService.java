
package service;

import dao.ReportDAO;

public class ReportService {

    private ReportDAO reportDAO = new ReportDAO();

    // 1. Candidate Report
    public void candidateReport() {
        reportDAO.candidateReport();
    }

    // 2. Company Report
    public void companyReport() {
        reportDAO.companyReport();
    }

    // 3. Job Report
    public void jobReport() {
        reportDAO.jobReport();
    }

    // 4. Application Report
    public void applicationReport() {
        reportDAO.applicationReport();
    }

    // 5. Interview Report
    public void interviewReport() {
        reportDAO.interviewReport();
    }

    // 6. Offer Report
    public void offerReport() {
        reportDAO.offerReport();
    }

    // 7. Placement Report
    public void placementReport() {
        reportDAO.placementReport();
    }
}
