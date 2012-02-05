package functional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;

import models.Bhaviour;
import models.Dictionary;
import models.Environment;
import models.terms.BTerm;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;

public class BhavioursTest extends FunctionalTest {
	
	@Before
	public void setUp() {
		Fixtures.deleteAllModels();
	}

    @Test
    public void createShouldReturnAnEmptyBhaviourAsJson() {
        Response response = GET("/@bhave/bhaviour/new");
        assertIsOk(response);
        Gson gson = new Gson();
        Bhaviour returnedBhaviour;
        try {
        	returnedBhaviour = gson.fromJson(getContent(response), Bhaviour.class);
        	assertThat(returnedBhaviour.command, is(""));
        	assertThat(returnedBhaviour.language, is(""));
        	assertThat(returnedBhaviour.syntax.size(), is(0));
        } catch (JsonSyntaxException jse) {
        	fail();
        }
    }
    
    @Test
	public void getShouldReturnNotFoundForInvalidId() throws Exception {
    	Response response = GET("/@bhave/bhaviour/" + String.valueOf(Long.MAX_VALUE));
    	assertIsNotFound(response);
	}
    
    @Test
	public void getShouldReturnBhaviourAsJsonForValidId() throws Exception {
    	ArrayList<BTerm> syntax = new ArrayList<BTerm>();
		String language = "english, french etc.";
		String command = "and conquer";
		Bhaviour bhaviour = new Bhaviour(syntax, language, command);
		bhaviour.save();
		Response response = GET("/@bhave/bhaviour/"+String.valueOf(bhaviour.id));
		assertIsOk(response);
		Gson gson = new Gson();
		Bhaviour returnedBhaviour;
		try {
			returnedBhaviour = gson.fromJson(getContent(response), Bhaviour.class);
			assertThat(returnedBhaviour.command, is(command));
			assertThat(returnedBhaviour.language, is(language));
			assertThat((ArrayList<BTerm>)returnedBhaviour.syntax, is(syntax));
		} catch (JsonSyntaxException jse) {
			fail();
		}
	}
}	