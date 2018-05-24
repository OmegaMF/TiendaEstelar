
function saludar(nombre)
{
	console.log("Hola "+nombre);
}

function holaMundo()
{
	alert("Hola Mundo");
}




$(document).ready
	(   function()
		{
			console.log("Terminó de cargar wei");
			
			$("#n1").click(function(){
				$("span").text("Clickea acá");
				$("#secreto").val("comunidad-it");
			});
			
			$("span").click(function(){
				alert("Clickeaste el texto :o");
				console.log("Clickeaste el texto :o");
			});
			
			$("#n2").click(function(){
				alert("El valor del input es "+ $("#secreto").val());
			});
		}
	);

console.log("aloo");