package catering.businesslogic.assignment;

import catering.businesslogic.event.Service;
import catering.businesslogic.recipe.Preparation;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.turn.KitchenTurnSerialize;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.recipe.KitchenDuty;
import catering.businesslogic.turn.KitchenTurn;
import catering.businesslogic.user.Cook;
import catering.businesslogic.menu.Menu;
import catering.businesslogic.menu.MenuItem;
import catering.businesslogic.menu.Section;
import catering.persistence.PersistenceManager;

import java.time.Duration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceSummary {
    protected HashMap<KitchenTurn, List<Assignment>> serviceSummary;

    public ServiceSummary() {
        this.serviceSummary = new HashMap<>();
    }

    public static String convertServiceSummaryToJson(HashMap<KitchenTurn, List<Assignment>> serviceSummary) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalTimeTypeAdapter())
                .registerTypeAdapter(KitchenTurn.class, new KitchenTurnSerialize())
                .registerTypeAdapter(Assignment.class, new AssignmentSerialize())
                .create();

        Type type = new TypeToken<HashMap<KitchenTurn, List<Assignment>>>() {}.getType();//TODO: isn't abel to convert because god is dead
        return gson.toJson(serviceSummary, type);
    }

    public static HashMap<KitchenTurn, List<Assignment>> convertJsonToServiceSummary(String json) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalTimeTypeAdapter())
                .registerTypeAdapter(KitchenTurn.class, new JsonSerializer<KitchenTurn>() {
                    @Override
                    public JsonElement serialize(KitchenTurn src, Type typeOfSrc, JsonSerializationContext context) {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("full", src.isFull());
                        jsonObject.addProperty("kitchen", src.getKitchen().getName());
                        jsonObject.addProperty("id", src.getKitchen().getId().toString());
                        return jsonObject;
                    }
                })
                .registerTypeAdapter(Assignment.class, new JsonSerializer<Assignment>() {
                    @Override
                    public JsonElement serialize(Assignment src, Type typeOfSrc, JsonSerializationContext context) {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("description", src.getDescription());
                        jsonObject.addProperty("estimatedTime", src.getEstimatedTime().getSeconds());
                        jsonObject.addProperty("completed", src.getCompleted());
                        jsonObject.addProperty("quantity", src.getQuantity());
                        return jsonObject;
                    }
                })
                .create();

        Type type = new TypeToken<HashMap<KitchenTurn, List<Assignment>>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static String convertKitchenTurnToJson(KitchenTurn kitchenTurn) {
        Gson gson = new Gson();
        Type type = new TypeToken<KitchenTurn>() {}.getType();
        return gson.toJson(kitchenTurn, type);
    }

    public static KitchenTurn convertJsonToKitchenTurn(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<KitchenTurn>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static String convertAssignmentToJson(Assignment assignment) {
        Gson gson = new Gson();
        Type type = new TypeToken<Assignment>() {}.getType();
        return gson.toJson(assignment, type);
    }

    public static Assignment convertJsonToAssignment(String json) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalTimeTypeAdapter())
                .registerTypeAdapter(KitchenTurn.class, new JsonSerializer<KitchenTurn>() {
                    @Override
                    public JsonElement serialize(KitchenTurn src, Type typeOfSrc, JsonSerializationContext context) {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("full", src.isFull());
                        jsonObject.addProperty("kitchen", src.getKitchen().getName());
                        jsonObject.addProperty("id", src.getKitchen().getId().toString());
                        return jsonObject;
                    }
                })
                .registerTypeAdapter(Assignment.class, new JsonSerializer<Assignment>() {
                    @Override
                    public JsonElement serialize(Assignment src, Type typeOfSrc, JsonSerializationContext context) {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("description", src.getDescription());
                        jsonObject.addProperty("estimatedTime", src.getEstimatedTime().getSeconds());
                        jsonObject.addProperty("completed", src.getCompleted());
                        jsonObject.addProperty("quantity", src.getQuantity());
                        return jsonObject;
                    }
                })
                .create();

        Type type = new TypeToken<Assignment>() {}.getType();
        return gson.fromJson(json, type);
    }

    public ServiceSummary open(Service service) {
        ServiceSummary currentService = new ServiceSummary();
        String serviceQuery = "SELECT * FROM Services WHERE id = " + service.getId();

        PersistenceManager.executeQuery(serviceQuery, rs -> currentService.serviceSummary = convertJsonToServiceSummary(rs.getString("service_summary")));
        return currentService;
    }

    public ServiceSummary create(Service service, Menu menu, KitchenTurn kt) {
        ServiceSummary newServiceSummary = new ServiceSummary();
        for (Section section : menu.getSections()) {
            for (MenuItem item : section.getItems()) {
                List<Preparation> preparations = item.getItemRecipe().getPreparations();
                if (preparations.isEmpty()) {
                    Duration preparationTime = Duration.ofMinutes(0);
                    newServiceSummary.addAssignment(item.getItemRecipe(), kt, preparationTime, new ArrayList<>(), 1);//we can use service to use THE EVIL, ideally we shouldn't
                } else {
                    for (Preparation preparation : preparations) {
                        Duration preparationTime = Duration.ofMinutes(0);
                        newServiceSummary.addAssignment(preparation, kt, preparationTime, new ArrayList<>(), 1);
                    }
                }
            }
        }
        String updateQuery = "UPDATE Services SET service_summary = '" + convertServiceSummaryToJson(newServiceSummary.serviceSummary) + "' WHERE id = " + service.getId();
        PersistenceManager.executeUpdate(updateQuery);
        return newServiceSummary;
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
        if (kD instanceof Preparation || (kD instanceof Recipe && ((Recipe) kD).getPreparations().isEmpty())) {
            Assignment.Builder builder = new Assignment.Builder();

            if (kD != null && kD.getDescription() != null) {
                builder.description(kD.getDescription());
            }

            if (eT != null) {
                builder.estimatedTime(eT);
            }

            builder.completed(false); // This is a default value, so no null-check is needed

            if (cooks != null) {
                builder.cooks(cooks);
            }

            builder.quantity(quantity);

            if (kD != null) {
                builder.kitchenDuty(kD);
            }

            if (kitchenTurn != null) {
                builder.kitchenTurn(kitchenTurn);
            }

            if (serviceSummary.containsKey(kitchenTurn)) {
                serviceSummary.get(kitchenTurn).add(builder.build());
            } else {
                List<Assignment> assignments = new ArrayList<>();
                assignments.add(builder.build());
                serviceSummary.put(kitchenTurn, assignments);
            }
        }
        if (kD instanceof Recipe) {
            if (!((Recipe) kD).getPreparations().isEmpty()) {
                for (Preparation preparation : ((Recipe) kD).getPreparations()) {
                    addAssignment(preparation, kitchenTurn, eT, cooks, quantity);
                }
            }
        }

    }

    private void saveServiceSummary(KitchenTurn kitchenTurn) {
        //Persistence ServiceSummary
        String json = convertServiceSummaryToJson(this.serviceSummary);
        String updateQuery = "UPDATE Services SET service_summary = '" + json + "' WHERE kitchen_turn = " + convertKitchenTurnToJson(kitchenTurn);
        PersistenceManager.executeUpdate(updateQuery);
    }

    public ServiceSummary deleteAssignment(Assignment assignment, KitchenTurn kitchenTurn) {
        this.serviceSummary.get(kitchenTurn).remove(assignment);
        saveServiceSummary(kitchenTurn);
        return this;
    }

    /**
     * This method is used to modify the details of an Assignment.
     * It gets the List of Assignments for a given Turn and modifies the details of the specific Assignment instance.
     *
     * @param assignment The Assignment instance to be modified.
     * @param turn The Turn instance representing the new turn for the assignment. If null, the turn is not updated.
     * @param cooks The list of Cook instances representing the new cooks for the assignment. If null, the cooks are not updated.
     * @param description The new description for the assignment. If null, the description is not updated.
     * @param completed The new completion status for the assignment.
     * @param quantity The new quantity for the assignment.
     * @param estimatedTime The new estimated time duration for the assignment.
     * @throws IllegalArgumentException If the passed list of assignments does not contain the same assignments as the serviceSummary map.
     */
    public void modifyAssignment(Assignment assignment, KitchenTurn turn, List<Cook> cooks, String description, boolean completed, int quantity, Duration estimatedTime) throws IllegalArgumentException {
        List<Assignment> assignments = this.serviceSummary.get(turn);
        int index = assignments.indexOf(assignment);

        if (index == -1) {
            throw new IllegalArgumentException("The specified assignment is not in the list related to the turn.");
        }

        Assignment modifiedAssignment = new Assignment.Builder()
                .description(description)
                .estimatedTime(estimatedTime)
                .completed(completed)
                .cooks(cooks)
                .quantity(quantity)
                .kitchenDuty(assignment.getKitchenDuty())
                .kitchenTurn(assignment.getKitchenTurn())
                .build();

        assignments.set(index, modifiedAssignment);

        // Persistence ServiceSummary
        saveServiceSummary(turn);
    }

    /**
     * This method is used to order the assignments based on user supplied preference.
     * First it checks that the passed list of assignments contains exactly the same assignments as the serviceSummary map.
     * Then it orders the assignments based on the user supplied preference.
     *
     * @param assignments The list of Assignment instances to be ordered.
     * @throws IllegalArgumentException If the passed list of assignments does not contain the same assignments as the serviceSummary map.
     */
    public ServiceSummary orderAssignments(List<Assignment> assignments, KitchenTurn turn) throws IllegalArgumentException{
        // Check if the passed list of assignments contains exactly the same assignments as the serviceSummary map
        for (List<Assignment> assignmentList : serviceSummary.values()) {
            for (Assignment assignment : assignmentList) {
                if (!assignments.contains(assignment)) {
                    throw new IllegalArgumentException("The passed list of assignments does not contain the same assignments as the serviceSummary map.");
                }
            }
        }

        this.serviceSummary.put(turn, assignments);

        // Persistence ServiceSummary
        saveServiceSummary(turn);
        return this;
    }

    /**
     * This method is used to show the state of the assignments for a given turn.
     *
     * @param turn The Turn instance for which the assignments state will be shown.
     * @return A list of Assignment instances representing the assignments for the given turn.
     */
    public List<Assignment> showAssignmentsState(Turn turn) {
        return serviceSummary.getOrDefault(turn, new ArrayList<>());
    }

    // Getters and Setters
    public HashMap<KitchenTurn, List<Assignment>> getServiceSummary() {
        return serviceSummary;
    }
    public void setServiceSummary(HashMap<KitchenTurn, List<Assignment>> serviceSummary) {
        this.serviceSummary = serviceSummary;
    }
}

