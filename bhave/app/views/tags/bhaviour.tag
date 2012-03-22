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
								active: ko.observable(false),
								add: function() {
									var term = {
											name: ko.observable(self.language()),
											type: ko.observable('Object'),
											testCopy: ko.observable(false),
											objectType: ko.observable('Value'),
											value: ko.observable(self.definition.terms.object.types.value.value())
										}
									self.definition.terms.object.types.value.value(self.definition.saveTerm(term));
								}
							},
							page: {
								url: ko.observable("http://"),
								active:ko.observable(false),
								add: function() {
									var term = {
											name: ko.observable(self.language()),
											type: ko.observable('Object'),
											testCopy: ko.observable(false),
											objectType: ko.observable('Page'),
											value: ko.observable(self.definition.terms.object.types.page.url())
										}
									self.definition.terms.object.types.value.value(self.definition.saveTerm(term));
								}
							},
							element: {
								active: ko.observable(false),
								defaultValue: 'myTest.driver.isElementPresent(webdriver.By.~~identifier~~(\'~~value~~\')).then(function(result){if(result){myTest.pass(~~id~~);}else{myTest.fail(~~id~~);}});myTest.driver.findElement(webdriver.By.~~identifier~~(\'~~value~~\'))',
								makeTerm: function(value) {
									return {
										name: ko.observable(self.language()),
										type: ko.observable('Object'),
										testCopy: ko.observable(false),
										objectType: ko.observable('Element'),
										value: ko.observable(value)
									}
								},
								addTerm: function(identifier) {
									var value = self.definition.terms.object.types.element.defaultValue.replace(/~~identifier~~/g, identifier);
									value = value.replace(/~~value~~/g, self.definition.terms.object.types.element.identifier[identifier].value());
									var term = self.definition.terms.object.types.element.makeTerm(value);
									self.definition.terms.object.types.element.identifier[identifier].value(self.definition.saveTerm(term));
								},
								makeActive: function(identifier) {
									if (self.definition.terms.object.types.element.identifier[identifier].active()) {
										return;
									};
									self.definition.terms.object.types.element.makeUnactive();
									self.definition.terms.object.types.element.identifier[identifier].active(true);
								},
								makeUnactive: function() {
									for (identifier in self.definition.terms.object.types.element.identifier) {
										self.definition.terms.object.types.element.identifier[identifier].active(false);
									}
								},
								identifier: {
									id: {
										active: ko.observable(false),
										value: ko.observable(),
										add: function() {
											self.definition.terms.object.types.element.addTerm('id');
										}
									},
									name: {
										active: ko.observable(false),
										value: ko.observable(),
										add: function() {
											self.definition.terms.object.types.element.addTerm('name');
										}
									},
									className: {
										active: ko.observable(false),
										value: ko.observable(),
										add: function() {
											self.definition.terms.object.types.element.addTerm('className');
										}
									},
									css: {
										active: ko.observable(false),
										value: ko.observable(),
										add: function() {
											self.definition.terms.object.types.element.addTerm('css');
										}
									},
									js: {
										active: ko.observable(false),
										value: ko.observable(),
										add: function() {
											self.definition.terms.object.types.element.addTerm('js');
										}
									},
									linkText: {
										active: ko.observable(false),
										value: ko.observable(),
										add: function() {
											self.definition.terms.object.types.element.addTerm('linkText');
										}
									},
									partialLinkText: {
										active: ko.observable(false),
										value: ko.observable(),
										add: function() {
											self.definition.terms.object.types.element.addTerm('partialLinkText');
										}
									},
									tagName: {
										active: ko.observable(false),
										value: ko.observable(),
										add: function() {
											self.definition.terms.object.types.element.addTerm('tagName');
										}
									},
									xpath: {
										active: ko.observable(false),
										value: ko.observable(),
										add: function() {
											self.definition.terms.object.types.element.addTerm('xpath');
										}
									}
								}
							},
							pageAttribute: {
								active:ko.observable(false)
							},
							elementAttribute: {
								active:ko.observable(false)
							}
						},
						makeActive: function(type) {
							if (self.definition.terms.object.types[type].active()) {
								return;
							};
							self.definition.terms.object.makeUnactive();
							self.definition.terms.object.types[type].active(true);
						},
						makeUnactive: function() {
							self.definition.terms.object.types.element.makeUnactive();
							for (type in self.definition.terms.object.types) {
								self.definition.terms.object.types[type].active(false);
							}
						}
					},
					article: {
						active:ko.observable(false)
					},
					subject: {
						active:ko.observable(false)
					},
					conjunction: {
						active: ko.observable(false)
					},
					synonym: {
						active:ko.observable(false),
						terms: ko.observableArray()
					}
				},
				makeActive: function(term) {
					if (self.definition.terms[term].active()) {
						return;
					};
					self.definition.makeUnactive();
					self.definition.active(true);
					self.definition.terms[term].active(true);
				},
				makeUnactive: function() {
					self.definition.terms.object.makeUnactive();
					for (term in self.definition.terms) {
						self.definition.terms[term].active(false);
					}
				},
				disable: function() {
					if (!e) var e = window.event;
					var tg = (window.event) ? e.srcElement : e.target;
					if (tg.className != 'definition_tool_container') return;
					var reltg = (e.relatedTarget) ? e.relatedTarget : e.toElement;
					while (reltg != tg && reltg.nodeName != 'BODY'){
						reltg= reltg.parentNode;
						if (reltg== tg) return;
					}
					self.definition.makeUnactive();
					self.definition.active(false);
				},
				saveTerm: function(term) {
					$.ajax("@{Terms.save}", {
						data: ko.mapping.toJSON(term),
						type: "post", contentType: "application/json",
						success: function(termId) {
							term.id = ko.observable(termId);
							myDictionary.terms.push(term);
							self.definition.makeUnactive();
							self.definition.active(false);
							self.isActive(true);
							return '';
						},
						failure: function(error) {
							return error;
						}
					});
				}
			}
	}


</script>