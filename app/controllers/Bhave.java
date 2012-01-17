package controllers;

import java.util.List;

import models.Test;
import play.mvc.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Bhave extends Controller {

	public static void list() {
		List<Test> tests = Test.findAll();
		render(tests);
	}
	
	public static void save(String body) {
		Test test = new Gson().fromJson(body, Test.class);
		if (test.id != null) {
			test = test.merge();
		} 
		test.save();
		renderText(test.id);
	} 
	
	public static void show(long id) {
		Test test = Test.findById(id);
		render(test);
	}
	
	public static void loadTest(long id) {
		Test test = Test.findById(id);
		renderJSON(test);
	}

	public static void newTest() {
		render();
	}
	
	public static void dummy() {
		renderText("");
	}
}
