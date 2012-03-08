package bhave;

import java.lang.reflect.Type;

import models.terms.*;
import models.terms.BTerm.BTermType;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class BTermDeserializer implements JsonDeserializer<BTerm>{

	@Override
	public BTerm deserialize(JsonElement json, Type typeOf, JsonDeserializationContext context) throws JsonParseException {
		Long id = Long.valueOf(json.getAsJsonObject().get("id").getAsString());
		
		BTerm term = BTerm.findById(id);
		switch (term.type) {
		case Article:
			return BArticle.<BArticle>findById(id);
		case Conjunction:
			return BConjunction.<BConjunction>findById(id);
		case Object:
			return BObject.<BObject>findById(id);
		case Subject:
			return BSubject.<BSubject>findById(id);
		case Synonym:
			return BSynonym.<BSynonym>findById(id);
		case Verb:
			return BVerb.<BVerb>findById(id);
		default:
			System.err.println("WTF? Is this a new Term type?");
			return null;
		}
	}
}
