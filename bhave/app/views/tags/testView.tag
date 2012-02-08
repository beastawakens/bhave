<div class="glow">
	<h3>
		<span data-bind="text: id"></span> - </span><span data-bind="text: name, event: {dblclick: editName}, visible: !editingName()"></span>
		<input id="nameEdit" data-bind="value: name, valueUpdate: 'afterkeydown', visible: editingName, hasfocus: editingName, event: {blur: editName, keypress: editName} "/>
	</h3>
	<table class="testTable">
	    <tfoot>
	    	<tr><td></td><td></td><td><input id="syntax_input" data-bind="value: syntaxInput" /></td><td><button data-bind="click: addBhaviour">Add</button></td></tr>
	    </tfoot>
	    <tbody data-bind="foreach: bhaviours">
	        <tr class="bhaviour_row" id="bhaviour_">
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
		            	<a data-bind="attr: {'id': 'term_delete_'+id()}, click: myTest.removeTerm.bind($data, id(), $parent.id())" class="termDelete"><img class="termDeleteImage" src="@{'/public/images/closeSmall.png'}"></a>
	            	</span>
	            </td>
	            <td class="languageCell">
	            	<input size="30" class="language_input" data-bind="value: language, attr: {id: 'language_input_'+id(), 'data-id': id()}" />
	            </td>
	            <td>
	            	<input size="30" class="command_input" data-bind="value: command, attr: {id: 'command_input_'+id(), 'data-id': id()}" />
	            </td>
	            <td>
	            	<button data-bind="click: $root.removeBhaviour">Remove</button>
	            </td>
	        </tr>    
	    </tbody>
	</table>
	<button data-bind="click: run">Run Test</button>
	<button data-bind="click: saveTest">Save Test</button>
</div>