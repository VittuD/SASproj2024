package catering.businesslogic.assignment;

import catering.businesslogic.event.Service;
import catering.businesslogic.menu.Menu;
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
        HashMap<Turn, List<Assignment>> serviceSummary = new HashMap<>();
        serviceSummary.put(new KitchenTurn(), List.of(new Assignment.Builder().description("Descrizione").estimatedTime(Duration.ofDays(10)).completed(false).quantity(5).kitchenDuty(null).kitchenTurn(null).build()));
        String json = ServiceSummary.convertServiceSummaryToJson(serviceSummary);
        assertNotNull(json);
    }

    @Test
    void convertJsonToServiceSummary() {
    }

    @Test
    void convertKitchenTurnToJson() {
    }

    @Test
    void open() {
    }

    @Test
    void create() {
        ServiceSummary serviceSummary = new ServiceSummary();
        serviceSummary.create(s, m);
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