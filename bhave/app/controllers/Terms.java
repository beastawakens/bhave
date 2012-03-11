package controllers;

import bhave.*;

import com.google.gson.*;

import models.*;
import models.terms.BTerm;
import play.mvc.Controller;

public class Terms extends Controller {
	
	public static void get(Long id) {
		BTerm term = BTerm.findById(id);
		if (term != null) {
			renderJSON(term.createTestCopy());
		} else {
			notFound();
		}
	}
	
	public static void delete(Long id) {
		BTerm term = BTerm.findById(id);
		if (term != null) {
			term.delete();
			ok();
		} else {
			notFound();
		}
	}
	
	public static void save(String body) {
		Gson gson = new GsonBuilder().registerTypeAdapter(BTerm.class, new BTermDeserializer()).create();
		BTerm term = gson.fromJson(body, BTerm.class);
		if (term.id != null) {
			term = term.merge();
		}
		term.save();
		renderText(term.id);
	}

}
