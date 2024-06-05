package catering.businesslogic.turn;

import catering.businesslogic.user.User;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class TurnTable {
    private static TurnTable instance = null;
    private Map<User, List<Turn>> turnTable;

    private TurnTable() {
        turnTable = new HashMap<>();
    }

    public static TurnTable getInstance() {
        if (instance == null) {
            instance = new TurnTable();
        }
        return instance;
    }

    public Map<User, List<Turn>> showTurn(TurnTableFilter filter) {
        return turnTable.entrySet().stream()
                .filter(entry -> filter.filter(entry.getKey(), entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
