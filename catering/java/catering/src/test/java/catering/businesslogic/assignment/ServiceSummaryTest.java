package catering.businesslogic.assignment;

import catering.businesslogic.CatERing;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
        KitchenTurn kitchenTurn = new KitchenTurn();
        String json = ServiceSummary.convertKitchenTurnToJson(kitchenTurn);
        System.out.println(json);
        assertNotNull(json);
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
        Menu m = CatERing.getInstance().getMenuManager().getAllMenus().get(2);
        CatERing.getInstance().getMenuManager().setCurrentMenu(m);
        Service s = CatERing.getInstance().getEventManager().createService("Service 1");
        s.setApprovedMenuId(m.getId());
        CatERing.getInstance().getServiceSummaryMgr().openServiceSummary(s);
    }

    @Test
    void create() {
        ServiceSummary serviceSummary = new ServiceSummary();
        serviceSummary.create(s, m, new KitchenTurn());
    }

    @Test
    void showAssignmentsState() {
        LocalDate date = LocalDate.of(2024, 6, 6);
        LocalTime timeStart = LocalTime.of(9, 0);
        LocalTime timeEnd = LocalTime.of(17, 0);
        List<Assignment> assList = new ArrayList<>();
        assList.add(new Assignment.Builder().
                completed(false).
                description("description1").
                build());
        assList.add(new Assignment.Builder().
                completed(false).
                description("description2").
                build());
        KitchenTurn kt = new KitchenTurn(date, timeStart, timeEnd, assList, false, null, s, true, null);
        List<Assignment> list = CatERing.getInstance().getServiceSummaryMgr().showAssignmentsState(kt);
        assertNotNull(list);
    }

    @Test
    void addAssignment() {
        Assignment a = new Assignment.Builder().completed(false).description("description1").build();
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