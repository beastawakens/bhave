package models.terms;

import javax.persistence.Entity;

import models.*;

import play.db.jpa.Model;

@Entity
public class BVerb extends BTerm {
	public String command;
	
	public BVerb(String name, String command) {
		super(name, BTermType.Verb);
		this.command = command;
	}

}
