package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Offer;
import util.DBConnection;

public class OfferDAO {

    // add a new offer
    public boolean addOffer(Offer offer) {

        String sql = "INSERT INTO offers "
                + "(offer_code, application_id, designation, offered_package_lpa, "
                + "offer_date, expected_joining_date, offer_status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String offerCode = "OFF" + UUID.randomUUID()
                    .toString().replace("-", "").substring(0, 27);

            ps.setString(1, offerCode);
            ps.setInt(2, offer.getApplicationId());
            ps.setString(3, offer.getDesignation());
            ps.setDouble(4, offer.getPackageAmount());
            ps.setDate(5, Date.valueOf(offer.getOfferDate()));
            ps.setDate(6, Date.valueOf(offer.getJoiningDate()));
            ps.setString(7, offer.getOfferStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("Error adding offer: " + e.getMessage());
            return false;
        }
    }

    // display all offers
    public List<Offer> getAllOffers() {

        List<Offer> offers = new ArrayList<>();

        String sql = "SELECT * FROM offers";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Offer offer = new Offer();

                offer.setOfferId(rs.getInt("offer_id"));
                offer.setApplicationId(rs.getInt("application_id"));
                offer.setDesignation(rs.getString("designation"));
                offer.setPackageAmount(rs.getDouble("offered_package_lpa"));
                offer.setOfferDate(rs.getDate("offer_date").toString());
                offer.setJoiningDate(
                        rs.getDate("expected_joining_date").toString()
                );
                offer.setOfferStatus(rs.getString("offer_status"));

                offers.add(offer);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching offers: " + e.getMessage());
        }

        return offers;
    }
}