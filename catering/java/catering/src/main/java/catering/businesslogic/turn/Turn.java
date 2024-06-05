package catering.businesslogic.turn;

import catering.businesslogic.assignment.Assignment;
import catering.businesslogic.assignment.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;

public abstract class Turn {
    protected LocalDate date;
    protected LocalTime timeStart;
    protected LocalTime timeEnd;
    protected List<Assignment> assList;
    protected boolean published;
    protected Period deadline;

    protected Service service;

    public Turn(){}

    @Override
    public String toString() {
        return "Turn{" +
                "date=" + date +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", assList=" + assList +
                ", published=" + published +
                ", deadline=" + deadline +
                ", service=" + service+
                '}';
    }

    public void updateTurn(Turn turn) {
        // Logic to update turn
    }
}
