package models.terms;

import javax.persistence.Entity;

@Entity
public class BConjunction extends BTerm {

	public BConjunction(String name) {
		super(name, BTermType.Conjunction);
	}

	@Override
	public BConjunction createTestCopy() {
		return (BConjunction) saveAsCopy(new BConjunction(name));
	}
	
	

}
