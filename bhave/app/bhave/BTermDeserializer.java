package bhave;

import java.lang.reflect.Type;

import models.terms.BTerm;
import models.terms.BTerm.BTermType;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class BTermDeserializer implements JsonDeserializer<BTerm>{

	@Override
	public BTerm deserialize(JsonElement json, Type typeOf, JsonDeserializationContext context) throws JsonParseException {
		Long id = Long.valueOf(json.getAsJsonObject().get("id").getAsString());
		return BTerm.findById(id);
	}
}
