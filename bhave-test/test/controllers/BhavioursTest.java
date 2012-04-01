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
	public void listRendersAllBhaviours() throws Exception {
		Response response = GET("/@bhave");
		
		assertIsOk(response);
		List<Bhaviour> renderedBhaviours = (List<Bhaviour>) renderArgs("bhaviours");
		assertNotNull(renderedBhaviours);
		assertThat(renderedBhaviours.size(), is(0));
		
		Bhaviour bhaviour = new Bhaviour("bhaviourName", new ArrayList<Long>(), new ArrayList<BTerm>());
		bhaviour.save();
		
		response = GET("/@bhave");
		
		assertIsOk(response);
		renderedBhaviours = (List<Bhaviour>) renderArgs("bhaviours");
		assertNotNull(renderedBhaviours);
		assertThat(renderedBhaviours.size(), is(1));
		assertThat(renderedBhaviours.get(0), is(bhaviour));
	}
	
	@Test
	public void newBhaviourCreatesABhaviourThenRedirectsToShowIt() throws Exception {
		assertThat(Bhaviour.count(), is(0l));
		
		Response response = GET("/@bhave/bhaviour/new");
		
		assertThat(Bhaviour.count(), is(1l));
		
		Long newId = ((Bhaviour)Bhaviour.findAll().get(0)).id;
		
		assertThat(response.status, is(302));
        assertThat(response.getHeader("location"), is("/@bhave/"+newId));
	}
	
	@Test
	public void saveTurnsJsonIntoNewBhaviourAndCreates() throws Exception {
		Bhaviour bhaviour = new Bhaviour("newJsonBhaviour", new ArrayList<Long>(), new ArrayList<BTerm>());
		String bhaviourAsJson = new Gson().toJson(bhaviour);
		
		assertThat(Bhaviour.count(), is(0l));
		Response response = POST("/@bhave/bhaviour", "application/json", bhaviourAsJson);

		assertIsOk(response);
		assertThat(Bhaviour.count(), is(1l));

		Long newId = ((Bhaviour)Bhaviour.findAll().get(0)).id;
		assertThat(getContent(response), is(String.valueOf(newId)));
		
		assertThat(((Bhaviour)Bhaviour.findById(newId)).name, is(bhaviour.name));
	}
	
	@Test
	public void saveTurnsJsonIntoExistingBhaviourAndUpdates() throws Exception {
		Bhaviour bhaviour = new Bhaviour("updateJsonBhaviour", new ArrayList<Long>(), new ArrayList<BTerm>());
		bhaviour.save();
		
		bhaviour.name = "nameShouldBeUpdated";

		String bhaviourAsJson = new Gson().toJson(bhaviour);
		
		assertThat(Bhaviour.count(), is(1l));
		Response response = POST("/@bhave/bhaviour", "application/json", bhaviourAsJson);
		
		assertIsOk(response);
		assertThat(Bhaviour.count(), is(1l));
		
		Long existingId = ((Bhaviour)Bhaviour.findAll().get(0)).id;
		assertThat(getContent(response), is(String.valueOf(existingId)));
		
		assertThat(((Bhaviour)Bhaviour.findById(existingId)).name, is("nameShouldBeUpdated"));
	}
	
	@Test
	public void showInjectsIdAndBhaviourName() throws Exception {
		Bhaviour bhaviour = new Bhaviour("showBhaviourName", new ArrayList<Long>(), new ArrayList<BTerm>());
		bhaviour.save();
		
		GET("/@bhave/"+bhaviour.id);
		
		assertThat((Long)renderArgs("bhaviourId"), is(bhaviour.id));
		assertThat((String)renderArgs("bhaviourName"), is(bhaviour.name));
	}
	
	@Test
	public void deleteThrowsNotFoundForInvalidId() throws Exception {
		Response response = DELETE("/@bhave/bhaviour/"+Long.MAX_VALUE);
		assertIsNotFound(response);
	}
	
	@Test
	public void deleteWorksAndReturnsSuccessForValidId() throws Exception {
		Bhaviour bhaviour = new Bhaviour("shouldBeDeleted", new ArrayList<Long>(), new ArrayList<BTerm>());
		bhaviour.save();
		
		assertThat(Bhaviour.count(), is(1l));
		Response response = DELETE("/@bhave/bhaviour/"+bhaviour.id);
		assertThat(Bhaviour.count(), is(0l));
		
		assertIsOk(response);
	}
	
	@Test
	public void getReturnsBhaviourAsJson() throws Exception {
		Bhaviour bhaviour = new Bhaviour("shouldBeJson", new ArrayList<Long>(), new ArrayList<BTerm>());
		bhaviour.save();
		
		String bhaviourAsJson = new Gson().toJson(bhaviour);
		
		Response response = GET("/@bhave/bhaviour/"+bhaviour.id);
		
		assertIsOk(response);
		
		assertThat(getContent(response), is(bhaviourAsJson));
	}
}
