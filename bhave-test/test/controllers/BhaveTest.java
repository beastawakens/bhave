package controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import jobs.*;

import models.Dictionary;
import models.Environment;
import models.terms.BTerm;

import org.junit.Before;
import org.junit.Test;

import bhave.BTermDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;

public class BhaveTest extends FunctionalTest {
	
	@Before
	public void setUp() {
		Fixtures.deleteAllModels();
	}

    @Test
	public void getEnvShouldReturnEnvironmentAsJson() {
    	Environment currentEnvironment = new Environment();
		Response response = GET("/@bhave/env");
		assertIsOk(response);
		assertContentType("application/json", response);
		Gson gson = new Gson();
		Environment returnedEnvironment;
		try {
			returnedEnvironment = gson.fromJson(getContent(response), Environment.class);
			assertEquals(currentEnvironment.driverBrowserName, returnedEnvironment.driverBrowserName);
			assertEquals(currentEnvironment.driverPlatform, returnedEnvironment.driverPlatform);
			assertEquals(currentEnvironment.driverServer, returnedEnvironment.driverServer);
			assertEquals(currentEnvironment.driverVersion, returnedEnvironment.driverVersion);
			assertEquals(currentEnvironment.driverJavascriptEnabled, returnedEnvironment.driverJavascriptEnabled);
			assertArrayEquals(currentEnvironment.availableBrowsers, returnedEnvironment.availableBrowsers);
			assertArrayEquals(currentEnvironment.availablePlatforms, returnedEnvironment.availablePlatforms);
		} catch (JsonSyntaxException jse) {
			fail();
		}
    }

    @Test
    public void getDictionaryShouldReturnFullDictionaryAsJson() {
    	new TermsLoader().doJob();
    	Dictionary currentDictionary = new Dictionary(BTerm.<BTerm>findAll());
    	Response response = GET("/@bhave/dictionary");
    	assertIsOk(response);
    	assertContentType("application/json", response);
    	GsonBuilder gson = new GsonBuilder();
    	gson.registerTypeAdapter(BTerm.class, new BTermDeserializer());
    	Dictionary returnedDictionary;
    	try {
    		returnedDictionary = gson.create().fromJson(getContent(response), Dictionary.class);
    		assertEquals(currentDictionary.terms, returnedDictionary.terms);
    		assertThat(returnedDictionary.terms.size(), is(17));
    	} catch (JsonSyntaxException jse) {
    		fail();
    	}
    }
    
}	