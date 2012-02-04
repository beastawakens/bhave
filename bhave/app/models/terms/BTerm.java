package models.terms;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;

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
	public String command;
	public String value;
	public LinkedList<Long> to;
	public BTermType type;
	public BTermType forType;
	public BObjectType objectType;
	
	public BTerm(String name, BTermType type) {
		this.name = name;
		this.type = type;
	}
	
	public BTerm() {
		
	}

}
