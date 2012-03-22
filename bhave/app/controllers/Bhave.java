package controllers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import models.Bhaviour;
import models.Dictionary;
import models.Environment;
import models.Screenshot;
import models.Test;
import models.terms.BObject;
import models.terms.BSubject;
import models.terms.BTerm;
import models.terms.BVerb;
import play.mvc.Controller;

import bhave.*;

import com.google.gson.Gson;

public class Bhave extends Controller {
	
	public static void list() {
		List<Test> tests = Test.findAll();
		render(tests);
	}
	
	public static void getEnv() {
		Environment environment = new Environment();
		renderJSON(environment);
	}

	public static void getDictionary() {
		Dictionary dictionary = new Dictionary(BTerm.find("byTestCopyIsNull").<BTerm>fetch());
		renderJSON(dictionary, new BTermSerializer());
	}

	public static void displayDictionary() {
		Dictionary dictionary = new Dictionary(BTerm.<BTerm>findAll());
		render(dictionary);
	}

	public static void dummy() {
		renderText("");
	}
}
