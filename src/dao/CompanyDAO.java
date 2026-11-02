
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Company;
import util.DBConnection;

public class CompanyDAO {

    // add a new company
    public boolean addCompany(Company company) {

        String sql = "INSERT INTO companies "
                + "(company_code, company_name, industry, website, location, "
                + "company_type, hr_name, hr_email, hr_mobile, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // generate unique company code
        String companyCode = "COM" +
                UUID.randomUUID().toString()
                .replace("-", "").substring(0, 27);

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, companyCode);
            ps.setString(2, company.getCompanyName());
            ps.setString(3, company.getIndustry());
            ps.setString(4, company.getWebsite());
            ps.setString(5, company.getLocation());
            ps.setString(6, company.getCompanyType());
            ps.setString(7, company.getHrName());
            ps.setString(8, company.getHrEmail());
            ps.setString(9, company.getHrMobile());
            ps.setString(10, "ACTIVE");

            int result = ps.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            System.out.println("Error adding company: " + e.getMessage());
            return false;
        }
    }

    // display all companies
    public List<Company> getAllCompanies() {

        List<Company> companies = new ArrayList<>();

        String sql = "SELECT company_id, company_name, industry, website, "
                + "location, company_type, hr_name, hr_email, hr_mobile, status "
                + "FROM companies";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Company company = new Company();

                company.setCompanyId(rs.getInt("company_id"));
                company.setCompanyName(rs.getString("company_name"));
                company.setIndustry(rs.getString("industry"));
                company.setWebsite(rs.getString("website"));
                company.setLocation(rs.getString("location"));
                company.setCompanyType(rs.getString("company_type"));
                company.setHrName(rs.getString("hr_name"));
                company.setHrEmail(rs.getString("hr_email"));
                company.setHrMobile(rs.getString("hr_mobile"));
                company.setStatus(rs.getString("status"));

                companies.add(company);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching companies: " + e.getMessage());
        }

        return companies;
    }
}
