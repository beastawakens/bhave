<script>
	ko.bindingHandlers.slideVisible = {
	    init: function(element, valueAccessor) {
	        var shouldDisplay = valueAccessor();
	        $(element).toggle(shouldDisplay);
	    },
	    update: function(element, valueAccessor) {
	        var shouldDisplay = valueAccessor();
	        shouldDisplay ? $(element).slideDown() : $(element).slideUp();
	    } 
	};

	ko.bindingHandlers.fadeVisible = {
	    init: function(element, valueAccessor) {
	        var shouldDisplay = valueAccessor();
	        $(element).toggle(shouldDisplay);
	    },
	    update: function(element, valueAccessor) {
	        var shouldDisplay = valueAccessor();
	        shouldDisplay ? $(element).fadeIn() : $(element).fadeOut();
	    } 
	};
</script>