package controllers;

import java.util.*;

import models.*;
import models.Dictionary;
import models.terms.*;
import play.mvc.*;
import bhave.*;

public class Bhave extends Controller {
	
	public static void list() {
		List<Bhaviour> bhaviours = Bhaviour.findAll();
		render(bhaviours);
	}
	
	public static void getEnv() {
		Environment environment = new Environment();
		renderJSON(environment);
	}

	public static void getDictionary() {
		Dictionary dictionary = new Dictionary(BTerm.find("byTestCopyIsNull").<BTerm>fetch());
		renderJSON(dictionary, new BTermSerializer());
	}

	public static void dictionary() {
		Dictionary dictionary = new Dictionary(BTerm.<BTerm>findAll());
		render(dictionary);
	}

	public static void dummy() {
		renderText("");
	}
}
