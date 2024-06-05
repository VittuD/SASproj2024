package catering.businesslogic.turn;

import java.time.LocalDateTime;
import java.util.List;

public class Grouping {
    private List<KitchenTurn> group;

    private LocalDateTime firstDeadline;

    private Recurrency recurrency;

    public Grouping(List<KitchenTurn> group, Recurrency recurrency) {
        this.group = group;
        this.firstDeadline = computeDeadline();
        this.recurrency = recurrency;
    }

    private LocalDateTime computeDeadline() {
        /*stub*/
        return null;
    }

}
