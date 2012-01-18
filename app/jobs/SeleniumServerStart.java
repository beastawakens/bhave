package jobs;

import java.util.Map;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import bhave.SeleniumHolder;

import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.vfs.VirtualFile;

@OnApplicationStart(async=true)
public class SeleniumServerStart extends Job {

	public void doJob() {
		String defaultChromeDriverPath = Play.modules.get("bhave").getRealFile().getAbsolutePath() + "/chromedriver";
		
		System.setProperty("webdriver.chrome.driver", Play.configuration.getProperty("webdriver.chrome.driver", defaultChromeDriverPath));
		try {
			RemoteControlConfiguration configuration = new RemoteControlConfiguration();
			configuration.setPort(Integer.parseInt(Play.configuration.getProperty("local.selenium.server.port", "4444"), 10));
			SeleniumHolder.server = new SeleniumServer(configuration);
			SeleniumHolder.server.boot();
			SeleniumHolder.server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
