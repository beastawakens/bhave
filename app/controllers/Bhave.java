package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Bhaviour;
import models.Environment;
import models.Screenshot;
import models.Test;
import play.mvc.Controller;

import com.google.gson.Gson;

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

	public static void saveScreenshot(Screenshot screenshot) {
		System.out.println("saving screenshot: " + screenshot.locator);
		System.out.println("saving screenshot: " + screenshot.source.getUUID());
		screenshot.save();
		renderText(screenshot.id);
	} 
	
	public static void loadScreenshot(long id) { 
		final Screenshot screenshot = Screenshot.findById(id); 
		response.setContentTypeIfNotSet(screenshot.source.type());
		java.io.InputStream binaryData = screenshot.source.get();
		renderBinary(binaryData);
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
	
	public static void loadNewTest() {
		Test test = new Test("New test", new ArrayList<Long>(), new ArrayList<Bhaviour>());
		test.bhaviours.add(new Bhaviour("", "myTest.driver.get('http://www.google.com');"));
		test.bhaviours.add(new Bhaviour("", "screenshot = myTest.driver.takeScreenshot();"));
		test.bhaviours.add(new Bhaviour("", "myTest.driver.quit();"));
		test.save();
		renderJSON(test);
	}

	public static void loadTest(long id) {
		Test test = Test.findById(id);
		renderJSON(test);
	}

	public static void loadEnv() {
		Environment environment = new Environment();
		renderJSON(environment);
	}

	public static void newTest() {
		render();
	}
	
	public static void dummy() {
		renderText("");
	}
}
