<script>
	var bhaviour, myEnv, myRunner, myEditor, myEcosystem, screenshot, myDictionary;

	var deleteScreenshotUrl = #{jsAction @Screenshots.delete(':id') /}
	var getTermUrl = #{jsAction @Terms.get(':id') /}
	var deleteTermUrl = #{jsAction @Terms.delete(':id') /}
</script>