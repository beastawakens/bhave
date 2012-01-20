package models;

import java.util.List;

import play.db.jpa.Model;

public class Dictionary extends Model {
	
	public List<BVerb> verbs;
	public List<BObject> objects;
	public List<BSubject> subjects;
	public List<BSynonym> synonyms;

}
