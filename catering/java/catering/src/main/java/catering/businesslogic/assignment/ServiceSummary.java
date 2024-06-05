package catering.businesslogic.assignment;

import catering.businesslogic.event.Service;
import catering.businesslogic.recipe.Preparation;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.recipe.KitchenDuty;
import catering.businesslogic.turn.KitchenTurn;
import catering.businesslogic.user.Cook;
import catering.businesslogic.menu.Menu;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceSummary {
    private HashMap<Turn, List<Assignment>> serviceSummary;

    public ServiceSummary() {
        this.serviceSummary = new HashMap<>();
    }

    public void create(Menu menu) {

    }

    public void open(Service service) {
        Service currentService = new Service();
        String serviceQuery = "SELECT * FROM Services WHERE id = ?";

        PersistenceManager.executeQuery(serviceQuery, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                currentService.setId(rs.getInt("id"));
                currentService.setEventId(rs.getInt("eventId"));
                currentService.setName(rs.getString("name"));
                currentService.setPlace(rs.getString("place"));
                currentService.setApprovedMenuId(rs.getInt("approvedMenuId"));
                PersistenceManager.executeQuery("SELECT * FROM Menus WHERE id = " + currentService.getApprovedMenuId(), new ResultHandler() {
                    @Override
                    public void handle(ResultSet rs) throws SQLException {
                        // Logic to set menu    
                    }
                });
                currentService.setServiceDate(Date.valueOf(rs.getDate("serviceDate").toLocalDate()));
                currentService.setTimeStart(Time.valueOf(rs.getTime("timeStart").toLocalTime()));
                currentService.setTimeEnd(Time.valueOf(rs.getTime("timeEnd").toLocalTime()));
                currentService.setExpectedParticipants(rs.getInt("expectedParticipants"));
            }
        });
    }

    public List<Assignment> showAssignmentsState(Turn turn) {
        return serviceSummary.getOrDefault(turn, new ArrayList<>());
    }

    /**
     * This method is used to add a new Assignment to the serviceSummary map.
     * The Assignment is created based on the type of KitchenDuty provided.
     * If the KitchenDuty is a Recipe, the method will create an Assignment for each Preparation in the Recipe.
     *
     * @param kD The KitchenDuty instance, which can be either a Preparation or a Recipe.
     * @param kitchenTurn The KitchenTurn instance representing the kitchen turn for the assignment.
     * @param eT The estimated time duration for the assignment.
     * @param cooks The list of Cook instances assigned to the assignment.
     * @param quantity The quantity for the assignment.
     */
    public void addAssignment(KitchenDuty kD, KitchenTurn kitchenTurn, Duration eT, List<Cook> cooks, int quantity) {
        //TODO persistence
        if (kD instanceof Preparation) {
            this.serviceSummary.get(kitchenTurn).add(new Assignment((Preparation) kD, kitchenTurn, eT, cooks, kD.getDescription(), false, quantity));
        }
        if (kD instanceof Recipe) {
            if (!((Recipe) kD).getPreparations().isEmpty()) {
                for (Preparation preparation : ((Recipe) kD).getPreparations()) {
                    addAssignment(preparation, kitchenTurn, eT, cooks, quantity);
                }
            } else {
                this.serviceSummary.get(kitchenTurn).add(new Assignment((Recipe) kD, kitchenTurn, eT, cooks, kD.getDescription(), false, quantity));
            }
        }
    }

    public void deleteAssignment(Assignment assignment, KitchenTurn kitchenTurn) {
        this.serviceSummary.get(kitchenTurn).remove(assignment);
        //TODO persistence
    }

    public void assignAssignment(Assignment assignment, List<Cook> cooks, Turn turn) {
        // Logic to assign assignment
    }

    public void modifyAssignment(Assignment assignment, Turn turn, List<Cook> cooks, String description, boolean completed, int quantity, Duration estimatedTime) {
        // Logic to modify assignment
    }

    public void orderAssignments(List<Assignment> assignments) {
        // Logic to order assignments
    }

    /**
     * This method is used to show the state of the assignments for a given turn.
     *
     * @param turn The Turn instance for which the assignments state will be shown.
     * @return A list of Assignment instances representing the assignments for the given turn.
     */
    public List<Assignment> showAssigmentState(Turn turn) {
        return serviceSummary.get(turn);
    }

    // Getters and Setters
    public HashMap<Turn, List<Assignment>> getServiceSummary() {
        return serviceSummary;
    }
    public void setServiceSummary(HashMap<Turn, List<Assignment>> serviceSummary) {
        this.serviceSummary = serviceSummary;
    }
}

