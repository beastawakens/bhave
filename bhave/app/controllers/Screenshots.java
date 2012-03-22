package controllers;

import java.io.InputStream;

import models.Screenshot;
import models.Test;
import play.mvc.Controller;

public class Screenshots extends Controller {

	public static void save(Screenshot screenshot) {
		screenshot.save();
		Test test = Test.findById(screenshot.testId);
		if (test != null) {
			test.screenshots.add(screenshot.id);
			test = test.merge();
			test.save();
			renderJSON(screenshot);
		} else {
			notFound();
		}
	} 
	
	public static void delete(Long testId, Long id) {
		Screenshot screenshot = Screenshot.findById(id);
		Test test = Test.findById(testId);
		if (screenshot != null && test != null) {
			screenshot.source.getFile().delete();
			screenshot.delete();
			test.screenshots.remove(id);
			test = test.merge();
			test.save();
			ok();
		} else {
			notFound();
		}
	}

	public static void load(Long testId, Long id) { 
		Screenshot screenshot = Screenshot.findById(id);
		if (screenshot != null) {
			response.setContentTypeIfNotSet(screenshot.source.type());
			InputStream binaryData = screenshot.source.get();
			renderBinary(binaryData);
		} else {
			notFound();
		}
	} 

	public static void getName(long id) { 
		final Screenshot screenshot = Screenshot.findById(id); 
		renderText(screenshot.name);
	}
}
