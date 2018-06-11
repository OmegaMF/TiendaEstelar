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
		folder: 'mi_carpeta',
		sources: [ 'local', 'url', 'facebook'], 
	}, function(error, result) {
		console.log(error, result)
	})
})

})