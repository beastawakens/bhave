package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import models.terms.*;

import com.google.gson.JsonObject;

import play.db.jpa.Model;

@Entity
public class Test extends BhaveModel {

	public String name;
	
	public ArrayList<Long> screenshots;

	@OneToMany(cascade=CascadeType.ALL)
	public List<BTerm> syntax;
	
	public Test(String name, ArrayList<Long> screenshots, ArrayList<BTerm> syntax) {
		this.name = name;
		this.screenshots = screenshots;
		this.syntax = syntax;
	}
	
}
