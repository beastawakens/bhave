package functional;

import static org.hamcrest.CoreMatchers.is;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Bhaviour;
import models.Screenshot;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ScreenshotsTest extends FunctionalTest {
	
	@Before
	public void setUp() {
		Fixtures.deleteAllModels();
	}
	
	@Test
	public void passingAScreenshotToTheControllerStoresIt() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, File> files = new HashMap<String, File>();
		models.Test test = new models.Test("", new ArrayList<Long>(), new ArrayList<Bhaviour>());
		test.save();
		String screenshotName = "name";
		String screenshotTestId = test.id.toString();
		String screenshotBlob = "blob";

		parameters.put("screenshot.name", screenshotName);
		parameters.put("screenshot.testId", screenshotTestId);
		parameters.put("screenshot.source", screenshotBlob);
		
		POST("/@bhave/screenshot", parameters, files);
		
		Screenshot screenshot = Screenshot.find("byName", screenshotName).first();
		
		assertNotNull(screenshot);
		assertThat(screenshot.testId, is(Long.valueOf(screenshotTestId)));
	}
	
	@Test
	public void savingAScreenShotStoresTheIdInTheTest() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, File> files = new HashMap<String, File>();
		
		models.Test test = new models.Test("", new ArrayList<Long>(), new ArrayList<Bhaviour>());
		test.save();
		String screenshotName = "storeMyId";
		String screenshotTestId = test.id.toString();
		String screenshotBlob = "blob";
		
		parameters.put("screenshot.name", screenshotName);
		parameters.put("screenshot.testId", screenshotTestId);
		parameters.put("screenshot.source", screenshotBlob);
		
		Response response = POST("/@bhave/screenshot", parameters, files);
		
		test.refresh();
		Screenshot screenshot = Screenshot.find("byName", screenshotName).first();
		
		assertNotNull(screenshot);
		assertTrue(test.screenshots.contains(screenshot.id));
	}
	
	@Test
	public void validTestInTheScreenshotReturnsTheScreenshotAsJson() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, File> files = new HashMap<String, File>();
		
		models.Test test = new models.Test("", new ArrayList<Long>(), new ArrayList<Bhaviour>());
		test.save();
		String screenshotName = "storeMyId";
		String screenshotTestId = test.id.toString();
		String screenshotBlob = "blob";
		
		parameters.put("screenshot.name", screenshotName);
		parameters.put("screenshot.testId", screenshotTestId);
		parameters.put("screenshot.source", screenshotBlob);
		
		Response response = POST("/@bhave/screenshot", parameters, files);

		assertIsOk(response);
		assertContentType("application/json", response);
    	Gson gson = new Gson();
    	Screenshot screenshot;
    	try {
    		screenshot = gson.fromJson(getContent(response), Screenshot.class);
    		assertThat(screenshot.name, is(screenshotName));
    		assertThat(screenshot.testId, is(Long.valueOf(screenshotTestId)));
    	} catch (JsonSyntaxException jse) {
    		fail();
    	}
	}
	
	@Test
	public void invalidTestInTheScreenshotReturnsNotFound() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, File> files = new HashMap<String, File>();
		
		String screenshotName = "storeMyId";
		String screenshotTestId = "0";
		String screenshotBlob = "blob";
		
		parameters.put("screenshot.name", screenshotName);
		parameters.put("screenshot.testId", screenshotTestId);
		parameters.put("screenshot.source", screenshotBlob);
		
		Response response = POST("/@bhave/screenshot", parameters, files);
		
		assertIsNotFound(response);
	}
	
	@Test
	public void tryingToDeleteAnInvalidScreenshotReturnsNotFound() throws Exception {
		models.Test test = new models.Test("", new ArrayList<Long>(), new ArrayList<Bhaviour>());
		test.save();
		String testId = String.valueOf(test.id);
		String screenshotId = String.valueOf(Long.MAX_VALUE);
		Response response = DELETE("/@bhave/screenshot/"+testId+"/"+screenshotId);
		
		assertIsNotFound(response);
	}
	
	@Test
	public void tryingToDeleteAScreenshotFromAnInvalidTestReturnsNotFound() throws Exception {
		Screenshot screenshot = new Screenshot();
		screenshot.save();
		
		String testId = String.valueOf(Long.MAX_VALUE);
		String screenshotId = String.valueOf(screenshot.id);
		Response response = DELETE("/@bhave/screenshot/"+testId+"/"+screenshotId);
		
		assertIsNotFound(response);
		
	}
	
	@Ignore
	@Test // TODO: got to work out how to test this properly :P
	public void deletingAScreenshotDeletesTheFile() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, File> files = new HashMap<String, File>();
		
		models.Test test = new models.Test("", new ArrayList<Long>(), new ArrayList<Bhaviour>());
		test.save();
		String screenshotName = "storeMyBlob";
		String screenshotTestId = test.id.toString();
		String screenshotBlob = "blob";
		
		parameters.put("screenshot.name", screenshotName);
		parameters.put("screenshot.testId", screenshotTestId);
		parameters.put("screenshot.source", screenshotBlob);

		File file = File.createTempFile("testImage", "tmp");
		
		System.out.println(file.exists());
		
		files.put("blob", file);
		
		Response response = POST("/@bhave/screenshot", parameters, files);

		System.out.println(response.status);
		
		Screenshot screenshot = Screenshot.find("byName", screenshotName).first();
	
		System.out.println(screenshot.source.exists());
		
		
		File retrievedFile = screenshot.source.getFile();
		assertTrue(retrievedFile.exists());
	}
	
	@Test
	public void deletingAScreenshotJustWorks() throws Exception {
		
	}
	
	@Test
	public void deletingAScreenshotRemovesItsIdFromItsTest() throws Exception {
		
	}

}
