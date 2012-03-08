<div class="glow">
	<h3>
		<span id="testId" data-bind="text: id"></span> - </span><span id="testName" data-bind="text: name, event: {dblclick: editName}, visible: !editingName()"></span>
		<input id="nameEdit" data-bind="value: name, valueUpdate: 'afterkeydown', visible: editingName, hasfocus: editingName, event: {blur: editName, keypress: editName} "/>
	</h3>
	<table class="testTable">
	    <tfoot>
	    	<tr><td></td><td></td><td><input id="syntax_input" data-bind="value: syntaxInput" /></td><td><button data-bind="click: addBhaviour"><i class="icon-plus"></i></button></td></tr>
	    </tfoot>
	    <tbody data-bind="foreach: bhaviours">
	        <tr class="bhaviour_row" data-bind="attr: {'id': 'bhaviour_'+id()}">
	            <td class="syntaxCell" data-bind="foreach: syntax">
	            	<span class="termWrapper">
		            	<span data-bind="	attr: {'id': 'term_'+id()},
		            						text: name,
		            						css: {
		            								'term_Verb': type() == 'Verb',
		            								'term_Object': type() == 'Object',
		            								'term_Subject': type() == 'Subject',
		            								'term_Conjunction': type() == 'Conjunction',
		            								'term_Article': type() == 'Article',
		            								'term_Synonym': type() == 'Synonym',
		            								'passingTerm': $root.passingTerms.indexOf(id()) > -1,
		            								'failingTerm': $root.failedTerms.indexOf(id()) > -1,
		            								'term': true
		            							}">
		            	</span>
		            	<i class="icon-remove-sign icon-large termDelete" data-bind="attr: {'id': 'term_delete_'+id()}, click: myTest.removeTerm.bind($data, id(), $parent.id())"></i>
	            	</span>
	            </td>
	            <td class="languageCell">
	            	<input size="30" class="language_input" data-bind="hasfocus: isActive, value: language, attr: {id: 'language_input_'+id(), 'data-id': id()}" />

	            	<div class="definitionContainer">
		            	<i class="icon-plus-sign icon-large bhaviourDefine" data-bind="visible: isActive, attr: {'id': 'bhaviour_define_'+id()}, event: {mouseover: definition.active.bind($data, id())}"></i>
		            	
		            	#{definitionTool /}
	            	</div>
	            </td>
	            <td class="commandCell">
	            	<input size="30" class="command_input" data-bind="value: command, attr: {id: 'command_input_'+id(), 'data-id': id()}" />
	            </td>
	            <td>
	            	<button data-bind="click: $root.removeBhaviour"><i class="icon-trash"></i></button>
	            </td>
	        </tr>    
	    </tbody>
	</table>
	<button data-bind="click: run">Run <i class="icon-play"></i></button>
	<button data-bind="click: saveTest">Save <i class="icon-file"></i></button>
</div>