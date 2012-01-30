package controllers;

import models.terms.BTerm;
import play.mvc.Controller;

public class Terms extends Controller {
	
	public static void get(Long id) {
		BTerm term = BTerm.findById(id);
		if (term != null) {
			renderJSON(term);
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

}
