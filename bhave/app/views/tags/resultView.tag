<div class="glow"
	data-bind="visible: lastSuccess() != TestState.PENDING,
	text: (lastSuccess()==TestState.SUCCESS) ? 'All Good :)' : failureMessage() ">
</div>