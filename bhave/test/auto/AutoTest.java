package auto;

import java.net.*;
import java.util.*;
import java.util.concurrent.*;

import jobs.*;


import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.*;

import bhave.*;

import play.*;
import play.test.*;

public class AutoTest extends FunctionalTest {

	private static final long SELENIUM_TIMEOUT_IN_SECONDS = Long.valueOf(Play.configuration.getProperty("bhave.test.maxTime", "300"));
	private RemoteWebDriver webdriver;
	
//	@BeforeClass
//	public static void startServer() {
//		SeleniumHolder.server.
//	}
//	
//	@AfterClass
//	public static void stopServer() {
//		new SeleniumServerStop().stop();
//	}

	@Test
    public void autoTest() throws MalformedURLException {
    	Capabilities desiredCapabilities = new DesiredCapabilities("firefox", "", Platform.ANY);
		
		String seleniumServerUrl = Play.configuration.getProperty("bhave.selenium.server", "http://localhost:9001/wd/hub");
		
		CommandExecutor executor = new HttpCommandExecutor(new java.net.URL(seleniumServerUrl));
		webdriver = new RemoteWebDriver(executor , desiredCapabilities);
		webdriver.manage().timeouts().implicitlyWait(SELENIUM_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
		
		String protocol = "http";
		String port = "9000";
		if(Play.configuration.getProperty("https.port") != null) {
			port = Play.configuration.getProperty("https.port");
			protocol = "https";
		} else if(Play.configuration.getProperty("http.port") != null) {
			port = Play.configuration.getProperty("http.port");
		}
		
		List<models.Test> tests = models.Test.findAll();
		Map<String, String> results = new HashMap<String, String>();
		
		Logger.info("Going to check " + tests.size() + " bhaviours");

		Boolean globalResult = true;
		
		for (Iterator iterator = tests.iterator(); iterator.hasNext();) {
			models.Test test = (models.Test) iterator.next();
			webdriver.get(protocol+"://localhost:" + port+"/@bhave/"+test.id);
			WebElement runner = webdriver.findElement(By.id("runTest"));
			if (runner.isEnabled()) {
				runner.click();
				try {
					WebElement result = webdriver.findElement(By.id("testResultContainer"));
					if (result.getAttribute("class").contains("passed")) {
						results.put(test.id + ". " + test.name, "Passed");
					} else {
						results.put(test.id + ". " + test.name, "Failed - " + result.getText());
						globalResult = false;
					}
				} catch (NoSuchElementException e) {
					results.put(test.id + ". " + test.name, "Failed - Timed out");
					globalResult = false;
				}
			}
		}
		
		assertTrue(results.toString(), globalResult);
    }
}