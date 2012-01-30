package models;

import java.util.ArrayList;
import java.util.List;

import models.terms.BTerm;
import models.terms.BObject.BObjectType;

import play.db.jpa.Model;

public class Dictionary extends Model {
	
	public static final String VALUE_SUBSTITUTION = "~@~";
	public List<BTerm> terms;
	
	public Dictionary() {
		this.terms = BTerm.findAll();
	}

}
