<script>
	var bhaviour, myEnv, myRunner, myEditor, myEcosystem, screenshot, myDictionary;

	var deleteScreenshotUrl = #{jsAction @Screenshots.delete(':testId', ':id') /}
	var getTermUrl = #{jsAction @Terms.get(':id') /}
	var deleteTermUrl = #{jsAction @Terms.delete(':id') /}
</script>