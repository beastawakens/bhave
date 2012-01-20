<div>
	Server: <input size="30" data-bind="value: driverServer" /><br/>
	Browser: <select data-bind="options: availableBrowsers, value: driverBrowserName"></select>
	Version: <input size="2" data-bind="value: driverVersion()"/>
	Platform: <select data-bind="options: availablePlatforms, value: driverPlatform"></select>
	JS Enabled: <input type="checkbox" data-bind="checked: driverJavascriptEnabled"/>
	<img src="@{'/public/images/running.gif'}">
</div>

<div data-bind="visible: screenshots().length > 0">
	<nav>
		<ul class="smallScreenshots" data-bind="foreach: screenshots">
			<li data-bind="attr: {id: 'screenshotSmall_'+$data}">
				<div>
					<a data-bind="attr: {href: '#'+$data}">
						<img class="screenshotThumbnail" data-bind="attr: {src: '/@bhave/screenshot/'+$parent.id()+'/'+$data}"/>
					</a>
					<br/>
					<button data-bind="click: $parent.deleteScreenshot, text: 'Delete'"></button>
				</div>
			</li>
		</ul>
	</nav>
	
	<ul class="largeScreenshots" data-bind="foreach: screenshots">
		<li data-bind="attr: {id: $data}">
			<a href="#home">
				<img class="screenshotLarge" data-bind="attr: {src: '/@bhave/screenshot/'+$parent.id()+'/'+$data}"/>
			</a>		
		</li>
	</ul>
</div>

<div>
	<h3>
		<span data-bind="text: id"></span> - </span><span data-bind="text: name, event: {dblclick: editName}, visible: !editingName()"></span>
		<input id="nameEdit" data-bind="value: name, valueUpdate: 'afterkeydown', visible: editingName, hasfocus: editingName, event: {blur: editName, keypress: editName} "/>
	</h3>
	<table>
	    <tfoot>
	    	<tr><td></td><td></td><td><button data-bind="click: addBhaviour">Add</button></td></tr>
	    </tfoot>
	    <tbody data-bind="foreach: bhaviours">
	        <tr class="bhaviour_row" id="bhaviour_">
	            <td><input size="30"  class="language_input" data-bind="value: language" /></td>
	            <td><input size="80"  class="command_input" data-bind="value: command" /></td>
	            <td><button data-bind="click: $root.removeBhaviour">Remove</button></td>
	        </tr>    
	    </tbody>
	</table>
	<button data-bind="click: run">Run Test</button>
	<button data-bind="click: saveTest">Save Test</button>
</div>