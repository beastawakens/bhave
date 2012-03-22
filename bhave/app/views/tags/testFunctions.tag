<script>

	function Test() {

		var myTest = this;

		myTest.syntaxInput = ko.observable();

		myTest.syntaxInput.subscribe(function(termId) {
			if (termId != '') {
			    $.get(getTermUrl({id: termId}), function(termData) {
			    	var term = ko.mapping.fromJS(termData);
					myTest.syntax.push(term);
				});
			    myTest.syntaxInput('');
			}
		});

		myTest.removeTerm = function(termId) {
			myTest.syntax.remove(function(item) {
				return item.id() == termId;
			});
		}

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
			$.getJSON("@{Bhaviours.create()}", function(bhaviourJson) {
		    	var bhaviour = new Bhaviour(bhaviourJson);
				myTest.bhaviours.push(bhaviour);
				applyTranslation();
		    });
		}

		myTest.running = ko.observable(false);
		myTest.lastSuccess = ko.observable(0);
		myTest.failureMessage = ko.observable();
		
		myTest.run = function() {
			myTest.failedTerms([]);
			myTest.passingTerms([]);
			myTest.getDriver();
			myTest.runBhaviours();
		}
		
		myTest.saveScreenshot = function(dataURI) {
			var blob = dataURItoBlob(dataURI);
			var fd = new FormData(document.forms[0]);
			var xhr = new XMLHttpRequest();
			fd.append("screenshot.source", blob);
			fd.append("screenshot.name", myTest.name() + ' - ' + new Date().toUTCString() );
			fd.append("screenshot.testId", myTest.id());
			xhr.open('POST', '@{Screenshots.save()}', false);
			xhr.send(fd);
			
			if (xhr.status === 200) {
				return xhr.responseText;
			} else {
				alert('Something went wrong saving screenshot')
				return '';
			}
		}
		
		myTest.deleteScreenshot = function(id) {
			$.ajax(deleteScreenshotUrl({testId: myTest.id(), id: id}), {
				type: "DELETE",
				success: function() {
					$('#screenshotSmall_'+id).fadeOut('slow', function() {
						$('#screenshotSmall_'+id).remove();
						$('#'+id).remove();
						myTest.screenshots.remove(id);
					});
				}
			});
		}
		
		myTest.saveTest = function() {
			myTest.saving(true);
			var mapping = {
			    'ignore': [ "isActive",
						    "definition",
						    "driverServer",
						    "driverVersion",
						    "driverPlatform",
						    "driverJavascriptEnabled",
						    "availableBrowsers",
						    "availablePlatforms",
						    "driverBrowserName",
						    "language",
						    "command"
						  ]
			}
		
			var unmapped = ko.mapping.toJSON(myTest, mapping);
			
			$.ajax("@{Tests.save}", {
				data: unmapped,
				type: "post", contentType: "application/json",
				success: function(result) { myTest.id(result); myTest.saving(false); }
			});
		};
		myTest.saving = ko.observable(false);
		   
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
		
		myTest.runBhaviours = function() {
			var bhaviourString = "";
			bhaviourString += new Translator(myTest.syntax()).produceCommand();
			var exec = new Function(bhaviourString);
			exec();
		};

		myTest.failedTerms = ko.observableArray();

		myTest.fail = function(termId) {
			myTest.lastSuccess(TestState.FAIL);
			myTest.failedTerms.push(termId);
		}

		myTest.passingTerms = ko.observableArray();

		myTest.pass = function(termId) {
			myTest.passingTerms.push(termId);
		}

		myTest.language = ko.observable("");
		myTest.command = ko.observable("");
		myTest.isActive = ko.observable(true);
		myTest.definition = {
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
										name: ko.observable(myTest.language()),
										type: ko.observable('Object'),
										testCopy: ko.observable(false),
										objectType: ko.observable('Value'),
										value: ko.observable(myTest.definition.terms.object.types.value.value())
									}
								myTest.definition.terms.object.types.value.value(myTest.definition.saveTerm(term));
							}
						},
						page: {
							url: ko.observable("http://"),
							active:ko.observable(false),
							add: function() {
								var term = {
										name: ko.observable(myTest.language()),
										type: ko.observable('Object'),
										testCopy: ko.observable(false),
										objectType: ko.observable('Page'),
										value: ko.observable(myTest.definition.terms.object.types.page.url())
									}
								myTest.definition.terms.object.types.value.value(myTest.definition.saveTerm(term));
							}
						},
						element: {
							active: ko.observable(false),
							defaultValue: 'myTest.driver.isElementPresent(webdriver.By.~~identifier~~(\'~~value~~\')).then(function(result){if(result){myTest.pass(~~id~~);}else{myTest.fail(~~id~~);}});myTest.driver.findElement(webdriver.By.~~identifier~~(\'~~value~~\'))',
							makeTerm: function(value) {
								return {
									name: ko.observable(myTest.language()),
									type: ko.observable('Object'),
									testCopy: ko.observable(false),
									objectType: ko.observable('Element'),
									value: ko.observable(value)
								}
							},
							addTerm: function(identifier) {
								var value = myTest.definition.terms.object.types.element.defaultValue.replace(/~~identifier~~/g, identifier);
								value = value.replace(/~~value~~/g, myTest.definition.terms.object.types.element.identifier[identifier].value());
								var term = myTest.definition.terms.object.types.element.makeTerm(value);
								myTest.definition.terms.object.types.element.identifier[identifier].value(myTest.definition.saveTerm(term));
							},
							makeActive: function(identifier) {
								if (myTest.definition.terms.object.types.element.identifier[identifier].active()) {
									return;
								};
								myTest.definition.terms.object.types.element.makeUnactive();
								myTest.definition.terms.object.types.element.identifier[identifier].active(true);
							},
							makeUnactive: function() {
								for (identifier in myTest.definition.terms.object.types.element.identifier) {
									myTest.definition.terms.object.types.element.identifier[identifier].active(false);
								}
							},
							identifier: {
								id: {
									active: ko.observable(false),
									value: ko.observable(),
									add: function() {
										myTest.definition.terms.object.types.element.addTerm('id');
									}
								},
								name: {
									active: ko.observable(false),
									value: ko.observable(),
									add: function() {
										myTest.definition.terms.object.types.element.addTerm('name');
									}
								},
								className: {
									active: ko.observable(false),
									value: ko.observable(),
									add: function() {
										myTest.definition.terms.object.types.element.addTerm('className');
									}
								},
								css: {
									active: ko.observable(false),
									value: ko.observable(),
									add: function() {
										myTest.definition.terms.object.types.element.addTerm('css');
									}
								},
								js: {
									active: ko.observable(false),
									value: ko.observable(),
									add: function() {
										myTest.definition.terms.object.types.element.addTerm('js');
									}
								},
								linkText: {
									active: ko.observable(false),
									value: ko.observable(),
									add: function() {
										myTest.definition.terms.object.types.element.addTerm('linkText');
									}
								},
								partialLinkText: {
									active: ko.observable(false),
									value: ko.observable(),
									add: function() {
										myTest.definition.terms.object.types.element.addTerm('partialLinkText');
									}
								},
								tagName: {
									active: ko.observable(false),
									value: ko.observable(),
									add: function() {
										myTest.definition.terms.object.types.element.addTerm('tagName');
									}
								},
								xpath: {
									active: ko.observable(false),
									value: ko.observable(),
									add: function() {
										myTest.definition.terms.object.types.element.addTerm('xpath');
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
						if (myTest.definition.terms.object.types[type].active()) {
							return;
						};
						myTest.definition.terms.object.makeUnactive();
						myTest.definition.terms.object.types[type].active(true);
					},
					makeUnactive: function() {
						myTest.definition.terms.object.types.element.makeUnactive();
						for (type in myTest.definition.terms.object.types) {
							myTest.definition.terms.object.types[type].active(false);
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
				if (myTest.definition.terms[term].active()) {
					return;
				};
				myTest.definition.makeUnactive();
				myTest.definition.active(true);
				myTest.definition.terms[term].active(true);
			},
			makeUnactive: function() {
				myTest.definition.terms.object.makeUnactive();
				for (term in myTest.definition.terms) {
					myTest.definition.terms[term].active(false);
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
				myTest.definition.makeUnactive();
				myTest.definition.active(false);
			},
			saveTerm: function(term) {
				$.ajax("@{Terms.save}", {
					data: ko.mapping.toJSON(term),
					type: "post", contentType: "application/json",
					success: function(termId) {
						term.id = ko.observable(termId);
						myDictionary.terms.push(term);
						myTest.definition.makeUnactive();
						myTest.definition.active(false);
						myTest.isActive(true);
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