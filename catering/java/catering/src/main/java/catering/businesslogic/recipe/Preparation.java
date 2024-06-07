package catering.businesslogic.recipe;

import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class Preparation extends KitchenDuty{

    public Preparation(String name, String instructions, String description) {
        super(name, instructions, description, getMaxId()+1);
    }

    // Get the highest id in the database
    public static int getMaxId() {
        AtomicInteger maxId = new AtomicInteger(0);
        String query = "SELECT MAX(id) FROM Preparation";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                maxId.set(rs.getInt(1));
            }
        });
        return maxId.get();
    }

}
