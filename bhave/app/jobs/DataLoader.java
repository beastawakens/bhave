package jobs;

import models.terms.BTerm;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class DataLoader extends Job {
	
	public void doJob() {
        if(BTerm.count() == 0) {
            Fixtures.loadModels("default-dictionary.yml");
        }
    }

}
