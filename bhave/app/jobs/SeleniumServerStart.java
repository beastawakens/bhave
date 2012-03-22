package jobs;

import java.util.Map;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import bhave.SeleniumHolder;

import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.vfs.VirtualFile;

@OnApplicationStart
public class SeleniumServerStart extends Job {

	public void doJob() {
		Boolean localServerEnabled = Boolean.parseBoolean(Play.configuration.getProperty("bhave.local.selenium.server.on", "true"));
		
		if (localServerEnabled) {
			start();
		}
	}

	public void start() {
		String defaultChromeDriverPath = Play.modules.get("bhave").getRealFile().getAbsolutePath() + "/chromedriver";
		
		System.setProperty("webdriver.chrome.driver", Play.configuration.getProperty("bhave.chrome.driver.path", defaultChromeDriverPath));
		System.out.println("Chromedriver should be here:" + System.getProperty("webdriver.chrome.driver"));
		try {
			RemoteControlConfiguration configuration = new RemoteControlConfiguration();
			configuration.setPort(Integer.parseInt(Play.configuration.getProperty("bhave.local.selenium.server.port", "4444"), 10));
			SeleniumHolder.server = new SeleniumServer(configuration);
			SeleniumHolder.server.boot();
			SeleniumHolder.server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
