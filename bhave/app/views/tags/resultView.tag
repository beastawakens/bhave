<div id="bhaviourResultContainer" class="glow"
	data-bind="visible: lastSuccess() != BhaviourState.PENDING,
	text: (lastSuccess()==BhaviourState.SUCCESS) ? 'All Good :)' : failureMessage(),
	class: {
		'passed' : lastSuccess()==BhaviourState.SUCCESS,
		'failed' : lastSuccess()!=BhaviourState.SUCCESS || lastSuccess()!=BhaviourState.PENDING
	}">
</div>