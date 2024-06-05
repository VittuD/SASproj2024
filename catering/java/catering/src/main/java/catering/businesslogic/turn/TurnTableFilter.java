package catering.businesslogic.turn;

import catering.businesslogic.user.User;

import java.util.List;

public interface TurnTableFilter {
    boolean filter(User user, List<Turn> turns);
}
