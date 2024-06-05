package catering.businesslogic.assignment;

import java.lang.module.ModuleDescriptor;
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
    private KitchenDuty kitchenDuty;
    private KitchenTurn kitchenTurn;

    /*public Assignment(KitchenDuty kD, KitchenTurn kitchenTurn, Duration eT, List<Cook> cooks, String desc, boolean completed, int quantity) {
        this.kitchenDuty = kD;
        this.description = desc;
        this.estimatedTime = eT;
        this.completed = completed;
        this.cooks = cooks;
        this.quantity = quantity;
    }*/

    // Builder pattern to avoid telescoping constructor
    public static class Builder {
        private String description;
        private Duration estimatedTime;
        private boolean completed;
        private List<Cook> cooks;
        private int quantity;
        private KitchenDuty kitchenDuty;
        private KitchenTurn kitchenTurn;

        public Builder() {}

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder estimatedTime(Duration estimatedTime) {
            this.estimatedTime = estimatedTime;
            return this;
        }

        public Builder completed(boolean completed) {
            this.completed = completed;
            return this;
        }

        public Builder cooks(List<Cook> cooks) {
            this.cooks = cooks;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder kitchenDuty(KitchenDuty kitchenDuty) {
            this.kitchenDuty = kitchenDuty;
            return this;
        }

        public Builder kitchenTurn(KitchenTurn kitchenTurn) {
            this.kitchenTurn = kitchenTurn;
            return this;
        }

        public Assignment build() {
            return new Assignment(this);
        }
    }

    private Assignment(Builder builder) {
        this.description = builder.description;
        this.estimatedTime = builder.estimatedTime;
        this.completed = builder.completed;
        this.cooks = builder.cooks;
        this.quantity = builder.quantity;
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
