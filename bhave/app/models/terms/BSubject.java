package models.terms;

import javax.persistence.Entity;

@Entity
public class BSubject extends BTerm {

	public BSubject(String name) {
		super(name, BTermType.Subject);
	}

}
