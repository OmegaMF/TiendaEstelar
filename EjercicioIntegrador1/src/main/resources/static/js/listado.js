
/*$(document).ready(function(){
	$(".tarjeta-usuario").click(
			function()
			{
				$(this).toggleClass("seleccionada"); //con toggle cuando se hace un nuevo click, le saca la clase al objeto que ya la tenga
			}	
		)
	});*/

$(document).ready(function(){
	$("#cb").click(function(){
		
				if($(this).is(":checked")){
					$(".tarjeta-usuario").addClass("seleccionada");
				}else{
					$(".tarjeta-usuario").removeClass("seleccionada");
			}
			
			//function(){$(".tarjeta-usuario").toggleClass("seleccionada");}
			//function(){console.log("si");}
			}
	)
	
});