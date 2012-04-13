<div class="glow">
	<h3>
		<span id="bhaviourId" data-bind="text: id"></span> - </span><span id="bhaviourName" data-bind="text: name, event: {dblclick: editName}, visible: !editingName()"></span>
		<input id="nameEdit" data-bind="value: name, valueUpdate: 'afterkeydown', visible: editingName, hasfocus: editingName, event: {blur: editName, keypress: editName} "/>
	</h3>
   		<section class="languageCell">
           	<input size="30" class="language_input" data-bind="hasfocus: isActive, value: language, attr: {id: 'language_input_'+id(), 'data-id': id()}" />

           	<div class="definitionContainer">
            	<i class="icon-plus-sign icon-large bhaviourDefine" data-bind="visible: isActive, attr: {'id': 'bhaviour_define_'+id()}, event: {mouseover: definition.active.bind($data, id())}"></i>
            	
            	#{definitionTool /}
           	</div>
           	<input id="syntax_input" data-bind="value: syntaxInput" />
           	<input size="30" class="command_input" data-bind="value: command, attr: {id: 'command_input_'+id(), 'data-id': id()}" />
        </section>
        <section class="bhaviour_row">
            <section class="syntaxCell" data-bind="foreach: syntax">
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
	            								'passingTerm': $root.passingTerms.indexOf(count) > -1,
	            								'failingTerm': $root.failedTerms.indexOf(count) > -1,
	            								'term': true
	            							}">
	            	</span>
	            	<i class="icon-remove-sign icon-large termDelete" data-bind="attr: {'id': 'term_delete_'+id()}, click: $root.removeTerm.bind($data, id())"></i>
            	</span>
            </section>
        </section>    
	</table>
	<button id="runBhaviour" data-bind="click: run">Run <i data-bind="css: {'icon-play': !running(), 'icon-refresh': running()}"></i></button>
	<button id="saveBhaviour" data-bind="click: saveBhaviour">Save <i data-bind="css: {'icon-file': !saving(), 'icon-refresh': saving()}"></i></button>
</div>