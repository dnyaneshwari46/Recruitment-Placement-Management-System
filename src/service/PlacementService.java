
package service;

import java.util.List;

import dao.PlacementDAO;
import model.Placement;

public class PlacementService {

    private PlacementDAO placementDAO = new PlacementDAO();

    public boolean addPlacement(Placement placement) {

        if (placement.getCandidateId() <= 0 ||
                placement.getCompanyId() <= 0 ||
                placement.getJobId() <= 0 ||
                placement.getOfferId() <= 0) {

            System.out.println("Invalid placement details!");
            return false;
        }

        if (placement.getFinalPackage() <= 0) {
            System.out.println("Invalid package amount!");
            return false;
        }

        if (placement.getExpectedJoiningDate() == null ||
                placement.getExpectedJoiningDate().trim().isEmpty()) {

            System.out.println("Expected joining date is required!");
            return false;
        }

        return placementDAO.addPlacement(placement);
    }

    public List<Placement> getAllPlacements() {
        return placementDAO.getAllPlacements();
    }
}
