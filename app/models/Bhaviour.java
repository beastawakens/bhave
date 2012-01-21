package models;

import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Bhaviour extends Model {

	public Bhaviour(LinkedList<BTerm> syntax, String language, String command) {
		this.syntax = syntax;
		this.language = language;
		this.command = command;
	}
	
	public String command;
	public String language;
	public LinkedList<BTerm> syntax;
	
	
}
