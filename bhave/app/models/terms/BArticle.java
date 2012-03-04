package models.terms;

import javax.persistence.Entity;

@Entity
public class BArticle extends BTerm {

	public BArticle(String name) {
		super(name, BTermType.Article);
	}

	@Override
	public BArticle createTestCopy() {
		return (BArticle) saveAsCopy(new BArticle(name));
	}
	
	

}
