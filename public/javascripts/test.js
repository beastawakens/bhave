  var driver = new webdriver.Builder().
      usingServer('http://localhost:4444/wd/hub').
      withCapabilities({
        'browserName': 'firefox',
        'version': '',
        'platform': 'ANY',
        'javascriptEnabled': true
      }).
      build();
      
  driver.get('http://www.google.com');
  driver.findElement(By.name('q')).sendKeys('webdriver');
  driver.findElement(By.name('btnG')).click();
  driver.getTitle().then(function(title) {
    if (title !== 'webdriver - Google Search') {
      throw new Error(
          'Expected "webdriver - Google Search", but was "' + title + '"');
    }
  });
	
