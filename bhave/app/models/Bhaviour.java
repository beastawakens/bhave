package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.*;

import models.terms.BTerm;
import play.db.jpa.Model;

@Entity
public class Bhaviour extends BhaveModel {

	public Bhaviour(ArrayList<BTerm> syntax, String language, String command) {
		this.syntax = syntax;
		this.language = language;
		this.command = command;
	}
	
	public String command;
	public String language;
	
	@ManyToMany(cascade=CascadeType.ALL)	
	public List<BTerm> syntax;
	
	
}
