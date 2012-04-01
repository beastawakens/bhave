package jobs;

import java.util.Map;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import bhave.SeleniumHolder;

import play.*;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.vfs.VirtualFile;

@OnApplicationStart
public class SeleniumServerStart extends Job {

	public void doJob() {
		Boolean localServerEnabled = Boolean.parseBoolean(Play.configuration.getProperty("bhave.local.selenium.server.on", "true"));
		
		if (localServerEnabled) {
			Logger.info("Local Selenium server enabled...");
			start();
		}
	}

	public void start() {
		String defaultChromeDriverPath = Play.modules.get("bhave").getRealFile().getAbsolutePath() + "/chromedriver";
		
		System.setProperty("webdriver.chrome.driver", Play.configuration.getProperty("bhave.chrome.driver.path", defaultChromeDriverPath));
		Logger.info("Chromedriver path set to: " + System.getProperty("webdriver.chrome.driver"));
		try {
			RemoteControlConfiguration configuration = new RemoteControlConfiguration();
			configuration.setPort(Integer.parseInt(Play.configuration.getProperty("bhave.local.selenium.server.port", "4444"), 10));
			Logger.info("Local Selenium server being started on port " + configuration.getPort());
			SeleniumHolder.server = new SeleniumServer(configuration);
			SeleniumHolder.server.boot();
			SeleniumHolder.server.start();
		} catch (Exception e) {
			Logger.error("Something went wrong starting Selenium server", e);
		}
	}
}
