package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Bhaviour extends Model {

	public Bhaviour(String language, String command) {
		this.language = language;
		this.command = command;
	}
	
	public String command;
	public String language;
	
}
