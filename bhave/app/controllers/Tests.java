package controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import models.Bhaviour;
import models.Screenshot;
import models.Test;
import models.terms.BObject;
import models.terms.BTerm;
import models.terms.BVerb;
import models.terms.BObject.BObjectType;

import bhave.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import play.mvc.Controller;

public class Tests extends Controller {
	
	public static void create() {
		Test test = new Test("New test", new ArrayList<Long>(), new ArrayList<Bhaviour>());
		test.save();
		redirect("Tests.show", test.id);
	}
	
	public static void save(String body) {
		Gson gson = new GsonBuilder().registerTypeAdapter(BTerm.class, new BTermDeserializer()).create();
		Test test = gson.fromJson(body, Test.class);
		if (test.id != null) {
			test = test.merge();
		}
		test.save();
		renderText(test.id);
	} 
	
	public static void show(long id) {
		Test test = Test.findById(id);
		if (test == null) {
			notFound();
		}
		renderArgs.put("testId", id);
		renderArgs.put("testName", test.name);
		render();
	}
	
	public static void delete(long id) {
		Test test = Test.findById(id);
		if (test != null) {
			test.delete();
			ok();
		} else {
			notFound();
		}
	}

	public static void get(long id) {
		Test test = Test.findById(id);
		renderJSON(test, new BTermSerializer());
	}

}
