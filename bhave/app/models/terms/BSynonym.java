package models.terms;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class BSynonym extends BTerm {
	
	@Column(name="synonymTo")
	public LinkedList<Long> to;
	
	public BSynonym(String name, LinkedList<Long> to) {
		super(name, BTermType.Synonym);
		this.to = to;
	}

}
