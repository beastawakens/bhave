<script>
$("body").jkey('ctrl+s',function(){
	bhaviour.saveBhaviour();
});
$("body").jkey('ctrl+enter',function(){
	bhaviour.run();
});
$("body").jkey('ctrl+i',function(){
	$(".language_input").focus();
});
$("body").jkey('ctrl+b',function(){
	window.location = "/@bhave/bhaviour/new";
});


</script>