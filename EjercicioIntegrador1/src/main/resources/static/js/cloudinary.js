$(document).ready(function() {
	
	$("#upload_widget_opener").click(function () {
		cloudinary.openUploadWidget({
	
		upload_preset : 'ybolhkcs', 
		cloud_name: 'dt0hqsxvd',
		theme: 'white',
		multiple: true,
		max_image_width: 750,
		max_image_height: 500,
		max_files: 5,
		folder: 'imgs',
		sources: [ 'local', 'url', 'facebook'], 
	}, function(error, result) {
		console.log(error, result)
		var url = result[0].secure_url;
		        
		console.log (url);
		        
		$("form").find(".img").val( url );
	})
})

})