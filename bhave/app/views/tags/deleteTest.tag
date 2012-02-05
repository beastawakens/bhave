<script>
function deleteTest(id) {
	var deleteTestUrl = #{jsAction @Tests.delete(':id') /} 
	
	$.ajax(deleteTestUrl({id: id}), {
		type: "DELETE",
		success: function() {
			console.log("test " + id + " removed");
			$('#test_'+id).fadeOut('slow', function() {
				$('#test_'+id).remove();
			});
		}
	});
}

</script>