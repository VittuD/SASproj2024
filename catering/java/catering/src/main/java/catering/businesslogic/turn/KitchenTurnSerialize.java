package catering.businesslogic.turn;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class KitchenTurnSerialize implements JsonSerializer<KitchenTurn> {
    @Override
    public JsonElement serialize(KitchenTurn kitchenTurn, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("full", kitchenTurn.isFull());
        jsonObject.add("kitchen", jsonSerializationContext.serialize(kitchenTurn.getKitchen()));
        return jsonObject;
    }
}
