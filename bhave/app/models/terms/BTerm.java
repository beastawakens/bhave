package models.terms;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;

import models.Dictionary;
import models.terms.BObject.BObjectType;
import play.db.jpa.Model;

@Entity
public abstract class BTerm extends Model {
	
	public enum BTermType {
		Verb,
		Object,
		Subject,
		Conjunction,
		Article,
		Synonym;
	}

	public String name;
	public BTermType type;
	public Boolean testCopy;

	public String command; // Verb
	public String value; // Object
	public BObjectType objectType; // Object
	public LinkedList<Long> to; // Synonym
	
	public BTerm(String name, BTermType type) {
		this.name = name;
		this.type = type;
	}
	
	public BTerm() {
		
	}
	
	public BTerm createTestCopy() {
		switch (type) {
		case Verb:
			BTerm copy = new BVerb(name, command);
			copy.save();
			copy.command = copy.command.replaceAll(Dictionary.TERM_ID_SUBSTITUTION, String.valueOf(copy.id));
			return saveAsCopy(copy);
		case Object:
			return saveAsCopy(new BObject(name, objectType, value));
		case Subject:
			return saveAsCopy(new BSubject(name));
		case Conjunction:
			return saveAsCopy(new BConjunction(name));
		case Article:
			return saveAsCopy(new BArticle(name));
		case Synonym:
			return saveAsCopy(new BSynonym(name, to));
		default:
			throw new UnsupportedOperationException();
		}
	}

	private BTerm saveAsCopy(BTerm copy) {
		copy.testCopy = true;
		copy.save();
		return copy;
	}

}
