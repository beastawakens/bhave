package controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.util.List;

import models.Bhaviour;
import models.terms.BObject;
import models.terms.BTerm;
import models.terms.BObject.BObjectType;
import models.terms.BVerb;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import play.test.Fixtures;
import play.test.FunctionalTest;

import play.mvc.Http.Response;

public class TermsTest extends FunctionalTest {
	
	@Before
	public void setUp() {
		Fixtures.deleteAllModels();
	}

	@Test
	public void getReturnsNotFoundForInvalidId() throws Exception {
		Response response = GET("/@bhave/term/" + String.valueOf(Long.MAX_VALUE));
		assertIsNotFound(response);
	}
	
	@Test
	public void getReturnsTermCopyAsJsonForValidId() throws Exception {
		BObject term = new BObject("termName", BObjectType.Element, "termValue");
		term.save();
		
		Response response = GET("/@bhave/term/" + String.valueOf(term.id));
		assertIsOk(response);
		Gson gson = new Gson();
        BObject returnedTerm;
        try {
        	returnedTerm = gson.fromJson(getContent(response), BObject.class);
        	assertThat(returnedTerm.name, is("termName"));
        	assertThat(returnedTerm.value, is("termValue"));
        	assertThat(returnedTerm.objectType, is(BObjectType.Element));
        	assertThat(returnedTerm.id, is(not(term.id)));
        	assertThat(returnedTerm.testCopy, is(true));
        } catch (JsonSyntaxException jse) {
        	fail();
        }
	}
	
	@Test
	public void deleteReturnsNotFoundForInvalidId() throws Exception {
		Response response = DELETE("/@bhave/term/" + String.valueOf(Long.MAX_VALUE));
		assertIsNotFound(response);		
	}
	
	@Test
	public void deleteReturnsOkForSuccessfulDelete() throws Exception {
		BObject term = new BObject("termName", BObjectType.Element, "termValue");
		term.save();
		Long termId = term.id;

		Response response = DELETE("/@bhave/term/" + String.valueOf(termId));
		assertIsOk(response);
		
		List<BTerm> allTerms = BTerm.findAll();
		assertFalse(allTerms.contains(term));
	}
	
}
