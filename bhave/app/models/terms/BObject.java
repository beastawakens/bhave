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
	
	public BObject(String name, BObjectType type, String value) {
		super(name, BTermType.Object);
		this.objectType = type;
		this.value = value;
	}

}
