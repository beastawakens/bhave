<div>
	Server: <input size="30" data-bind="value: driverServer" /><br/>
	Browser: <select data-bind="options: availableBrowsers, value: driverBrowserName"></select>
	Version: <input size="2" data-bind="value: driverVersion()"/>
	Platform: <select data-bind="options: availablePlatforms, value: driverPlatform"></select>
	JS Enabled: <input type="checkbox" data-bind="checked: driverJavascriptEnabled"/>
	<img src="@{'/public/images/running.gif'}">
</div>

<div data-bind="visible: screenshotIds().length > 0">
	<nav>
		<ul data-bind="foreach: screenshotIds">
			<li>
				<a data-bind="attr: {href: '#'+$data}">
					<img class="screenshotThumbnail" data-bind="attr: {src: '/@bhave/screenshot/load/'+$data}"/>
				</a>
			</li>
		</ul>
	</nav>
	
	<ul data-bind="foreach: screenshotIds">
		<li data-bind="attr: {id: $data}">
			<a href="#home">
				<img class="screenshotLarge" data-bind="attr: {src: '/@bhave/screenshot/load/'+$data}"/>
			</a>		
		</li>
	</ul>
</div>

<div>
	<h3>
		<span data-bind="text: id"></span><span data-bind="visible: id() != null">. </span><span data-bind="text: name"></span>
		<button data-bind="click: editName, visible: !editingName()">edit</button>
		<input data-bind="value: name, visible: editingName()"></span>
		<button data-bind="click: saveName, visible: editingName()">save</button>
	</h3>
	<table>
	    <tfoot>
	    	<tr><td></td><td></td><td><button data-bind="click: addBhaviour">Add</button></td></tr>
	    </tfoot>
	    <tbody data-bind="foreach: bhaviours">
	        <tr>
	            <td><input size="30"  data-bind="value: language" /></td>
	            <td><input size="80"  data-bind="value: command" /></td>
	            <td><button data-bind="click: $root.removeBhaviour">Remove</button></td>
	        </tr>    
	    </tbody>
	</table>
	<button data-bind="click: run">Run Test</button>
	<button data-bind="click: saveTest">Save Test</button>
</div>