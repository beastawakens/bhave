package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import models.terms.BTerm;
import models.terms.BObject.BObjectType;

import play.db.jpa.JPABase;
import play.db.jpa.Model;

@Entity
public class Dictionary extends Model {
	
	public static final String VALUE_SUBSTITUTION = "~@~";
	public ArrayList<BTerm> terms;
	
	public Dictionary() {

	}

	public Dictionary(List<BTerm> findAll) {
		this.terms = new ArrayList<BTerm>(findAll);
	}

}
