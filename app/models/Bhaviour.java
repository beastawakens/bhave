package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Bhaviour extends Model {

	public String language;
	public String command;
	
}
