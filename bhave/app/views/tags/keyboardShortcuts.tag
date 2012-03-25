<script>
$("body").jkey('ctrl+s',function(){
	bhaviour.saveBhaviour();
});
$("body").jkey('ctrl+enter',function(){
	bhaviour.run();
});
$("body").jkey('ctrl+b',function(){
	console.log("here");
	window.location = "/@bhave/bhaviour/new";
});


</script>