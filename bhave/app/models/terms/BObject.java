package models.terms;

import javax.persistence.Entity;

@Entity
public class BObject extends BTerm {
	
	public enum BObjectType {
		Page,
		PageAttribute,
		Element,
		ElementAttribute,
		Value;
	}
	
	public String value;
	public BObjectType objectType;
	
	public BObject(String name, BObjectType type, String value) {
		super(name, BTermType.Object);
		this.objectType = type;
		this.value = value;
	}
	
	@Override
	public BObject createTestCopy() {
		return (BObject) saveAsCopy(new BObject(name, objectType, value));
	}

}
