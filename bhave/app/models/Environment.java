package models;

import java.util.List;

import play.db.jpa.Model;

public class Environment extends Model {

	public String driverServer = "http://localhost:9001/wd/hub";
	public String[] availableBrowsers = {"firefox", "chrome", "opera", "internet explorer", "android"};
	public String[] availablePlatforms = {"ANY", "LINUX", "WINDOWS"};
	public String driverBrowserName = "";
	public String driverVersion = "";
	public String driverPlatform = "";
	public Boolean driverJavascriptEnabled = true;
	
	
}
