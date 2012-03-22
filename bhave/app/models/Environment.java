package models;

import java.util.List;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Environment extends BhaveModel {

	public String driverServer = "http://localhost:9001/wd/hub";
	public String[] availableBrowsers = {"firefox", "chrome", "opera", "internet explorer", "android"};
	public String[] availablePlatforms = {"ANY", "LINUX", "WINDOWS"};
	public String driverBrowserName = "";
	public String driverVersion = "";
	public String driverPlatform = "";
	public Boolean driverJavascriptEnabled = true;
	
	
}
