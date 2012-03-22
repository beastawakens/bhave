<script>

function Dictionary() {

	var self = this;

	self.deleteTerm = function(term) {
		
		$.ajax(deleteTermUrl({id: term.id()}), {
			type: "delete",
			success: function() {
				console.log('Puff!');
				self.terms.remove(term);
			},
			failure: function() {
				alert('Problem deleting term!');
			}
		});
	}
}

</script>


