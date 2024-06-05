package catering.businesslogic.assignment;

import catering.businesslogic.user.Cook;
import catering.businesslogic.turn.Turn;

import java.util.ArrayList;
import java.util.List;

public interface ServiceSummaryEventReceiver {
    void updateCreateServiceSummary(Service service);
    void updateOpenServiceSummary(Service service);
    void updateAddAssignment(Assignment assignment);
    void updateAssignAssignment(List<Assignment> assignments, Cook cook, Turn turn);
    void updateOrderAssignment(ArrayList<Assignment> assignments);
    void updateShowAssignmentState(List<Assignment> assignments);
    void updateDeleteAssignment(Assignment assignment);
    void updateModifyAssignment(Assignment assignment, Turn turn, Cook cook);
}

