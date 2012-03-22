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
					if (event.keyCode === $.ui.keyCode.TAB && $(this).data("autocomplete").menu.active) {
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
						
						response($.ui.autocomplete.filter(terms, extractLast(request.term)));
					},
					focus: function(event, ui) {
						$(element).val(ui.item.label);
						return false;
					},
					select: function(event, ui) {
						$('#syntax_input').val(ui.item.value);
						$('#syntax_input').change();
						$(this).val("");
						return false;
					}
			});
		});
	}

	function Translator(syntaxArray) {
		var self = this;
		var syntax = syntaxArray;
		var index = {
			'Article': 0,
			'Conjunction': 0,
			'Object.Page': 0,
			'Object.Element': 0,
			'Object.Attribute': 0,
			'Object.Value': 0,
			'Subject': 0,
			'Synonym': 0,
			'Verb': 0
		};

		self.produceCommand = function() {
			return self.replaceSynonyms(function() {
				var currentCommand = "";
				var verb = self.findNext('Verb');
				while (typeof verb != 'undefined') {
					currentCommand = currentCommand + self.replaceId(verb.command(), verb);
					currentCommand = self.replaceObjects(currentCommand);

					verb = self.findNext('Verb');
				}
				console.log(currentCommand);
				return currentCommand;
			});
		}

		self.replaceId = function(valueString, term) {
			return valueString.replace(/~~id~~/g, term.id());
		}

		self.replaceSynonyms = function(callback) {
			var synonym = self.findNext('Synonym');
			while (typeof synonym != 'undefined') {
				var originalIds = synonym.to();

				var preSynonymTerms = syntax.slice(0, index['Synonym']-1);
				var postSynonymTerms = syntax.slice(index['Synonym']);
				var originalTerms = [];

				for (var i = 0; i < originalIds.length; i++) {
					allTerms = myDictionary.terms();
					for (var j = 0; j < allTerms.length; j++) {
						if (allTerms[j].id() == originalIds[i]) {
							originalTerms[i] = allTerms[j];	
							originalTerms[i].id(synonym.id());					
						}
					}
				}

				syntax = preSynonymTerms.concat(originalTerms.concat(postSynonymTerms));
				
				synonym = self.findNext('Synonym');
			}

			return callback();

		}

		self.replaceObjects = function(commandString) {
			if (commandString.indexOf("~~page~~") != -1) {
				commandString = commandString.replace(/~~page~~/g, self.findObject('Page').value());
			}
			if (commandString.indexOf("~~element~~") != -1) {
				var element = self.findObject('Element');
				commandString = commandString.replace(/~~element~~/g, self.replaceId(element.value(), element));
			}
			if (commandString.indexOf("~~attribute~~") != -1) {
				commandString = commandString.replace(/~~attribute~~/g, self.findObject('Attribute').value());
			}
			if (commandString.indexOf("~~value~~") != -1) {
				commandString = commandString.replace(/~~value~~/g, self.findObject('Value').value());
			}

			return commandString;
		}

		self.findNext = function(termType) {
			for (var i = index[termType]; i < syntax.length; i++) {
				if (syntax[i].type() == termType) {
					index[termType] = i+1;
					return syntax[i]; 
				}
			}
		}

		self.findObject = function(objectType) {
			for (var i = index['Object.' + objectType]; i < syntax.length; i++) {
				if (syntax[i].type() == 'Object') {
					if (syntax[i].objectType() == objectType) {
						index['Object.' + objectType] = i;
						return syntax[i]; 
					}
				}
			}
		}


	}


</script>