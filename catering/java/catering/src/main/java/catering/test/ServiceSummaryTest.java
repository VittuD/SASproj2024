package catering.test;

import catering.businesslogic.assignment.Assignment;
import catering.businesslogic.assignment.ServiceSummary;
import catering.businesslogic.recipe.KitchenDuty;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.turn.KitchenTurn;
import catering.businesslogic.user.Cook;
import static org.junit.jupiter.api.Assertions.*;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;//java: module not found: org.junit.jupiter.engine

class ServiceSummaryTest {
    private ServiceSummary serviceSummary;
    private Assignment assignment;
    private KitchenDuty kitchenDuty;
    private KitchenTurn kitchenTurn;
    private List<Cook> cooks;
    private String description;
    private boolean completed;
    private int quantity;
    private Duration estimatedTime;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        serviceSummary = new ServiceSummary();
        assignment = new Assignment.Builder().
                completed(false).
                description("description1").
                build();
        kitchenDuty = new Recipe();
        kitchenTurn = new KitchenTurn();
        cooks = new ArrayList<>();
        description = "description";
        completed = false;
        quantity = 1;
        estimatedTime = Duration.ofMinutes(30);
    }

    @org.junit.jupiter.api.Test
    void testAddAssignment() {
        serviceSummary.addAssignment(kitchenDuty, kitchenTurn, estimatedTime, cooks, quantity);
        assertTrue(serviceSummary.getServiceSummary().get(kitchenTurn).contains(kitchenDuty));
    }

    @org.junit.jupiter.api.Test
    void testDeleteAssignment() {
        serviceSummary.addAssignment(kitchenDuty, kitchenTurn, estimatedTime, cooks, quantity);
        serviceSummary.deleteAssignment(assignment, kitchenTurn);
        assertFalse(serviceSummary.getServiceSummary().get(kitchenTurn).contains(kitchenDuty));
    }

    @org.junit.jupiter.api.Test
    void testModifyAssignment() {
        serviceSummary.addAssignment(kitchenDuty, kitchenTurn, estimatedTime, cooks, quantity);
        serviceSummary.modifyAssignment(assignment, kitchenTurn, cooks, description, completed, quantity, estimatedTime);
        Assignment modifiedAssignment = serviceSummary.getServiceSummary().get(kitchenTurn).get(0);
        assertEquals(description, modifiedAssignment.getDescription());
        assertEquals(completed, modifiedAssignment.isCompleted());
        assertEquals(quantity, modifiedAssignment.getQuantity());
        assertEquals(estimatedTime, modifiedAssignment.getEstimatedTime());
        assertEquals(cooks, modifiedAssignment.getCooks());
    }

    @org.junit.jupiter.api.Test
    void testOrderAssignments() {
        Assignment assignment2 = new Assignment.Builder().
                description("description2")
                .build();
        List<Assignment> orderedAssignments = new ArrayList<>();
        orderedAssignments.add(assignment);
        orderedAssignments.add(assignment2);
        // Add assignments to orderedAssignments in the desired order...
        serviceSummary.orderAssignments(orderedAssignments, kitchenTurn);
        assertEquals(orderedAssignments, serviceSummary.getServiceSummary().get(kitchenTurn));
    }

    @org.junit.jupiter.api.Test
    void testShowAssigmentState() {
        serviceSummary.addAssignment(kitchenDuty, kitchenTurn, estimatedTime, cooks, quantity);
        List<Assignment> assignments = serviceSummary.showAssigmentState(kitchenTurn);
        assertTrue(assignments.contains(kitchenDuty));
    }
}