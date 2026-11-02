
package service;

import java.util.List;

import dao.CompanyDAO;
import model.Company;

public class CompanyService {

    private CompanyDAO companyDAO = new CompanyDAO();

    // add company
    public boolean addCompany(Company company) {

        if (company.getCompanyName() == null ||
                company.getCompanyName().trim().isEmpty()) {

            System.out.println("Company name is required!");
            return false;
        }

        if (company.getHrEmail() != null &&
                !company.getHrEmail().trim().isEmpty() &&
                !company.getHrEmail().matches(
                        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            System.out.println("Enter a valid HR email!");
            return false;
        }

        if (company.getHrMobile() != null &&
                !company.getHrMobile().trim().isEmpty() &&
                !company.getHrMobile().matches("[0-9]{10,20}")) {

            System.out.println("Enter a valid HR mobile number!");
            return false;
        }

        boolean result = companyDAO.addCompany(company);

        if (result) {
            System.out.println("Company added successfully!");
        }

        return result;
    }

    // view all companies
    public List<Company> getAllCompanies() {

        return companyDAO.getAllCompanies();
    }
}
