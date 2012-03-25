package controllers;

import java.util.*;

import models.*;
import models.terms.*;
import play.mvc.*;
import bhave.*;

import com.google.gson.*;

public class Bhaviours extends Controller {
	
	public static void create() {
		Bhaviour bhaviour = new Bhaviour("New Bhaviour", new ArrayList<Long>(), new ArrayList<BTerm>());
		bhaviour.save();
		redirect("Bhaviours.show", bhaviour.id);
	}
	
	public static void save(String body) {
		Gson gson = new GsonBuilder().registerTypeAdapter(BTerm.class, new BTermDeserializer()).create();
		Bhaviour bhaviour = gson.fromJson(body, Bhaviour.class);
		if (bhaviour.id != null) {
			bhaviour = bhaviour.merge();
		}
		bhaviour.save();
		renderText(bhaviour.id);
	} 
	
	public static void show(long id) {
		Bhaviour bhaviour = Bhaviour.findById(id);
		if (bhaviour == null) {
			notFound();
		}
		renderArgs.put("bhaviourId", id);
		renderArgs.put("bhaviourName", bhaviour.name);
		render();
	}
	
	public static void delete(long id) {
		Bhaviour bhaviour = Bhaviour.findById(id);
		if (bhaviour != null) {
			bhaviour.delete();
			ok();
		} else {
			notFound();
		}
	}

	public static void get(long id) {
		Bhaviour bhaviour = Bhaviour.findById(id);
		renderJSON(bhaviour, new BTermSerializer());
	}

}
