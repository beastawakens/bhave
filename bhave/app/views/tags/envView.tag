<div class="glow">
	Server: <input size="30" data-bind="value: driverServer" /><br/>
	Browser: <select data-bind="options: availableBrowsers, value: driverBrowserName"></select>
	Version: <input size="2" data-bind="value: driverVersion()"/>
	Platform: <select data-bind="options: availablePlatforms, value: driverPlatform"></select>
	JS Enabled: <input type="checkbox" data-bind="checked: driverJavascriptEnabled"/>
	<img data-bind="visible: running()" src="@{'/public/images/running.gif'}">
	<span data-bind="visible: lastSuccess() != 0, text: (lastSuccess()==1) ? 'All Good' : 'FAIL' "></span>
</div>