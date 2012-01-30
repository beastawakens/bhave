package controllers;

import java.util.ArrayList;

import models.Bhaviour;
import models.terms.BTerm;
import play.mvc.Controller;

public class Bhaviours extends Controller {
	
	public static void create() {
		Bhaviour bhaviour = new Bhaviour(new ArrayList<BTerm>(), "", "");
		bhaviour.save();
		renderJSON(bhaviour);
	}
	
	public static void get(long id) {
		Bhaviour bhaviour = Bhaviour.findById(id);
		if (bhaviour != null) {
			renderJSON(bhaviour);
		} else {
			notFound();
		}
	}

}
