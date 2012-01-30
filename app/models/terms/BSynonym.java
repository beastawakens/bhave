package models.terms;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class BSynonym extends BTerm {
	
	public BSynonym(String name, LinkedList<Long> to) {
		super(name, BTermType.Synonym);
		this.to = to;
	}

}
