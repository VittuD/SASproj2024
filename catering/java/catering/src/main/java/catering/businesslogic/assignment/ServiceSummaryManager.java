package catering.businesslogic.assignment;

import catering.businesslogic.event.Service;
import catering.businesslogic.recipe.KitchenDuty;
import catering.businesslogic.turn.KitchenTurn;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.user.Cook;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ServiceSummaryManager {
    private List<ServiceSummaryEventReceiver> receivers;

    public ServiceSummaryManager() {
        this.receivers = new ArrayList<>();
    }

    public void addReceiver(ServiceSummaryEventReceiver receiver) {
        receivers.add(receiver);
    }

    public void removeReceiver(ServiceSummaryEventReceiver receiver) {
        receivers.remove(receiver);
    }

    private void notifyCreateServiceSummary(Service service) {
        for (ServiceSummaryEventReceiver receiver : receivers) {
            receiver.updateCreateServiceSummary(service);
        }
    }

    private void notifyOpenServiceSummary(Service service) {
        for (ServiceSummaryEventReceiver receiver : receivers) {
            receiver.updateOpenServiceSummary(service);
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
        notifyCreateServiceSummary(service);
    }

    public void openServiceSummary(Service service) {
        notifyOpenServiceSummary(service);
    }

    public ArrayList<Assignment> showAssignmentsState(Turn turn) {
        // Logic to get assignments state
        return new ArrayList<>();
    }

    public void addAssignment(KitchenDuty kD, KitchenTurn kitchenTurn, Duration eT, List<Cook> cooks) {
        // Logic to add assignment
        notifyAddAssignment(new Assignment(kD, kitchenTurn, eT, cooks, "", false, 1));
    }

    public void deleteAssignment(Assignment assignment, KitchenTurn kitchenTurn) {
        // Logic to delete assignment
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
