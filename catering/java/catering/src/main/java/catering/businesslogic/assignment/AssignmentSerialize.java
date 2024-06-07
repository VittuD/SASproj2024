package catering.businesslogic.assignment;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class AssignmentSerialize implements JsonSerializer<Assignment>{
    @Override
    public JsonElement serialize(Assignment src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("description", src.getDescription());
        jsonObject.addProperty("estimatedTime", src.getEstimatedTime().getSeconds());
        jsonObject.addProperty("completed", src.getCompleted());
        jsonObject.addProperty("quantity", src.getQuantity());
        // Assuming KitchenDuty and KitchenTurn have overridden toString() method to return JSON string
        jsonObject.addProperty("kitchenDuty", src.getKitchenDuty().toString());
        jsonObject.addProperty("kitchenTurn", src.getKitchenTurn().toString());
        return jsonObject;
    }
}
