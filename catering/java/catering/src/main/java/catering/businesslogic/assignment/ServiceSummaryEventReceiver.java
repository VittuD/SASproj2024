package catering.businesslogic.assignment;

import catering.businesslogic.event.Service;
import catering.businesslogic.user.Cook;
import catering.businesslogic.turn.Turn;

import java.util.ArrayList;
import java.util.List;

public interface ServiceSummaryEventReceiver {
    void updateCreateServiceSummary(ServiceSummary serviceSummary);
    void updateOpenServiceSummary(ServiceSummary serviceSummary);
    void updateAddAssignment(ServiceSummary serviceSummary);
    void updateAssignAssignment(List<Assignment> assignments, List<Cook> cook, Turn turn);
    void updateOrderAssignment(ArrayList<Assignment> assignments);
    void updateShowAssignmentState(List<Assignment> assignments);
    void updateDeleteAssignment(Assignment assignment);
    void updateModifyAssignment(Assignment assignment, Turn turn, List<Cook> cook);
}

