<div class="definition_tool_container" data-bind="slideVisible: definition.active(), attr: {'id': 'definitionTool_'+id()}, event: {mouseout: definition.disable}">
	
	<table>
		<tr><td>
				<ul class="definition_object_value_container" data-bind="slideVisible: definition.terms.object.types.value.active(), attr: {'id': 'definitionObjectValue_'+id()}">
					<li>Value: <input size="30" class="definition_object_value_input" data-bind="value: definition.terms.object.types.value.value" /></li>
					<li class="definitionStore"><button data-bind="click: definition.terms.object.types.value.add"><i class="icon-upload-alt"></i></button></li>
				</ul>
			
				<ul class="definition_object_page_container" data-bind="slideVisible: definition.terms.object.types.page.active(), attr: {'id': 'definitionObjectPage_'+id()}">
					<li>URL: <input size="30" class="definition_object_page_input" /></li>
					<li class="definitionStore"><button><i class="icon-upload-alt"></i></button></li>
				</ul>
				
				<ul class="definition_object_element_container" data-bind="slideVisible: definition.terms.object.types.element.active(), attr: {'id': 'definitionObjectElement_'+id()}">
					<li>Element: <input size="30" class="definition_object_element_input" /></li>
					<li class="definitionStore"><button><i class="icon-upload-alt"></i></button></li>
				</ul>

				<ul class="definition_object_elementAttribute_container" data-bind="slideVisible: definition.terms.object.types.elementAttribute.active(), attr: {'id': 'definitionObjectElementAttribute_'+id()}">
					<li>Attribute: <input size="30" class="definition_object_elementAttribute_input" /></li>
					<li class="definitionStore"><button><i class="icon-upload-alt"></i></button></li>
				</ul>

				<ul class="definition_object_pageAttribute_container" data-bind="slideVisible: definition.terms.object.types.pageAttribute.active(), attr: {'id': 'definitionObjectPageAttribute_'+id()}">
					<li>Attribute: <input size="30" class="definition_object_pageAttribute_input" /></li>
					<li class="definitionStore"><button><i class="icon-upload-alt"></i></button></li>
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
				<li class="definitionStore"><button><i class="icon-upload-alt"></i></button></li>
			</ul>

			<ul class="definition_article_container" data-bind="slideVisible: definition.terms.article.active(), attr: {'id': 'definitionArticle_'+id()}">
				<li class="definitionStore"><button>Article <i class="icon-upload-alt"></i></button></li>
			</ul>

			<ul class="definition_conjunction_container" data-bind="slideVisible: definition.terms.conjunction.active(), attr: {'id': 'definitionConjunction_'+id()}">
				<li class="definitionStore"><button>Conjunction <i class="icon-upload-alt"></i></button></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="definition_term_container" data-bind="attr: {'id': 'definitionTerms_'+id()}">
				<li data-bind="event: {mouseover: function() {definition.terms.makeActive('object')}}, css: {optionSelected: definition.terms.object.active()}">Object</li>
				<li data-bind="event: {mouseover: function() {definition.terms.makeActive('article')}}, css: {optionSelected: definition.terms.article.active()}">Article</li>
				<li data-bind="event: {mouseover: function() {definition.terms.makeActive('conjunction')}}, css: {optionSelected: definition.terms.conjunction.active()}">Conjunction</li>
				<li data-bind="event: {mouseover: function() {definition.terms.makeActive('synonym')}}, css: {optionSelected: definition.terms.synonym.active()}">Synonym</li>
			</ul>
		</td></tr>
	
	</table>
	&nbsp;
	
</div>
