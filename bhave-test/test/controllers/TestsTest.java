package controllers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

import models.Bhaviour;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;

public class TestsTest extends FunctionalTest {
	
	@Before
	public void setUp() {
		Fixtures.deleteAllModels();
	}
	
	@Test
	public void listRendersAllTests() throws Exception {
		Response response = GET("/@bhave");
		
		assertIsOk(response);
		List<models.Test> renderedTests = (List<models.Test>) renderArgs("tests");
		assertNotNull(renderedTests);
		assertThat(renderedTests.size(), is(0));
		
		models.Test test = new models.Test("testName", new ArrayList<Long>(), new ArrayList<Bhaviour>());
		test.save();
		
		response = GET("/@bhave");
		
		assertIsOk(response);
		renderedTests = (List<models.Test>) renderArgs("tests");
		assertNotNull(renderedTests);
		assertThat(renderedTests.size(), is(1));
		assertThat(renderedTests.get(0), is(test));
	}
	
	@Test
	public void newTestCreatesATestThenRedirectsToShowIt() throws Exception {
		assertThat(models.Test.count(), is(0l));
		
		Response response = GET("/@bhave/new");
		
		assertThat(models.Test.count(), is(1l));
		
		Long newId = ((models.Test)models.Test.findAll().get(0)).id;
		
		assertThat(response.status, is(302));
        assertThat(response.getHeader("location"), is("/@bhave/"+newId));
	}
	
	@Test
	public void saveTurnsJsonIntoNewTestAndCreates() throws Exception {
		models.Test test = new models.Test("newJsonTest", new ArrayList<Long>(), new ArrayList<Bhaviour>());
		Gson gson = new Gson();
		
		String testAsJson = gson.toJson(test);
		
		assertThat(models.Test.count(), is(0l));
		Response response = POST("/@bhave", "application/json", testAsJson);

		assertIsOk(response);
		assertThat(models.Test.count(), is(1l));

		Long newId = ((models.Test)models.Test.findAll().get(0)).id;
		assertThat(getContent(response), is(String.valueOf(newId)));
		
		assertThat(((models.Test)models.Test.findById(newId)).name, is(test.name));
	}
	
	@Test
	public void saveTurnsJsonIntoExistingTestAndUpdates() throws Exception {
		models.Test test = new models.Test("updateJsonTest", new ArrayList<Long>(), new ArrayList<Bhaviour>());
		test.save();
		
		test.name = "nameShouldBeUpdated";

		Gson gson = new Gson();
		String testAsJson = gson.toJson(test);
		
		assertThat(models.Test.count(), is(1l));
		Response response = POST("/@bhave", "application/json", testAsJson);
		
		assertIsOk(response);
		assertThat(models.Test.count(), is(1l));
		
		Long existingId = ((models.Test)models.Test.findAll().get(0)).id;
		assertThat(getContent(response), is(String.valueOf(existingId)));
		
		assertThat(((models.Test)models.Test.findById(existingId)).name, is("nameShouldBeUpdated"));
	}
	
	@Test
	public void showInjectsIdAndTestName() throws Exception {
		models.Test test = new models.Test("showTestName", new ArrayList<Long>(), new ArrayList<Bhaviour>());
		test.save();
		
		GET("/@bhave/"+test.id);
		
		assertThat((Long)renderArgs("testId"), is(test.id));
		assertThat((String)renderArgs("testName"), is(test.name));
	}
	
	@Test
	public void deleteThrowsNotFoundForInvalidId() throws Exception {
		Response response = DELETE("/@bhave/test/"+Long.MAX_VALUE);
		assertIsNotFound(response);
	}
	
	@Test
	public void deleteWorksAndReturnsSuccessForValidId() throws Exception {
		models.Test test = new models.Test("shouldBeDeleted", new ArrayList<Long>(), new ArrayList<Bhaviour>());
		test.save();
		
		assertThat(models.Test.count(), is(1l));
		Response response = DELETE("/@bhave/test/"+test.id);
		assertThat(models.Test.count(), is(0l));
		
		assertIsOk(response);
	}
	
	@Test
	public void getReturnsTestAsJson() throws Exception {
		models.Test test = new models.Test("shouldBeJson", new ArrayList<Long>(), new ArrayList<Bhaviour>());
		test.save();
		
		Gson gson = new Gson();
		String testAsJson = gson.toJson(test);
		
		Response response = GET("/@bhave/test/"+test.id);
		
		assertIsOk(response);
		
		assertThat(getContent(response), is(testAsJson));
	}
}
