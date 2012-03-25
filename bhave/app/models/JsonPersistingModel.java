package models;

import java.io.*;

import javax.persistence.*;

import models.terms.*;

import org.apache.commons.io.*;

import play.*;
import play.db.jpa.*;

import bhave.*;

import com.google.gson.*;

@MappedSuperclass
public class JsonPersistingModel extends Model {

	@PostPersist
	@PostUpdate
	public void postChange() {
		Logger.info("Saving out to json:" + this.id + " " + this.getClass().getName());
		Long id = this.id;
		String type = this.getClass().getName();

		File bhaveFolder = new File(Play.applicationPath, "bhave");
		
		if (!bhaveFolder.exists()) {
			bhaveFolder.mkdir();
		}
		
		File typeFolder = new File(bhaveFolder, type);
		if (!typeFolder.exists()) {
			typeFolder.mkdir();
		}
		
		File modelFile = new File(typeFolder, id+".json");
		
		if (!modelFile.exists()) {
			try {
				modelFile.createNewFile();
			} catch (IOException e) {
				Logger.error("Couldn't create json file of model: ", id + ". " + type, this);
			}
		}
		modelFile.setWritable(true);
		
		Gson gson = new GsonBuilder().registerTypeAdapter(BTerm.class, new BTermSerializer()).create();
		try {
			FileUtils.writeStringToFile(modelFile, gson.toJson(this));
		} catch (IOException e) {
			Logger.error("Couldn't write json file of model: ", id + ". " + type, this);
		}		
		
	}
}
