package functional;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
		List<Test> renderedTests = (List<Test>) renderArgs("tests");
		assertNotNull(renderedTests);
		
		
	}

}
