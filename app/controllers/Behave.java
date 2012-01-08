package controllers;

import java.util.List;

import models.Behaviour;

import com.google.gson.JsonObject;

import behave.BehaviourEngine;

import play.mvc.Controller;
import play.mvc.results.Ok;
import play.test.TestEngine;

public class Behave extends Controller {

	public static void list() {
		List<String> behaviours = BehaviourEngine.allBehaviours();
		render(behaviours);
	}
	
	public static void newBehaviour(String name, JsonObject content) {
		BehaviourEngine.storeBehaviour(name, new Behaviour(content));
		ok();
	}
	
	public static void show(String id) {
		Behaviour behaviour = Behaviour.findById(id);
		render(behaviour);
	}
}
