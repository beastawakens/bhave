<div class="definition_tool_container" data-bind="slideVisible: definition.active(), attr: {'id': 'definitionTool_'+id()}, event: {mouseout: definition.disable}">
	
	<table>
		<tr><td>
			<ul class="definition_object_element_id_container" data-bind="slideVisible: definition.terms.object.types.element.identifier.id.active(), attr: {'id': 'definitionObjectElementId_'+id()}">
				<li>Id: <input size="30" class="definition_object_element_id_input" data-bind="value: definition.terms.object.types.element.identifier.id.value" /></li>
				<li class="definitionStore"><button data-bind="click: definition.terms.object.types.element.identifier.id.add"><i class="icon-upload-alt"></i></button></li>
			</ul>
		
			<ul class="definition_object_element_name_container" data-bind="slideVisible: definition.terms.object.types.element.identifier.name.active(), attr: {'id': 'definitionObjectElementName_'+id()}">
				<li>Name: <input size="30" class="definition_object_element_name_input" data-bind="value: definition.terms.object.types.element.identifier.name.value" /></li>
				<li class="definitionStore"><button data-bind="click: definition.terms.object.types.element.identifier.name.add"><i class="icon-upload-alt"></i></button></li>
			</ul>
		
			<ul class="definition_object_element_tagName_container" data-bind="slideVisible: definition.terms.object.types.element.identifier.tagName.active(), attr: {'id': 'definitionObjectElementTagName_'+id()}">
				<li>Tag Name: <input size="30" class="definition_object_element_tagName_input" data-bind="value: definition.terms.object.types.element.identifier.tagName.value" /></li>
				<li class="definitionStore"><button data-bind="click: definition.terms.object.types.element.identifier.tagName.add"><i class="icon-upload-alt"></i></button></li>
			</ul>
		
			<ul class="definition_object_element_xpath_container" data-bind="slideVisible: definition.terms.object.types.element.identifier.xpath.active(), attr: {'id': 'definitionObjectElementXpath_'+id()}">
				<li>Xpath: <input size="30" class="definition_object_element_xpath_input" data-bind="value: definition.terms.object.types.element.identifier.xpath.value" /></li>
				<li class="definitionStore"><button data-bind="click: definition.terms.object.types.element.identifier.xpath.add"><i class="icon-upload-alt"></i></button></li>
			</ul>
		
			<ul class="definition_object_element_className_container" data-bind="slideVisible: definition.terms.object.types.element.identifier.className.active(), attr: {'id': 'definitionObjectElementClassName_'+id()}">
				<li>Class name: <input size="30" class="definition_object_element_className_input" data-bind="value: definition.terms.object.types.element.identifier.className.value" /></li>
				<li class="definitionStore"><button data-bind="click: definition.terms.object.types.element.identifier.className.add"><i class="icon-upload-alt"></i></button></li>
			</ul>
		
			<ul class="definition_object_element_css_container" data-bind="slideVisible: definition.terms.object.types.element.identifier.css.active(), attr: {'id': 'definitionObjectElementCss_'+id()}">
				<li>CSS: <input size="30" class="definition_object_element_css_input" data-bind="value: definition.terms.object.types.element.identifier.css.value" /></li>
				<li class="definitionStore"><button data-bind="click: definition.terms.object.types.element.identifier.css.add"><i class="icon-upload-alt"></i></button></li>
			</ul>
		
			<ul class="definition_object_element_js_container" data-bind="slideVisible: definition.terms.object.types.element.identifier.js.active(), attr: {'id': 'definitionObjectElementJs_'+id()}">
				<li>JS: <input size="30" class="definition_object_element_js_input" data-bind="value: definition.terms.object.types.element.identifier.js.value" /></li>
				<li class="definitionStore"><button data-bind="click: definition.terms.object.types.element.identifier.js.add"><i class="icon-upload-alt"></i></button></li>
			</ul>
		
			<ul class="definition_object_element_linkText_container" data-bind="slideVisible: definition.terms.object.types.element.identifier.linkText.active(), attr: {'id': 'definitionObjectElementLinkText_'+id()}">
				<li>Link Text: <input size="30" class="definition_object_element_linkText_input" data-bind="value: definition.terms.object.types.element.identifier.linkText.value" /></li>
				<li class="definitionStore"><button data-bind="click: definition.terms.object.types.element.identifier.linkText.add"><i class="icon-upload-alt"></i></button></li>
			</ul>
		
			<ul class="definition_object_element_partialLinkText_container" data-bind="slideVisible: definition.terms.object.types.element.identifier.partialLinkText.active(), attr: {'id': 'definitionObjectElementPartialLinkText_'+id()}">
				<li>Partial Link Text: <input size="30" class="definition_object_element_partialLinkText_input" data-bind="value: definition.terms.object.types.element.identifier.partialLinkText.value" /></li>
				<li class="definitionStore"><button data-bind="click: definition.terms.object.types.element.identifier.partialLinkText.add"><i class="icon-upload-alt"></i></button></li>
			</ul>
		
		</td></tr>
		<tr><td>
			<ul class="definition_object_value_container" data-bind="slideVisible: definition.terms.object.types.value.active(), attr: {'id': 'definitionObjectValue_'+id()}">
				<li>Value: <input size="30" class="definition_object_value_input" data-bind="value: definition.terms.object.types.value.value" /></li>
				<li class="definitionStore"><button data-bind="click: definition.terms.object.types.value.add"><i class="icon-upload-alt"></i></button></li>
			</ul>
		
			<ul class="definition_object_page_container" data-bind="slideVisible: definition.terms.object.types.page.active(), attr: {'id': 'definitionObjectPage_'+id()}">
				<li>URL: <input size="30" class="definition_object_page_input" data-bind="value: definition.terms.object.types.page.url" /></li>
				<li class="definitionStore"><button data-bind="click: definition.terms.object.types.page.add"><i class="icon-upload-alt"></i></button></li>
			</ul>
			
			<ul class="definition_object_element_container" data-bind="slideVisible: definition.terms.object.types.element.active(), attr: {'id': 'definitionObjectElement_'+id()}">
				<li data-bind="event: {mouseover: function() {definition.terms.object.types.element.makeActive('id')}}, css: {optionSelected: definition.terms.object.types.element.identifier.id.active()}">Id</li>
				<li data-bind="event: {mouseover: function() {definition.terms.object.types.element.makeActive('name')}}, css: {optionSelected: definition.terms.object.types.element.identifier.name.active()}">Name</li>
				<li data-bind="event: {mouseover: function() {definition.terms.object.types.element.makeActive('tagName')}}, css: {optionSelected: definition.terms.object.types.element.identifier.tagName.active()}">Tag</li>
				<li data-bind="event: {mouseover: function() {definition.terms.object.types.element.makeActive('xpath')}}, css: {optionSelected: definition.terms.object.types.element.identifier.xpath.active()}">Xpath</li>
				<li data-bind="event: {mouseover: function() {definition.terms.object.types.element.makeActive('css')}}, css: {optionSelected: definition.terms.object.types.element.identifier.css.active()}">CSS</li>
				<li data-bind="event: {mouseover: function() {definition.terms.object.types.element.makeActive('className')}}, css: {optionSelected: definition.terms.object.types.element.identifier.className.active()}">Class</li>
				<li data-bind="event: {mouseover: function() {definition.terms.object.types.element.makeActive('js')}}, css: {optionSelected: definition.terms.object.types.element.identifier.js.active()}">JS</li>
				<li data-bind="event: {mouseover: function() {definition.terms.object.types.element.makeActive('linkText')}}, css: {optionSelected: definition.terms.object.types.element.identifier.linkText.active()}">Link Text</li>
				<li data-bind="event: {mouseover: function() {definition.terms.object.types.element.makeActive('partialLinkText')}}, css: {optionSelected: definition.terms.object.types.element.identifier.partialLinkText.active()}">Partial Link Text</li>
			</ul>

			<ul class="definition_object_elementAttribute_container" data-bind="slideVisible: definition.terms.object.types.elementAttribute.active(), attr: {'id': 'definitionObjectElementAttribute_'+id()}">
				<li>Attribute: <input size="30" class="definition_object_elementAttribute_input" data-bind="value: definition.terms.object.types.elementAttribute.value" /></li>
				<li class="definitionStore"><button data-bind="click: definition.terms.object.types.elementAttribute.add"><i class="icon-upload-alt"></i></button></li>
			</ul>

			<ul class="definition_object_pageAttribute_container" data-bind="slideVisible: definition.terms.object.types.pageAttribute.active(), attr: {'id': 'definitionObjectPageAttribute_'+id()}">
				<li>Attribute: <input size="30" class="definition_object_pageAttribute_input" data-bind="value: definition.terms.object.types.pageAttribute.value" /></li>
				<li class="definitionStore"><button data-bind="click: definition.terms.object.types.pageAttribute.add"><i class="icon-upload-alt"></i></button></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="definition_object_container" data-bind="slideVisible: definition.terms.object.active(), attr: {'id': 'definitionObject_'+id()}">
				<li data-bind="event: {mouseover: function() {definition.terms.object.makeActive('value')}}, css: {optionSelected: definition.terms.object.types.value.active()}">Value</li>
				<li data-bind="event: {mouseover: function() {definition.terms.object.makeActive('page')}}, css: {optionSelected: definition.terms.object.types.page.active()}">Page</li>
				<li data-bind="event: {mouseover: function() {definition.terms.object.makeActive('element')}}, css: {optionSelected: definition.terms.object.types.element.active()}">Element</li>
				<li data-bind="event: {mouseover: function() {definition.terms.object.makeActive('elementAttribute')}}, css: {optionSelected: definition.terms.object.types.elementAttribute.active()}">Element Attribute</li>
				<li data-bind="event: {mouseover: function() {definition.terms.object.makeActive('pageAttribute')}}, css: {optionSelected: definition.terms.object.types.pageAttribute.active()}">Page Attribute</li>
			</ul>
		
			<ul class="definition_synonym_container" data-bind="slideVisible: definition.terms.synonym.active(), attr: {'id': 'definitionSynonym_'+id()}">
				<li>To: <input size="30" class="language_input" /></li>
				<li class="definitionStore"><button data-bind="click: definition.terms.synonym.add"><i class="icon-upload-alt"></i></button></li>
			</ul>

			<ul class="definition_article_container" data-bind="slideVisible: definition.terms.article.active(), attr: {'id': 'definitionArticle_'+id()}">
				<li class="definitionStore"><button data-bind="click: definition.terms.article.add">Article <i class="icon-upload-alt"></i></button></li>
			</ul>

			<ul class="definition_subject_container" data-bind="slideVisible: definition.terms.subject.active(), attr: {'id': 'definitionSubject_'+id()}">
				<li class="definitionStore"><button data-bind="click: definition.terms.subject.add">Subject <i class="icon-upload-alt"></i></button></li>
			</ul>

			<ul class="definition_conjunction_container" data-bind="slideVisible: definition.terms.conjunction.active(), attr: {'id': 'definitionConjunction_'+id()}">
				<li class="definitionStore"><button data-bind="click: definition.terms.conjunction.add">Conjunction <i class="icon-upload-alt"></i></button></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="definition_term_container" data-bind="attr: {'id': 'definitionTerms_'+id()}">
				<li data-bind="event: {mouseover: function() {definition.makeActive('object')}}, css: {optionSelected: definition.terms.object.active()}">Object</li>
				<li data-bind="event: {mouseover: function() {definition.makeActive('article')}}, css: {optionSelected: definition.terms.article.active()}">Article</li>
				<li data-bind="event: {mouseover: function() {definition.makeActive('subject')}}, css: {optionSelected: definition.terms.subject.active()}">Subject</li>
				<li data-bind="event: {mouseover: function() {definition.makeActive('conjunction')}}, css: {optionSelected: definition.terms.conjunction.active()}">Conjunction</li>
				<li data-bind="event: {mouseover: function() {definition.makeActive('synonym')}}, css: {optionSelected: definition.terms.synonym.active()}">Synonym</li>
			</ul>
		</td></tr>
	
	</table>
	&nbsp;
	
</div>
