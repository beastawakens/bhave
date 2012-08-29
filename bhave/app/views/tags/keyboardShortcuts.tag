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
$("body").jkey('ctrl+d',function(){
	window.location = "/@bhave/dictionary";
});
$("body").jkey('ctrl+a',function(){
	window.location = "/@bhave";
});


</script>