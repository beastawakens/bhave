package controllers;

import static org.hamcrest.CoreMatchers.*;

import java.io.*;
import java.util.*;

import models.*;
import models.terms.*;

import org.junit.*;
import org.junit.Before;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.*;

import com.google.gson.*;

public class ScreenshotsTest extends FunctionalTest {
	
	@Before
	public void setUp() {
		Fixtures.deleteAllModels();
	}
	
	@Test
	public void passingAScreenshotToTheControllerStoresIt() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, File> files = new HashMap<String, File>();
		Bhaviour bhaviour = new Bhaviour("", new ArrayList<Long>(), new ArrayList<BTerm>());
		bhaviour.save();
		String screenshotName = "name";
		String screenshotBhaviourId = bhaviour.id.toString();
		String screenshotBlob = "blob";

		parameters.put("screenshot.name", screenshotName);
		parameters.put("screenshot.bhaviourId", screenshotBhaviourId);
		parameters.put("screenshot.source", screenshotBlob);
		
		POST("/@bhave/screenshot", parameters, files);
		
		Screenshot screenshot = Screenshot.find("byName", screenshotName).first();
		
		assertNotNull(screenshot);
		assertThat(screenshot.bhaviourId, is(Long.valueOf(screenshotBhaviourId)));
	}
	
	@Test
	public void savingAScreenShotStoresTheIdInTheBhaviour() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, File> files = new HashMap<String, File>();
		
		Bhaviour bhaviour = new Bhaviour("", new ArrayList<Long>(), new ArrayList<BTerm>());
		bhaviour.save();
		String screenshotName = "storeMyId";
		String screenshotBhaviourId = bhaviour.id.toString();
		String screenshotBlob = "blob";
		
		parameters.put("screenshot.name", screenshotName);
		parameters.put("screenshot.bhaviourId", screenshotBhaviourId);
		parameters.put("screenshot.source", screenshotBlob);
		
		POST("/@bhave/screenshot", parameters, files);
		
		bhaviour.refresh();
		Screenshot screenshot = Screenshot.find("byName", screenshotName).first();
		
		assertNotNull(screenshot);
		assertTrue(bhaviour.screenshots.contains(screenshot.id));
	}
	
	@Test
	public void validBhaviourInTheScreenshotReturnsTheScreenshotAsJson() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, File> files = new HashMap<String, File>();
		
		Bhaviour bhaviour = new Bhaviour("", new ArrayList<Long>(), new ArrayList<BTerm>());
		bhaviour.save();
		String screenshotName = "storeMyId";
		String screenshotBhaviourId = bhaviour.id.toString();
		String screenshotBlob = "blob";
		
		parameters.put("screenshot.name", screenshotName);
		parameters.put("screenshot.bhaviourId", screenshotBhaviourId);
		parameters.put("screenshot.source", screenshotBlob);
		
		Response response = POST("/@bhave/screenshot", parameters, files);

		assertIsOk(response);
		assertContentType("application/json", response);
    	Gson gson = new Gson();
    	Screenshot screenshot;
    	try {
    		screenshot = gson.fromJson(getContent(response), Screenshot.class);
    		assertThat(screenshot.name, is(screenshotName));
    		assertThat(screenshot.bhaviourId, is(Long.valueOf(screenshotBhaviourId)));
    	} catch (JsonSyntaxException jse) {
    		fail();
    	}
	}
	
	@Test
	public void invalidBhaviourInTheScreenshotReturnsNotFound() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, File> files = new HashMap<String, File>();
		
		String screenshotName = "storeMyId";
		String screenshotBhaviourId = "0";
		String screenshotBlob = "blob";
		
		parameters.put("screenshot.name", screenshotName);
		parameters.put("screenshot.bhaviourId", screenshotBhaviourId);
		parameters.put("screenshot.source", screenshotBlob);
		
		Response response = POST("/@bhave/screenshot", parameters, files);
		
		assertIsNotFound(response);
	}
	
	@Test
	public void tryingToDeleteAnInvalidScreenshotReturnsNotFound() throws Exception {
		String screenshotId = String.valueOf(Long.MAX_VALUE);
		Response response = DELETE("/@bhave/screenshot/"+screenshotId);
		
		assertIsNotFound(response);
	}
	
	@Ignore
	@Test // TODO: got to work out how to test this properly :P
	public void deletingAScreenshotDeletesTheFile() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, File> files = new HashMap<String, File>();
		
		Bhaviour bhaviour = new Bhaviour("", new ArrayList<Long>(), new ArrayList<BTerm>());
		bhaviour.save();
		String screenshotName = "storeMyBlob";
		String screenshotBhaviourId = bhaviour.id.toString();
		String screenshotBlob = "blob";
		
		parameters.put("screenshot.name", screenshotName);
		parameters.put("screenshot.bhaviourId", screenshotBhaviourId);
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
	public void deletingAScreenshotRemovesItsIdFromItsBhaviour() throws Exception {
		
	}

}
