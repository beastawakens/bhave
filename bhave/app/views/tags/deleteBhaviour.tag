<script>
function deleteBhaviour(id) {
	var deleteBhaviourUrl = #{jsAction @Bhaviours.delete(':id') /} 
	
	$.ajax(deleteBhaviourUrl({id: id}), {
		type: "DELETE",
		success: function() {
			console.log("bhaviour " + id + " removed");
			$('#bhaviour_'+id).fadeOut('slow', function() {
				$('#bhaviour_'+id).remove();
				if ($('.bhaviourRow').size()==0) {
					$('#bhavioursContainer').fadeOut('slow');
				}
			});
		}
	});
}

</script>