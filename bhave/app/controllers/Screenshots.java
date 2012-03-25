package controllers;

import java.io.InputStream;

import models.Screenshot;
import models.Bhaviour;
import play.mvc.Controller;

public class Screenshots extends Controller {

	public static void save(Screenshot screenshot) {
		screenshot.save();
		Bhaviour bhaviour = Bhaviour.findById(screenshot.bhaviourId);
		if (bhaviour != null) {
			bhaviour.screenshots.add(screenshot.id);
			bhaviour = bhaviour.merge();
			bhaviour.save();
			renderJSON(screenshot);
		} else {
			notFound();
		}
	} 
	
	public static void delete(Long id) {
		Screenshot screenshot = Screenshot.findById(id);
		if (screenshot != null) {
			screenshot.source.getFile().delete();
			screenshot.delete();
			Bhaviour bhaviour = Bhaviour.findById(screenshot.bhaviourId);
			if (bhaviour != null) {
				bhaviour.screenshots.remove(id);
				bhaviour = bhaviour.merge();
				bhaviour.save();
			}
			ok();
		} else {
			notFound();
		}
	}

	public static void load(Long id) { 
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
