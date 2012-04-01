package controllers;

import java.io.*;
import java.util.*;

import com.google.gson.*;

import jobs.*;

import models.*;
import models.Dictionary;
import models.terms.*;
import mx4j.log.*;
import play.Logger;
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
		Dictionary dictionary = new Dictionary(BTerm.<BTerm>findAll());
		renderJSON(dictionary, new BTermSerializer());
	}

	public static void dictionary() {
		render();
	}

	public static void dummy() {
		renderText("");
	}
	
	public static void addTerm(File[] attachments) {
		for (File file : attachments) {
			try {
				BTerm term = TermsLoader.readTermFromFile(file);
				term = term.merge();
				term.save();
			} catch (JsonSyntaxException e) {
				error(400, "Term file invalid: " + file.getName());
			} catch (FileNotFoundException e) {
				error(400, "Term file not found: " + file.getName());
			} catch (IOException e) {
				error(503, "Cannot access file: " + file.getName());
			} catch (ClassNotFoundException e) {
				error(400, "Cannot determine type of Term from file: " + file.getName());
			}
		}
		dictionary();
	}
}
