package controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import models.Bhaviour;
import models.Screenshot;
import models.Test;
import models.terms.BObject;
import models.terms.BTerm;
import models.terms.BVerb;
import models.terms.BObject.BObjectType;

import bhave.BTermDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import play.mvc.Controller;

public class Tests extends Controller {
	
	public static void list() {
		List<Test> tests = Test.findAll();
		render(tests);
	}
	
	public static void newTest() {
		Test test = createNewTest();
		redirect("Tests.show", test.id);
	}
	
	public static void save(String body) {
		Gson gson = new GsonBuilder().registerTypeAdapter(BTerm.class, new BTermDeserializer()).create();
		Test test = gson.fromJson(body, Test.class);
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
	
	//TODO: is anything using this anymore? delete it when test coverage is good.
	public static void init() {
		Test test = createNewTest();
		renderJSON(test);
	}
	
	private static Test createNewTest() {
		Test test = new Test("New test", new ArrayList<Long>(), new ArrayList<Bhaviour>());
		
		test.bhaviours.add(new Bhaviour(new ArrayList<BTerm>(), "", "myTest.driver.get('http://www.google.com').then(function() {myTest.pass(~~id~~);}, function() {myTest.fail(~~id~~);});"));
		test.bhaviours.add(new Bhaviour(new ArrayList<BTerm>(), "", "myTest.driver.takeScreenshot().then(function(png){var screenshot=ko.mapping.fromJSON(myTest.saveScreenshot('data:image/png;base64,'+png));myTest.screenshots.push(screenshot.id());myTest.pass(~~id~~);},function(){myTest.fail(~~id~~);});"));
		test.bhaviours.add(new Bhaviour(new ArrayList<BTerm>(), "", "myTest.driver.findElement(webdriver.By.name('q')).sendKeys('bhave').then(function() {myTest.pass(~~id~~);}, function() {myTest.fail(~~id~~);});"));
		test.bhaviours.add(new Bhaviour(new ArrayList<BTerm>(), "", "myTest.driver.findElement(webdriver.By.name('btnG')).click().then(function() {myTest.pass(~~id~~);}, function() {myTest.fail(~~id~~);});"));
		test.bhaviours.add(new Bhaviour(new ArrayList<BTerm>(), "", "myTest.driver.takeScreenshot().then(function(png){var screenshot=ko.mapping.fromJSON(myTest.saveScreenshot('data:image/png;base64,'+png));myTest.screenshots.push(screenshot.id());myTest.pass(~~id~~);},function(){myTest.fail(~~id~~);});"));
		test.bhaviours.add(new Bhaviour(new ArrayList<BTerm>(), "", "myTest.driver.quit().then(function() {myTest.pass(~~id~~);}, function() {myTest.fail(~~id~~);});"));
		test.save();
		return test;
	}
	
	public static void delete(long id) {
		Test test = Test.findById(id);
		if (test != null) {
			test.delete();
			ok();
		} else {
			notFound();
		}
	}

	public static void get(long id) {
		Test test = Test.findById(id);
		renderJSON(test);
	}

}
