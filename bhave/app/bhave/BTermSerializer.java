package bhave;

import java.lang.reflect.*;

import models.terms.*;

import com.google.gson.*;

public class BTermSerializer implements JsonSerializer<BTerm>{

	@Override
	public JsonElement serialize(BTerm term, Type typeOfTerm, JsonSerializationContext context) {
		
		switch (term.type) {
		case Article:
			return new GsonBuilder().create().toJsonTree(BArticle.<BArticle>findById(term.id));
		case Conjunction:
			return new GsonBuilder().create().toJsonTree(BConjunction.<BConjunction>findById(term.id));
		case Object:
			return new GsonBuilder().create().toJsonTree(BObject.<BObject>findById(term.id));
		case Subject:
			return new GsonBuilder().create().toJsonTree(BSubject.<BSubject>findById(term.id));
		case Synonym:
			return new GsonBuilder().create().toJsonTree(BSynonym.<BSynonym>findById(term.id));
		case Verb:
			return new GsonBuilder().create().toJsonTree(BVerb.<BVerb>findById(term.id));
		default:
			System.err.println("WTF? Is this a new Term type?");
			return null;
		}
	}

}
