package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import models.terms.*;

import com.google.gson.JsonObject;

import play.db.jpa.Model;

@Entity
public class Bhaviour extends JsonPersistingModel {

	public String name;
	
	public ArrayList<Long> screenshots;

	@OneToMany(cascade=CascadeType.ALL)
	public List<BTerm> syntax;
	
	public Bhaviour(String name, ArrayList<Long> screenshots, ArrayList<BTerm> syntax) {
		this.name = name;
		this.screenshots = screenshots;
		this.syntax = syntax;
	}
	
}
