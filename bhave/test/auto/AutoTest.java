package auto;

import java.net.*;
import java.util.*;
import java.util.concurrent.*;

import models.*;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.*;

import play.*;
import play.test.*;

public class AutoTest extends FunctionalTest {

	private static final long SELENIUM_TIMEOUT_IN_SECONDS = Long.valueOf(Play.configuration.getProperty("bhave.test.maxTime", "300"));
	private RemoteWebDriver webdriver;
	
	@Test
    public void autoTest() throws MalformedURLException {
    	Capabilities desiredCapabilities = new DesiredCapabilities("firefox", "", Platform.ANY);
		
		String seleniumServerUrl = getSeleniumServerUrl();
		
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
		
		List<Bhaviour> bhaviours = Bhaviour.findAll();
		Map<String, String> results = new HashMap<String, String>();
		
		Logger.info("Going to check " + bhaviours.size() + " bhaviours");

		Boolean globalResult = true;
		
		for (Iterator iterator = bhaviours.iterator(); iterator.hasNext();) {
			Bhaviour bhaviour = (Bhaviour) iterator.next();
			webdriver.get(protocol+"://localhost:" + port+"/@bhave/"+bhaviour.id);
			WebElement runner = webdriver.findElement(By.id("runBhaviour"));
			if (runner.isEnabled()) {
				runner.click();
				try {
					WebElement result = webdriver.findElement(By.id("bhaviourResultContainer"));
					if (result.getAttribute("class").contains("passed")) {
						results.put(bhaviour.id + ". " + bhaviour.name, "Passed");
					} else {
						results.put(bhaviour.id + ". " + bhaviour.name, "Failed - " + result.getText());
						globalResult = false;
					}
				} catch (NoSuchElementException e) {
					results.put(bhaviour.id + ". " + bhaviour.name, "Failed - Timed out");
					globalResult = false;
				}
			}
		}
		
		assertTrue(results.toString(), globalResult);
    }

	public String getSeleniumServerUrl() {
		String url;
		if (Boolean.parseBoolean(Play.configuration.getProperty("bhave.local.selenium.server.on", "true"))) {
			String port = Play.configuration.getProperty("bhave.local.selenium.server.port", "4444");
			url = "http://localhost:"+port+"/wd/hub";
		} else {
			url = Play.configuration.getProperty("bhave.remote.selenium.server");
		}
		return url;
	}
}