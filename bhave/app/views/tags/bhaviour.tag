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
					}
				},
				disable: function() {
					console.log('double nice');

					if (!e) var e = window.event;
					console.log(e);
					var tg = (window.event) ? e.srcElement : e.target;
					if (tg.nodeName != 'DIV') return;
					var reltg = (e.relatedTarget) ? e.relatedTarget : e.toElement;
					while (reltg != tg && reltg.nodeName != 'BODY')
						reltg= reltg.parentNode
					if (reltg== tg) return;

					// actually left area, do something
				}
			}
	}


</script>