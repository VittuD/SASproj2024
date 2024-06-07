package catering.businesslogic.assignment;

import catering.businesslogic.recipe.KitchenDuty;
import catering.businesslogic.recipe.Preparation;
import catering.businesslogic.turn.KitchenTurn;
import catering.businesslogic.user.Cook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest {

    KitchenDuty kD;
    KitchenTurn kT;
    Assignment a;

    @BeforeEach
    void setUp() {
        kD = new Preparation("preparation", "instructions", "prep desc");
        kT = new KitchenTurn();
        a = new Assignment.Builder().description("Descrizione").estimatedTime(Duration.ofDays(10)).completed(false).quantity(5).kitchenDuty(kD).kitchenTurn(kT).build();
    }

    @Test
    void edit() {
        KitchenDuty kD2 = new Preparation("preparation2", "instructions2", "prep desc2");
        KitchenTurn kT2 = new KitchenTurn(true);
        Assignment a2 = new Assignment.Builder().description("Descrizione").estimatedTime(Duration.ofDays(10)).completed(false).quantity(5).kitchenDuty(kD).kitchenTurn(kT).build();
        List<Cook> cooks = List.of(new Cook("Cook1"));
        a.edit(kT2, cooks, "Descrizione Modificata", true, 10, Duration.ofDays(5));
        assertEquals("Descrizione Modificata", a.getDescription());
        assertEquals(Duration.ofDays(5), a.getEstimatedTime());
        assertTrue(a.getCompleted());
        assertEquals(10, a.getQuantity());
    }
}