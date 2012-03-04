<script>
	var myTest, myEnv, myRunner, myEditor, myEcosystem, screenshot, myDictionary;

	var deleteScreenshotUrl = #{jsAction @Screenshots.deleteScreenshot(':testId', ':id') /}
	var getTermUrl = #{jsAction @Terms.get(':id') /}
</script>