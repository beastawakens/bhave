package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Screenshot extends Model {
	
	public String locator;
	public String href;
	@Column(length=6000)
	public String source;
	
	private static String dummySource = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==";
	
	public void setSource(String source) {
		this.source = dummySource;
	}

}
