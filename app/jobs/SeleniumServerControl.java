package jobs;

import java.util.Map;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.vfs.VirtualFile;

@OnApplicationStart(async=true)
public class SeleniumServerControl extends Job {

	private static SeleniumServer server;

	public void doJob() {
		String defaultChromeDriverPath = Play.modules.get("bhave").getRealFile().getAbsolutePath() + "/chromedriver";
		
		System.setProperty("webdriver.chrome.driver", Play.configuration.getProperty("webdriver.chrome.driver", defaultChromeDriverPath));
		try {
			RemoteControlConfiguration configuration = new RemoteControlConfiguration();
			configuration.setPort(Integer.parseInt(Play.configuration.getProperty("local.selenium.server.port", "4444"), 10));
			server = new SeleniumServer(configuration);
			server.boot();
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
