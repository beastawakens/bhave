<script>
	function forElementToBePresent(findBy) { 
		return function() { 
			return myTest.driver.isElementPresent(findBy); 
		}; 
	}
	
	function toBe(input) {
		return input;
	}
	
	function forElementAttribute(element, attribute, requiredValue) {
		return function() {
			return element.getAttribute(attribute).then(function(currentValue) {
				return currentValue == requiredValue;
			});
		};
	}
	
	function forTitle(requiredTitle) {
		return function() {
			return myTest.driver.getTitle().then(function(currentTitle) {
				return currentTitle == requiredTitle;
			});
		}; 
	}
</script>