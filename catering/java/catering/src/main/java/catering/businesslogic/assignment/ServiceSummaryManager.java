package catering.businesslogic.assignment;

import catering.businesslogic.event.Service;
import catering.businesslogic.menu.Menu;
import catering.businesslogic.recipe.KitchenDuty;
import catering.businesslogic.turn.KitchenTurn;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.user.Cook;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
//TODO a lot of shit is missing here
public class ServiceSummaryManager {
    private ServiceSummary managedServiceSummary;
    private List<ServiceSummaryEventReceiver> receivers;

    public ServiceSummaryManager(ServiceSummary managedServiceSummary) {
        this.receivers = new ArrayList<>();
        this.managedServiceSummary = managedServiceSummary;
    }

    public void addReceiver(ServiceSummaryEventReceiver receiver) {
        receivers.add(receiver);
    }

    public void removeReceiver(ServiceSummaryEventReceiver receiver) {
        receivers.remove(receiver);
    }

    private void notifyCreateServiceSummary(ServiceSummary serviceSummary) {
        for (ServiceSummaryEventReceiver receiver : receivers) {
            receiver.updateCreateServiceSummary(serviceSummary);
        }
    }

    private void notifyOpenServiceSummary(ServiceSummary serviceSummary) {
        for (ServiceSummaryEventReceiver receiver : receivers) {
            receiver.updateOpenServiceSummary(serviceSummary);
        }
    }

    private void notifyAddAssignment(Assignment assignment) {
        for (ServiceSummaryEventReceiver receiver : receivers) {
            receiver.updateAddAssignment(assignment);
        }
    }

    private void notifyAssignAssignment(List<Assignment> assignments, Cook cook, Turn turn) {
        for (ServiceSummaryEventReceiver receiver : receivers) {
            receiver.updateAssignAssignment(assignments, cook, turn);
        }
    }

    private void notifyModifyAssignment(Assignment assignment, Turn turn, Cook cook, String description, boolean completed) {
        for (ServiceSummaryEventReceiver receiver : receivers) {
            receiver.updateModifyAssignment(assignment, turn, cook);
        }
    }

    private void notifyOrderAssignment(ArrayList<Assignment> assignments) {
        for (ServiceSummaryEventReceiver receiver : receivers) {
            receiver.updateOrderAssignment(assignments);
        }
    }

    private void notifyShowAssignmentState(List<Assignment> assignments) {
        for (ServiceSummaryEventReceiver receiver : receivers) {
            receiver.updateShowAssignmentState(assignments);
        }
    }

    private void notifyDeleteAssignment(Assignment assignment) {
        for (ServiceSummaryEventReceiver receiver : receivers) {
            receiver.updateDeleteAssignment(assignment);
        }
    }

    // Methods to create, open, show assignments state, add, delete, assign, modify, and order assignments
    public void create(Service service) {
        Menu relatedMenu = getMenuFromService(service);
        managedServiceSummary = managedServiceSummary.create(service, relatedMenu);
        notifyCreateServiceSummary(managedServiceSummary);
    }

    /**
     * This method is used to get the menu from the service.
     * The menu is stored in the service with the key 'approved_menu_id'.
     * The menu has a key 'id'.
     *
     * @param service The service instance.
     * @return The menu instance.
     */
    public Menu getMenuFromService(Service service) {
        // Get menu from persistence: Service has a key 'approved_menu_id' and menu has a key 'id'
        String query = "SELECT * FROM Menus WHERE id = " + service.getApprovedMenuId();
        final Menu[] menu = new Menu[1];
        // Handle the result
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int user_id = rs.getInt("owner_id");
                User user = User.loadUserById(user_id);
                String[] features = new String[0];
                menu[0] = new Menu(user, rs.getString("title"), features, rs.getBoolean("published"));
            }
        });
        return menu[0];
    }

    public void openServiceSummary(Service service) {
        managedServiceSummary = managedServiceSummary.open(service);
        notifyOpenServiceSummary(managedServiceSummary);
    }

    public ArrayList<Assignment> showAssignmentsState(Turn turn) {
        // Logic to get assignments state
        return new ArrayList<>();
    }

    public void addAssignment(KitchenDuty kD, KitchenTurn kitchenTurn, Duration eT, List<Cook> cooks) {
        // Logic to add assignment
        notifyAddAssignment(new Assignment.Builder().
                description(kD.getDescription()).
                estimatedTime(eT).
                completed(false).
                cooks(cooks).
                quantity(1).
                kitchenDuty(kD).
                kitchenTurn(kitchenTurn).
                build());
    }

    public void deleteAssignment(Assignment assignment, KitchenTurn kitchenTurn) {
        managedServiceSummary.deleteAssignment(assignment, kitchenTurn);
        notifyDeleteAssignment(assignment);
    }

    public void assignAssignment(Assignment assignment, List<Cook> cooks, Turn turn) {
        // Logic to assign assignment
        notifyAssignAssignment(List.of(assignment), null, turn);
    }

    public void modifyAssignment(Assignment assignment, Turn turn, List<Cook> cooks, String description, boolean completed, int quantity, Duration estimatedTime) {
        // Logic to modify assignment
        notifyModifyAssignment(assignment, turn, null, description, completed);
    }

    public void orderAssignments(ArrayList<Assignment> assignments) {
        // Logic to order assignments
        notifyOrderAssignment(assignments);
    }
}
