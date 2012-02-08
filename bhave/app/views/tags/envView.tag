<div class="glow">
	Server: <input size="30" data-bind="value: driverServer" /><br/>
	Browser: <select data-bind="options: availableBrowsers, value: driverBrowserName"></select>
	Version: <input size="2" data-bind="value: driverVersion()"/>
	Platform: <select data-bind="options: availablePlatforms, value: driverPlatform"></select>
	JS Enabled: <input type="checkbox" data-bind="checked: driverJavascriptEnabled"/>
	<span data-bind="visible: lastSuccess() != TestState.PENDING, text: (lastSuccess()==TestState.SUCCESS) ? 'All Good' : failureMessage() "></span>
</div>

<div data-bind="visible: running()" class="throbber">
	<img src="@{'/public/images/throbber.gif'}">
</div>