package controllers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import models.BObject;
import models.BSubject;
import models.BVerb;
import models.Bhaviour;
import models.Environment;
import models.Screenshot;
import models.Test;
import play.mvc.Controller;

import com.google.gson.Gson;

public class Bhave extends Controller {

	public static void loadEnv() {
		Environment environment = new Environment();
		renderJSON(environment);
	}

	public static void dummy() {
		renderText("");
	}
}
