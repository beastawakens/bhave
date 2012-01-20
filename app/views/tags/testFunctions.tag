myTest.editName = function(data, event) {
	if (event.type == 'dblclick') {
	  	myTest.editingName(true);
	}
	if (event.type == 'blur') {
		myTest.editingName(false);
	}	
	if (event.type == 'keypress') {
		if (event.keyCode == '13') {
			if (data.name() == null || data.name().trim() == '') {
				data.name('Test');
			}
			myTest.editingName(false);
		}
	}

	return true;
};

myTest.editingName = ko.observable(false);
  	
myTest.removeBhaviour = function(bhaviour) {
	myTest.bhaviours.remove(bhaviour)
}
myTest.addBhaviour = function(bhaviour) {
	myTest.bhaviours.push(new Bhaviour("", ""));
	
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
	fd.append("screenshot.name", myTest.name() + ' - ' + new Date().toUTCString() );
	fd.append("screenshot.testId", myTest.id());
	xhr.open('POST', '/@bhave/screenshot', false);
	xhr.send(fd);
	
	if (xhr.status === 200) {
		return xhr.responseText;
	} else {
		console.log('something went wrong saving screenshot')
		return '';
	}
}

myTest.deleteScreenshot = function(id) {
	console.log('deleting screenshot ' + id + ' from test ' + myTest.id());
	$.ajax("/@bhave/screenshot/"+myTest.id() +'/' + id, {
		type: "DELETE",
		success: function() {
			console.log("screenshot " + id + " removed");
			$('#screenshotSmall_'+id).fadeOut('slow', function() {
				console.log("screenshot " + id + " faded out");
				$('#screenshotSmall_'+id).remove();
				$('#'+id).remove();
				myTest.screenshots.remove(id);
			});
		}
	});
}

myTest.saveTest = function() {
	var mapping = {
	    'ignore': ["driverServer", "driverVersion", "driverPlatform", "driverJavascriptEnabled", "availableBrowsers", "availablePlatforms", "driverBrowserName"]
	}

	var unmapped = ko.mapping.toJSON(myTest, mapping);
	
	console.log(unmapped);
	$.ajax("/@bhave", {
		data: unmapped,
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
			var screenshot = ko.mapping.fromJSON(myTest.saveScreenshot('data:image/png;base64,' + png));
			myTest.screenshots.push(screenshot.id());
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