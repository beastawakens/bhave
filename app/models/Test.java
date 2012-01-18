package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.google.gson.JsonObject;

import play.db.jpa.Model;

@Entity
public class Test extends Model {

	public String name;
	public ArrayList<Long> screenshotIds;

	@OneToMany(cascade=CascadeType.ALL)
	public List<Bhaviour> bhaviours;
	
	public Test(String name, ArrayList<Long> screenshotIds, ArrayList<Bhaviour> bhaviours) {
		this.name = name;
		this.screenshotIds = screenshotIds;
		this.bhaviours = bhaviours;
	}
	
}
