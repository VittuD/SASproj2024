package catering.businesslogic.assignment;

import catering.businesslogic.turn.Turn;
import catering.businesslogic.recipe.KitchenDuty;
import catering.businesslogic.turn.KitchenTurn;
import catering.businesslogic.user.Cook;
import catering.businesslogic.menu.Menu;

import java.time.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServiceSummary {
    private HashMap<Turn, ArrayList<Assignment>> serviceSummary;

    public ServiceSummary() {
        this.serviceSummary = new HashMap<>();
    }

    public void create(Menu menu) {
        // Logic to create service summary from menu
    }

    public List<Assignment> showAssignmentsState(Turn turn) {
        return serviceSummary.getOrDefault(turn, new ArrayList<>());
    }

    public void addAssignment(KitchenDuty kD, KitchenTurn kitchenTurn, Duration eT, List<Cook> cooks) {
        // Logic to add assignment
    }

    public void deleteAssignment(Assignment assignment, KitchenTurn kitchenTurn) {
        // Logic to delete assignment
    }

    public void assignAssignment(Assignment assignment, List<Cook> cooks, Turn turn) {
        // Logic to assign assignment
    }

    public void modifyAssignment(Assignment assignment, Turn turn, List<Cook> cooks, String description, boolean completed, int quantity, Duration estimatedTime) {
        // Logic to modify assignment
    }

    public void orderAssignments(ArrayList<Assignment> assignments) {
        // Logic to order assignments
    }
}

