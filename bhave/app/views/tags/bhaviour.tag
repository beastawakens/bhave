<script>

	function Bhaviour(data) {
		var self = this;
		self.id = ko.observable(data.id);
		self.syntax = ko.mapping.fromJS(data.syntax);
		self.language = ko.observable(data.language);
		self.command = ko.observable(data.command);
		self.isActive = ko.observable(true);
		self.definition = {
				active: ko.observable(false),
				terms: {
					object: {
						active: ko.observable(false),
						types: {
							value: {
								value: ko.observable(""),
								active: ko.observable(false)
							},
							page: {
								url: ko.observable("http://"),
								active:ko.observable(false)
							},
							element: {
								active:ko.observable(false),
								identifier: {
									id: {
										active: ko.observable(false),
										value: ko.observable()
									},
									name: {
										active: ko.observable(false),
										value: ko.observable()
									}
								}
							},
							pageAttribute: {
								active:ko.observable(false)
							},
							elementAttribute: {
								active:ko.observable(false)
							},
							makeActive: function(type) {
								if (self.definition.terms.object.types[type].active()) {
									return;
								};
								self.definition.terms.object.types.value.active(false);
								self.definition.terms.object.types.page.active(false);
								self.definition.terms.object.types.element.active(false);
								self.definition.terms.object.types.pageAttribute.active(false);
								self.definition.terms.object.types.elementAttribute.active(false);
								self.definition.terms.object.types[type].active(true);
							}
						}
					},
					article: {
						active:ko.observable(false)
					},
					conjunction: {
						active: ko.observable(false)
					},
					synonym: {
						active:ko.observable(false),
						terms: ko.observableArray()
					},
					makeActive: function(term) {
						if (self.definition.terms[term].active()) {
							return;
						};
						self.definition.terms.object.active(false);
						self.definition.terms.article.active(false);
						self.definition.terms.conjunction.active(false);
						self.definition.terms.synonym.active(false);
						self.definition.terms[term].active(true);
					}
				},
				disable: function() {
					if (!e) var e = window.event;
					var tg = (window.event) ? e.srcElement : e.target;
					if (tg.className != 'definition_tool_container') return;
					var reltg = (e.relatedTarget) ? e.relatedTarget : e.toElement;
					while (reltg != tg && reltg.nodeName != 'BODY')
						reltg= reltg.parentNode
					if (reltg== tg) return;

					self.definition.active(false);

					// actually left area, do something
				}
			}
	}


</script>