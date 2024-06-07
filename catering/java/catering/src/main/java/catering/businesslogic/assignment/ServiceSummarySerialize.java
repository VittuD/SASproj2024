package catering.businesslogic.assignment;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ServiceSummarySerialize implements JsonSerializer<ServiceSummary> {

    @Override
    public JsonElement serialize(ServiceSummary serviceSummary, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        //jsonObject.addProperty("serviceSummary", serviceSummary.getServiceSummary());
        return null;
    }
}
