package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import models.terms.BTerm;
import models.terms.BObject.BObjectType;

import play.db.jpa.JPABase;
import play.db.jpa.Model;

@Entity
public class Dictionary extends JsonPersistingModel {
	
	public static final String TERM_ID_SUBSTITUTION = "~~id~~";
	public static final String PAGE_SUBSTITUTION = "~~page~~";
	public static final String VALUE_SUBSTITUTION = "~~value~~";
	public static final String ELEMENT_SUBSTITUTION = "~~element~~";
	public static final String ATTRIBUTE_SUBSTITUTION = "~~attribute~~";
	
	public ArrayList<BTerm> terms;
	
	public Dictionary() {

	}

	public Dictionary(List<BTerm> terms) {
		this.terms = new ArrayList<BTerm>(terms);
	}

}
