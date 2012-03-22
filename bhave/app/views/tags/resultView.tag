<div id="testResultContainer" class="glow"
	data-bind="visible: lastSuccess() != TestState.PENDING,
	text: (lastSuccess()==TestState.SUCCESS) ? 'All Good :)' : failureMessage(),
	class: {
		'passed' : lastSuccess()==TestState.SUCCESS,
		'failed' : lastSuccess()!=TestState.SUCCESS || lastSuccess()!=TestState.PENDING
	}">
</div>