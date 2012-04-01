package jobs;

import java.io.*;
import java.util.regex.*;

import org.apache.commons.io.*;
import org.apache.commons.lang.*;

import bhave.*;

import com.google.gson.*;

import models.terms.BTerm;
import play.*;
import play.db.jpa.*;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;
import play.vfs.*;

@OnApplicationStart
public class DataLoader extends Job {
	
	private static final String TERM_CLASS_PREFIX = "models.terms.B";
	private static final String TERMS_FOLDER_PREFIX = "models.terms";
	private static final String BHAVE_MODULE = "bhave";
	private static final String DICTIONARY_FOLDER = "dictionary";
	private static Gson gson = new GsonBuilder().registerTypeAdapter(BTerm.class, new BTermDeserializer()).create();
	private static final Pattern PATTERN = Pattern.compile("\"type\":\"([A-Z][a-z]*)\"");

	public void doJob() {
		if (BTerm.count() == 0) {
			Fixtures.deleteDatabase();
			for (VirtualFile module : Play.modules.values()) {
	            if (findBhaveModule(module)) {
	            	getTermFoldersFrom(module.child(DICTIONARY_FOLDER).getRealFile());
	            	break;
	            }
	        }
		}
    }

	public Boolean findBhaveModule(VirtualFile module) {
		if (!module.getRealFile().getAbsolutePath().startsWith(Play.frameworkPath.getAbsolutePath()) && module.getName().equalsIgnoreCase(BHAVE_MODULE) && module.child(DICTIONARY_FOLDER).exists()) {
			return true;
		}
		return false;
	}

	public void getTermFoldersFrom(File bhaveFolder) {
		for (File modelFolder : bhaveFolder.listFiles()) {
			if (modelFolder.getName().contains(TERMS_FOLDER_PREFIX)) {
				loadTermsFrom(modelFolder);
			}
		}
	}

	public void loadTermsFrom(File modelFolder) {
		for (File modelFile : modelFolder.listFiles()) {
			try {
				BTerm term = readTermFromFile(modelFile).merge();
				term.save();
			} catch (JsonSyntaxException e) {
				Logger.error("Can't parse term json: "+modelFile.getName(), e);
			} catch (FileNotFoundException e) {
				Logger.error("Can't find term file: "+modelFile.getName(), e);
			} catch (IOException e) {
				Logger.error("Can't access term file: "+modelFile.getName(), e);
			} catch (ClassNotFoundException e) {
				Logger.error("Can't find term type: "+modelFile.getName(), e);
			}
		}
	}
	
	public static BTerm readTermFromFile(File termFile) throws FileNotFoundException, IOException, JsonSyntaxException, ClassNotFoundException  {
		String termContents = IOUtils.toString(new FileInputStream(termFile));
		Matcher matcher = PATTERN.matcher(termContents);
		matcher.find();
		String type = matcher.group(1);
		BTerm term = (BTerm) gson.fromJson(termContents, Class.forName(TERM_CLASS_PREFIX+type));
		Logger.info("Loading term: " + type + ", " + term.name);
		return term;
	}
}
