package catering.businesslogic.recipe;

import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Recipe extends KitchenDuty {
    private static Map<Integer, Recipe> all = new HashMap<>();
    private List<Preparation> preparations = new ArrayList<>();
    public int id;

    public Recipe() {
    }

    public Recipe(String name, String instructions, String description, int id, List<Preparation> preparations ) {
        super(name, instructions, description);
        this.id = id;
        this.preparations = preparations;
        List<Integer> prep_ids = new ArrayList<Integer>();
        for (Preparation prep : preparations) {
            prep_ids.add(prep.getId());
        }
    }

    public Recipe(String name) {
        super(name, "", "");
    }

    public String toString() {
        return name;
    }

    // STATIC METHODS FOR PERSISTENCE

    public static ArrayList<Recipe> loadAllRecipes() {
        String query = "SELECT * FROM Recipes";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");
                if (all.containsKey(id)) {
                    Recipe rec = all.get(id);
                    rec.name = rs.getString("name");
                } else {
                    Recipe rec = new Recipe(rs.getString("name"));
                    rec.id = id;
                    all.put(rec.id, rec);
                }
            }
        });
        ArrayList<Recipe> ret = new ArrayList<Recipe>(all.values());
        ret.sort(new Comparator<Recipe>() {
            @Override
            public int compare(Recipe o1, Recipe o2) {
                return (o1.getName().compareTo(o2.getName()));
            }
        });
        return ret;
    }

    public static ArrayList<Recipe> getAllRecipes() {
        return new ArrayList<Recipe>(all.values());
    }

    public static Recipe loadRecipeById(int id) {
        if (all.containsKey(id)) return all.get(id);
        Recipe rec = new Recipe();
        String query = "SELECT * FROM Recipes WHERE id = " + id;
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                    rec.name = rs.getString("name");
                    rec.id = id;
                    all.put(rec.id, rec);
            }
        });
        return rec;
    }

    // Get the highest id in the database
    public static int getMaxId() {
        int maxId = 0;
        String query = "SELECT MAX(id) FROM Recipes";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                maxId = rs.getInt(1);
            }
        });
        return maxId;
    }

    // Getters and Setters
    public List<Preparation> getPreparations() {
        return preparations;
    }

}
