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
import models.terms.BVerb;
import play.mvc.Controller;

import com.google.gson.Gson;

public class Bhave extends Controller {
	
	public static void index() {
		redirect("Tests.list");
	}

	public static void getEnv() {
		Environment environment = new Environment();
		renderJSON(environment);
	}

	public static void getDictionary() {
		Dictionary dictionary = new Dictionary();
		renderJSON(dictionary);
	}

	public static void dummy() {
		renderText("");
	}
}
