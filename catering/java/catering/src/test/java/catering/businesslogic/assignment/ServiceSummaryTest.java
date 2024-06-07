package catering.businesslogic.assignment;

import catering.businesslogic.event.Service;
import catering.businesslogic.menu.Menu;
import catering.businesslogic.recipe.KitchenDuty;
import catering.businesslogic.recipe.Preparation;
import catering.businesslogic.turn.KitchenTurn;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceSummaryTest {

    Service s;
    User user;
    Menu m;

    @BeforeEach
    void setUp() {
        s = new Service("Service");
        user = new User();
        m = new Menu(user, "Menu", new String[]{"Antipasto", "Primo", "Secondo", "Dolce"});
    }

    @Test
    void convertServiceSummaryToJson() {
        KitchenDuty kD = new Preparation("preparation", "instructions", "prep desc");
        KitchenTurn kT = new KitchenTurn();
        HashMap<KitchenTurn, List<Assignment>> serviceSummary = new HashMap<>();
        Assignment a = new Assignment.Builder().description("Descrizione").estimatedTime(Duration.ofDays(10)).completed(false).quantity(5).kitchenDuty(kD).kitchenTurn(kT).build();
        serviceSummary.put(new KitchenTurn(), List.of(a));
        String json = ServiceSummary.convertServiceSummaryToJson(serviceSummary);
        System.out.println(json);
        assertNotNull(json);
    }

    @Test
    void convertJsonToServiceSummary() {
        HashMap<KitchenTurn, List<Assignment>> serviceSummary = new HashMap<>();
        serviceSummary.put(new KitchenTurn(), List.of(new Assignment.Builder().description("Descrizione").estimatedTime(Duration.ofDays(10)).completed(false).quantity(5).kitchenDuty(null).kitchenTurn(new KitchenTurn()).build()));
        String json = ServiceSummary.convertServiceSummaryToJson(serviceSummary);
        System.out.println(json);
        assertEquals(serviceSummary, ServiceSummary.convertJsonToServiceSummary(json));
    }

    @Test
    void convertKitchenTurnToJson() {
    }

    @Test
    void convertJsonToKitchenTurn() {
        KitchenTurn kitchenTurn = new KitchenTurn();
        String json = ServiceSummary.convertKitchenTurnToJson(kitchenTurn);
        System.out.println(json);
        assertEquals(kitchenTurn.toString(), ServiceSummary.convertJsonToKitchenTurn(json).toString());
    }

    @Test
    void convertAssignmentToJson() {
        Assignment assignment = new Assignment.Builder().description("Descrizione").estimatedTime(Duration.ofDays(10)).completed(false).quantity(5).kitchenDuty(null).kitchenTurn(null).build();
        String json = ServiceSummary.convertAssignmentToJson(assignment);
        System.out.println(json);
        assertNotNull(json);
    }

    @Test
    void convertJsonToAssignment() {
        Assignment assignment = new Assignment.Builder().description("Descrizione").estimatedTime(Duration.ofDays(10)).completed(false).quantity(5).kitchenDuty(null).kitchenTurn(null).build();
        String json = ServiceSummary.convertAssignmentToJson(assignment);
        System.out.println(json);
        assertEquals(assignment.toString(), ServiceSummary.convertJsonToAssignment(json).toString());
    }

    @Test
    void open() {
    }

    @Test
    void create() {
        ServiceSummary serviceSummary = new ServiceSummary();
        serviceSummary.create(s, m, new KitchenTurn());
    }

    @Test
    void showAssignmentsState() {
    }

    @Test
    void addAssignment() {
    }

    @Test
    void deleteAssignment() {
    }

    @Test
    void modifyAssignment() {
    }

    @Test
    void orderAssignments() {
    }
}