<div class="definition_tool_container" data-bind="fadeVisible: definition.active(), attr: {'id': 'definitionTool_'+id()}, event: {mouseout: definition.disable}">
	<ul class="definition_object_value_container" data-bind="fadeVisible: definition.terms.object.types.value.active(), attr: {'id': 'definitionObjectValue_'+id()}">
		<li>Value: <input size="30" class="definition_object_value_input" /></li>
		<li><button>Store</button></li>
	</ul>

	<ul class="definition_object_page_container" data-bind="fadeVisible: definition.terms.object.types.page.active(), attr: {'id': 'definitionObjectPage_'+id()}">
		<li>URL: <input size="30" class="definition_object_page_input" /></li>
		<li><button>Store</button></li>
	</ul>
	
	<ul class="definition_object_container" data-bind="fadeVisible: definition.terms.object.active(), attr: {'id': 'definitionObject_'+id()}">
		<li data-bind="event: {mouseover: definition.terms.object.types.value.active.bind($data, true)}">Value</li>
		<li data-bind="event: {mouseover: definition.terms.object.types.page.active.bind($data, true)}">Page</li>
		<li>Element</li>
		<li>Element Attribute</li>
		<li>Page Attribute</li>
	</ul>

	<ul class="definition_synonym_container" data-bind="fadeVisible: definition.terms.synonym.active(), attr: {'id': 'definitionSynonym_'+id()}">
		<li>To: <input size="30" class="language_input" /></li>
		<li><button>Store</button></li>
	</ul>
	
	<ul class="definition_term_container" data-bind="attr: {'id': 'definitionTerms_'+id()}">
		<li data-bind="event: {mouseover: definition.terms.object.active.bind($data, true)}">Object</li>
		<li>Article</li>
		<li>Conjunction</li>
		<li data-bind="event: {mouseover: definition.terms.synonym.active.bind($data, true)}">
			Synonym
		</li>
	</ul>
</div>
