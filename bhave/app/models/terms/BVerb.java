package models.terms;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class BVerb extends BTerm {
	
	public BVerb(String name, String command) {
		super(name, BTermType.Verb);
		this.command = command;
	}

}
