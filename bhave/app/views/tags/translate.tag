<script>
	function split(val) {
		return val.split( /,\s*/ );
	}
	function extractLast(term) {
		return split(term).pop();
	}

	function applyTranslation() {
		$('.language_input').each(function(index) {
			var element = this;

			$(element)
				.bind("keydown", function(event) {
					if ( event.keyCode === $.ui.keyCode.TAB &&
							$(this).data("autocomplete").menu.active) {
						event.preventDefault();
					}
				})
				.autocomplete({
					minLength: 1,
					source: function(request, response) {

						var terms = [];
						
						$.each(myDictionary.terms(), function(index, term) {
							terms[index] = {label: term.name(), value: term.id()};
						});
						
						// delegate back to autocomplete, but extract the last term
						response($.ui.autocomplete.filter(terms, extractLast(request.term)));
					},
					focus: function(event, ui) {
						$(element).val(ui.item.label);
						return false;
					},
					select: function(event, ui) {
						var behaviourId = $(this).attr('data-id');
						$('#syntax_input').val(behaviourId + ':' + ui.item.value);
						$('#syntax_input').change();
						$(this).val("");
						return false;
					}
				});
			});
	}

</script>