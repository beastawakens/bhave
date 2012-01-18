myTest.editName = function() {
  		myTest.editingName(true);
};
myTest.saveName = function() {
	myTest.editingName(false);
};
myTest.editingName = ko.observable(false);
  	
myTest.removeBhaviour = function(bhaviour) {
	myTest.bhaviours.remove(bhaviour)
}
myTest.addBhaviour = function(bhaviour) {
	myTest.bhaviours.push(new Bhaviour(""));
}

myTest.run = function() {
	myTest.getDriver();
	console.log("running test");
	myTest.runBhaviours();
	myTest.updateScreenshot();
}

myTest.saveScreenshot = function(dataURI) {
	var blob = dataURItoBlob(dataURI);
	var fd = new FormData(document.forms[0]);
	var xhr = new XMLHttpRequest();
	fd.append("screenshot.source", blob);
	xhr.open('POST', '/@bhave/screenshot/save', false);
	xhr.send(fd);
	
	if (xhr.status === 200) {
		return xhr.responseText;
	} else {
		console.log('something went wrong saving screenshot')
		return '';
	}
}

myTest.saveTest = function() {

	$.ajax("/@bhave", {
		data: ko.toJSON({
			id: myTest.id,
			name: myTest.name,
			bhaviours: myTest.bhaviours,
			screenshotIds: myTest.screenshotIds
		}),
		type: "post", contentType: "application/json",
		success: function(result) { myTest.id(result) }
	});
	
};
   
myTest.getDriver = function() {
   	myTest.client = new webdriver.http.CorsClient(myTest.driverServer());
   	myTest.executor = new webdriver.http.Executor(myTest.client);
	myTest.driver = webdriver.WebDriver.createSession(myTest.executor, {
		'browserName': myTest.driverBrowserName(),
		'version': myTest.driverVersion(),
		'platform': myTest.driverPlatform(),
		'javascriptEnabled': myTest.driverJavascriptEnabled()
	});
}

myTest.updateScreenshot = function() {
	if (screenshot != undefined) {
		screenshot.then(function(png) {
			console.log("making image");
			
			var id = myTest.saveScreenshot('data:image/png;base64,' + png);
			myTest.screenshotIds.push(id);
		});
	}
}

myTest.runBhaviours = function() {
	var bhaviourString = "";
	for (var i = 0; i < myTest.bhaviours().length; i++) {
		bhaviourString += myTest.bhaviours()[i].command();
	}
	console.log(bhaviourString);
	var exec = new Function(bhaviourString);
	exec();
};