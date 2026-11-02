package service;

import java.util.List;

import dao.OfferDAO;
import model.Offer;

public class OfferService {

    private OfferDAO offerDAO = new OfferDAO();

    // add a new offer
    public boolean addOffer(Offer offer) {

        if (offer.getApplicationId() <= 0) {
            System.out.println("Invalid application ID!");
            return false;
        }

        if (offer.getDesignation() == null ||
                offer.getDesignation().trim().isEmpty()) {
            System.out.println("Designation cannot be empty!");
            return false;
        }

        if (offer.getPackageAmount() <= 0) {
            System.out.println("Package must be greater than zero!");
            return false;
        }

        if (offer.getOfferDate() == null ||
                offer.getOfferDate().trim().isEmpty()) {
            System.out.println("Offer date cannot be empty!");
            return false;
        }

        if (offer.getJoiningDate() == null ||
                offer.getJoiningDate().trim().isEmpty()) {
            System.out.println("Joining date cannot be empty!");
            return false;
        }

        if (offer.getOfferStatus() == null ||
                offer.getOfferStatus().trim().isEmpty()) {
            System.out.println("Offer status cannot be empty!");
            return false;
        }

        return offerDAO.addOffer(offer);
    }

    // display all offers
    public List<Offer> getAllOffers() {
        return offerDAO.getAllOffers();
    }
}