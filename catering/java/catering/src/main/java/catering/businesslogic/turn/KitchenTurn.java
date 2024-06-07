package catering.businesslogic.turn;

import catering.businesslogic.assignment.Assignment;
import catering.businesslogic.event.Service;
import com.google.gson.Gson;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class KitchenTurn extends Turn {
    private  Boolean full;

    private Kitchen kitchen;

    public KitchenTurn() {
        super();
        this.full = false;
        this.kitchen = new Kitchen("Kitchen");
    }

    public KitchenTurn (boolean full) {
        super();
        this.full = full;
        this.kitchen = new Kitchen("Kitchen");
    }
}
