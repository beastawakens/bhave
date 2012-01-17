package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.google.gson.JsonObject;

import play.db.jpa.Model;

@Entity
public class Test extends Model {

	public String name;
	@OneToMany(cascade=CascadeType.ALL)
	public List<Bhaviour> bhaviours;
	@OneToMany(cascade=CascadeType.ALL)
	public List<Screenshot> screenshots;
	
}
