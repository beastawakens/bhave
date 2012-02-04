<script>

function Dictionary() {
	this.globalVerbs = {
		quit : "myTest.driver.quit()",
		get : "myTest.driver.get(~~~)",
		getCurrentUrl : "myTest.driver.getCurrentUrl()",
		goBack : "myTest.driver.goBack()",
		goForward : "myTest.driver.goForward()",
		refresh : "myTest.driver.refresh()",
		findElement : "myTest.driver.findElement(~~~)",
		clickElement : globalVerbs.findElement + ".click()",
		typeElement :  globalVerbs.findElement + ".sendKeys(~~~)"
	}
	
	this.globalSynonyms = {
		finish : globalVerbs.quit,
		open : globalVerbs.get,
		load : globalVerbs.get
	}
}



</script>


