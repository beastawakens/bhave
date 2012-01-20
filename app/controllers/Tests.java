package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Bhaviour;
import models.Screenshot;
import models.Test;

import com.google.gson.Gson;

import play.mvc.Controller;

public class Tests extends Controller {
	
	public static void list() {
		List<Test> tests = Test.findAll();
		render(tests);
	}
	
	public static void newTest() {
		render();
	}
	
	public static void save(String body) {
		System.out.println(body);
		Test test = new Gson().fromJson(body, Test.class);
		if (test.id != null) {
			test = test.merge();
		}
		test.save();
		renderText(test.id);
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
	
	public static void deleteTest(long id) {
		Test test = Test.findById(id);
		if (test != null) {
			test.delete();
			ok();
		} else {
			notFound();
		}
	}

	public static void loadTest(long id) {
		Test test = Test.findById(id);
		renderJSON(test);
	}

}
