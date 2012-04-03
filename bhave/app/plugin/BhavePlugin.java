package plugin;

import java.io.*;

import play.*;
import play.mvc.*;
import play.mvc.Router.*;
import play.test.*;
import play.test.TestEngine.TestResults;
import play.vfs.*;

public class BhavePlugin extends PlayPlugin {

	private static final String BHAVE_MODULE = "bhave";

	@Override
	public void onApplicationStart() {
		Logger.info("Now then, now then, make sure to Bhave yourself...");
	}

	@Override
    public void onLoad() {
        for (VirtualFile module : Play.modules.values()) {
            File modulePath = module.getRealFile();
            if (!modulePath.getAbsolutePath().startsWith(Play.frameworkPath.getAbsolutePath())
            		&& module.getName().equalsIgnoreCase(BHAVE_MODULE)
            		&& !Play.javaPath.contains(module.child("test"))) {
                Play.javaPath.add(module.child("test"));
                break;
            }
        }
    }

    @Override
    public void onRoutesLoaded() {
        
        Router.addRoute("GET", "/@bhave/{id}", "Bhaviours.show");

        Router.addRoute("GET", "/@bhave/autoTest", "Bhave.autoTest");

        Router.addRoute("GET", "/@bhave/displayDictionary", "Bhave.displayDictionary");
        Router.addRoute("GET", "/@bhave/dictionary", "Bhave.getDictionary");
        Router.addRoute("GET", "/@bhave/env", "Bhave.getEnv");
        
        Router.addRoute("DELETE", "/@bhave/term/{id}", "Terms.delete");
        Router.addRoute("GET", "/@bhave/term/{id}", "Terms.get");
        Router.addRoute("POST", "/@bhave/term", "Terms.save");
        
        Router.addRoute("DELETE", "/@bhave/bhaviour/{id}", "Bhaviours.delete");
        Router.addRoute("GET", "/@bhave/bhaviour/{id}", "Bhaviours.get");
        Router.addRoute("GET", "/@bhave/bhaviour/new", "Bhaviours.create");
        Router.addRoute("POST", "/@bhave/bhaviour", "Bhaviours.save");
        
        Router.addRoute("DELETE", "/@bhave/screenshot/{id}", "Screenshots.delete");
        Router.addRoute("GET", "/@bhave/screenshot/{id}", "Screenshots.load");
        Router.addRoute("POST", "/@bhave/screenshot", "Screenshots.save");
        
        Router.addRoute("GET", "/@bhave", "Bhave.list");
    }

    @Override
    public void onApplicationReady() {
//        String protocol = "http";
//        String port = "9000";
//        if(Play.configuration.getProperty("https.port") != null) {
//            port = Play.configuration.getProperty("https.port");
//            protocol = "https";
//        } else if(Play.configuration.getProperty("http.port") != null) {
//          port = Play.configuration.getProperty("http.port");
//        }
//        System.out.println("~");
//        System.out.println("~ Go to "+protocol+"://localhost:" + port + "/@tests to run the tests");
//        System.out.println("~");
    }
}
