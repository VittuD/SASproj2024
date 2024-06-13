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

    public KitchenTurn(LocalDate date, LocalTime timeStart, LocalTime timeEnd, List<Assignment> assList, boolean published, Period deadline, Service service, Boolean full, Kitchen kitchen) {
        super(date, timeStart, timeEnd, assList, published, deadline, service);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public boolean isFull() {
        return full;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }
}
