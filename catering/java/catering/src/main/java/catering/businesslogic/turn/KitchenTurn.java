package catering.businesslogic.turn;

import catering.businesslogic.assignment.Assignment;
import catering.businesslogic.event.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class KitchenTurn extends Turn {
    private  Boolean full;

    private Kitchen kitchen;

    public KitchenTurn(){
        super();
    }

    public KitchenTurn(LocalDate date, LocalTime timeStart, LocalTime timeEnd, List<Assignment> assList, boolean published, Period deadline, Service service, Boolean full, Kitchen kitchen) {
        super(date, timeStart, timeEnd, assList, published, deadline, service);
    }
}
