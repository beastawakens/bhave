package bhave;

import java.lang.reflect.Type;
import java.util.*;

import models.terms.*;
import models.terms.BObject.BObjectType;
import models.terms.BTerm.BTermType;

import com.google.gson.*;
import com.google.gson.reflect.*;

public class BTermDeserializer implements JsonDeserializer<BTerm>{

	@Override
	public BTerm deserialize(JsonElement json, Type typeOf, JsonDeserializationContext context) throws JsonParseException {
		JsonObject termJson = json.getAsJsonObject();
		JsonElement idValue = termJson.get("id");
		if (idValue != null) {
			Long id = Long.valueOf(idValue.getAsString());
			
			BTerm term = BTerm.findById(id);
			if (term != null) {
				return findExisting(term, id);
			} else {
				return createNew(termJson);
			}
		} else {
			return createNew(termJson);
		}
	}
	
	public BTerm findExisting(BTerm term, long id) {
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
	
	public BTerm createNew(JsonObject termJson) {
		switch (BTermType.valueOf(termJson.get("type").getAsString())) {
		case Article:
			return new BArticle(termJson.get("name").getAsString());
		case Conjunction:
			return new BConjunction(termJson.get("name").getAsString());
		case Object:
			return new BObject(termJson.get("name").getAsString(), BObjectType.valueOf(termJson.get("objectType").getAsString()), termJson.get("value").getAsString());
		case Subject:
			return new BSubject(termJson.get("name").getAsString());
		case Synonym:
			Type collectionType = new TypeToken<LinkedList<Long>>(){}.getType();
			LinkedList<Long> to = new Gson().fromJson(termJson.get("to"), collectionType);
			return new BSynonym(termJson.get("name").getAsString(), to);
		case Verb:
			return new BVerb(termJson.get("name").getAsString(), termJson.get("command").getAsString());
		default:
			System.err.println("WTF? Is this a new Term type?");
			return null;
		}
		
	}
	
}
