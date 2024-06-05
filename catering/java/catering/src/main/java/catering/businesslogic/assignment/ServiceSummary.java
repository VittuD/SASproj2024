package catering.businesslogic.assignment;

import catering.businesslogic.event.Service;
import catering.businesslogic.recipe.Preparation;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.recipe.KitchenDuty;
import catering.businesslogic.turn.KitchenTurn;
import catering.businesslogic.user.Cook;
import catering.businesslogic.menu.Menu;
import catering.businesslogic.menu.MenuItem;
import catering.businesslogic.menu.Section;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceSummary {
    protected HashMap<Turn, List<Assignment>> serviceSummary;

    public ServiceSummary() {
        this.serviceSummary = new HashMap<>();
    }

    public static String convertServiceSummaryToJson(HashMap<Turn, List<Assignment>> serviceSummary) {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<Turn, List<Assignment>>>() {}.getType();
        return gson.toJson(serviceSummary, type);
    }

    public static HashMap<Turn, List<Assignment>> convertJsonToServiceSummary(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<Turn, List<Assignment>>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public ServiceSummary open(Service service) {
        ServiceSummary currentService = new ServiceSummary();
        String serviceQuery = "SELECT * FROM Services WHERE id = " + service.getId();

        PersistenceManager.executeQuery(serviceQuery, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                currentService.serviceSummary = convertJsonToServiceSummary(rs.getString("service_summary"));
            }
        });
        return currentService;
    }

    public ServiceSummary create(Menu menu) {
        ServiceSummary newServiceSummary = new ServiceSummary();
        for (Section section : menu.getSections()) {
            for (MenuItem item : section.getItems()) {               ;
                for (Preparation preparation : item.getItemRecipe().getPreparations()) {
                    Duration preparationTime = Duration.ofMinutes(0);
                    newServiceSummary.addAssignment(preparation, new KitchenTurn(), preparationTime, new ArrayList<>(), 1);
                }
            }
        }
        return newServiceSummary;
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

    /**
     * This method is used to order the assignments based on user supplied preference.
     * First it checks that the passed list of assignments contains exactly the same assignments as the serviceSummary map.
     * Then it orders the assignments based on the user supplied preference.
     *
     * @param assignments The list of Assignment instances to be ordered.
     * @throws IllegalArgumentException If the passed list of assignments does not contain the same assignments as the serviceSummary map.
     */
    public void orderAssignments(List<Assignment> assignments, KitchenTurn turn) {
        // Check if the passed list of assignments contains exactly the same assignments as the serviceSummary map
        for (List<Assignment> assignmentList : serviceSummary.values()) {
            for (Assignment assignment : assignmentList) {
                if (!assignments.contains(assignment)) {
                    throw new IllegalArgumentException("The passed list of assignments does not contain the same assignments as the serviceSummary map.");
                }
            }
        }

        this.serviceSummary.put(turn, assignments);
        //TODO persistence
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

