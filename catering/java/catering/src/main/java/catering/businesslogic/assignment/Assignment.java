package catering.businesslogic.assignment;

import java.time.Duration;
import java.util.List;
import catering.businesslogic.recipe.KitchenDuty;
import catering.businesslogic.turn.KitchenTurn;
import catering.businesslogic.turn.Turn;
import catering.businesslogic.user.Cook;

public class Assignment {
    private String description;
    private Duration estimatedTime;
    private boolean completed;
    private List<Cook> cooks;
    private int quantity;



    public Assignment(KitchenDuty kD, KitchenTurn kitchenTurn, Duration eT, List<Cook> cooks, String desc, boolean completed, int quantity) {
        this.description = desc;
        this.estimatedTime = eT;
        this.completed = completed;
        this.cooks = cooks;
        this.quantity = quantity;
    }

    public void edit(Turn turn, List<Cook> cooks, String description, boolean completed, int quantity, Duration estimatedTime) {
        if (turn != null) {
            // Assuming Turn has a method to update or assign turn
            turn.updateTurn(turn);
        }
        if (cooks != null) {
            this.cooks = cooks;
        }
        if (description != null) {
            this.description = description;
        }
        this.completed = completed;
        this.quantity = quantity;
        this.estimatedTime = estimatedTime;
    }

    // Getters and setters for each field could be added here
}
