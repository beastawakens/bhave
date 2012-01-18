package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Screenshot extends Model {
	
	public String locator;
	public String href;
	public Blob source;

}
