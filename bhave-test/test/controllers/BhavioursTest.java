package controllers;

import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import models.*;
import models.terms.*;

import org.junit.*;

import play.mvc.Http.Response;
import play.test.*;

import com.google.gson.*;

public class BhavioursTest extends FunctionalTest {
	
	@Before
	public void setUp() {
		Fixtures.deleteAllModels();
	}
	
	@Test
	public void listRendersAllTests() throws Exception {
		Response response = GET("/@bhave");
		
		assertIsOk(response);
		List<Bhaviour> renderedTests = (List<Bhaviour>) renderArgs("tests");
		assertNotNull(renderedTests);
		assertThat(renderedTests.size(), is(0));
		
		Bhaviour test = new Bhaviour("testName", new ArrayList<Long>(), new ArrayList<BTerm>());
		test.save();
		
		response = GET("/@bhave");
		
		assertIsOk(response);
		renderedTests = (List<Bhaviour>) renderArgs("tests");
		assertNotNull(renderedTests);
		assertThat(renderedTests.size(), is(1));
		assertThat(renderedTests.get(0), is(test));
	}
	
	@Test
	public void newTestCreatesATestThenRedirectsToShowIt() throws Exception {
		assertThat(Bhaviour.count(), is(0l));
		
		Response response = GET("/@bhave/new");
		
		assertThat(Bhaviour.count(), is(1l));
		
		Long newId = ((Bhaviour)Bhaviour.findAll().get(0)).id;
		
		assertThat(response.status, is(302));
        assertThat(response.getHeader("location"), is("/@bhave/"+newId));
	}
	
	@Test
	public void saveTurnsJsonIntoNewTestAndCreates() throws Exception {
		Bhaviour test = new Bhaviour("newJsonTest", new ArrayList<Long>(), new ArrayList<BTerm>());
		Gson gson = new Gson();
		
		String testAsJson = gson.toJson(test);
		
		assertThat(Bhaviour.count(), is(0l));
		Response response = POST("/@bhave", "application/json", testAsJson);

		assertIsOk(response);
		assertThat(Bhaviour.count(), is(1l));

		Long newId = ((Bhaviour)Bhaviour.findAll().get(0)).id;
		assertThat(getContent(response), is(String.valueOf(newId)));
		
		assertThat(((Bhaviour)Bhaviour.findById(newId)).name, is(test.name));
	}
	
	@Test
	public void saveTurnsJsonIntoExistingTestAndUpdates() throws Exception {
		Bhaviour test = new Bhaviour("updateJsonTest", new ArrayList<Long>(), new ArrayList<BTerm>());
		test.save();
		
		test.name = "nameShouldBeUpdated";

		Gson gson = new Gson();
		String testAsJson = gson.toJson(test);
		
		assertThat(Bhaviour.count(), is(1l));
		Response response = POST("/@bhave", "application/json", testAsJson);
		
		assertIsOk(response);
		assertThat(Bhaviour.count(), is(1l));
		
		Long existingId = ((Bhaviour)Bhaviour.findAll().get(0)).id;
		assertThat(getContent(response), is(String.valueOf(existingId)));
		
		assertThat(((Bhaviour)Bhaviour.findById(existingId)).name, is("nameShouldBeUpdated"));
	}
	
	@Test
	public void showInjectsIdAndTestName() throws Exception {
		Bhaviour test = new Bhaviour("showTestName", new ArrayList<Long>(), new ArrayList<BTerm>());
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
		Bhaviour test = new Bhaviour("shouldBeDeleted", new ArrayList<Long>(), new ArrayList<BTerm>());
		test.save();
		
		assertThat(Bhaviour.count(), is(1l));
		Response response = DELETE("/@bhave/test/"+test.id);
		assertThat(Bhaviour.count(), is(0l));
		
		assertIsOk(response);
	}
	
	@Test
	public void getReturnsTestAsJson() throws Exception {
		Bhaviour test = new Bhaviour("shouldBeJson", new ArrayList<Long>(), new ArrayList<BTerm>());
		test.save();
		
		Gson gson = new Gson();
		String testAsJson = gson.toJson(test);
		
		Response response = GET("/@bhave/test/"+test.id);
		
		assertIsOk(response);
		
		assertThat(getContent(response), is(testAsJson));
	}
}
