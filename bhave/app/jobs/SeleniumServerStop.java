package jobs;

import java.util.Map;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import bhave.SeleniumHolder;

import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.jobs.OnApplicationStop;
import play.vfs.VirtualFile;

@OnApplicationStop
public class SeleniumServerStop extends Job {

	public void doJob() {
		Boolean localServerEnabled = Boolean.parseBoolean(Play.configuration.getProperty("bhave.local.selenium.server.on", "true"));
		
		if (localServerEnabled) {
			try {
				SeleniumHolder.server.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
