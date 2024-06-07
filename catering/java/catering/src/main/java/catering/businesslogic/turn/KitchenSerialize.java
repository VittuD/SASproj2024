package catering.businesslogic.turn;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class KitchenSerialize implements JsonSerializer<Kitchen> {
    @Override
    public JsonElement serialize(Kitchen kitchen, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", kitchen.getName());
        jsonObject.addProperty("id", kitchen.getId().toString());
        return jsonObject;
    }
}
