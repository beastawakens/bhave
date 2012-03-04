package jobs;

import models.terms.BTerm;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class DataLoader extends Job {
	
	public void doJob() {
		for (BTerm term : BTerm.find("byTestCopyIsNull").<BTerm>fetch()) {
			term.delete();
		}
		Fixtures.loadModels("default-dictionary.yml");
    }

}
