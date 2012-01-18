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
		try {
			SeleniumHolder.server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
