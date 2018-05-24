package com.example.demo;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExampleController
{
	@GetMapping("/ejercicio-css")
	public String ejercicioCss() {
		return "ejercicio-css";
	}
	
	@GetMapping("/ejercicio-css-bootstrap")
	public String ejercicioCssBootstrap() {
		return "ejercicio-css-bootstrap";
	}
	
	@GetMapping("/ejemplo-jquery")
	public String ejemplojquery() {
		return "ejemplo-jquery";
	}
	
	@GetMapping("/ejercicio-rutas-y-formularios")
	public String ejercicioRutasYFormularios() {
		return "ejercicio-rutas-formularios";
	}
	
	@GetMapping("/sorteo")
	@ResponseBody
	public int sorteo()
	{
		 Random generador = new Random();
	     int n = generador.nextInt(11);
		return n;
	}
	
	@GetMapping("/sorteo/{n}")
	@ResponseBody
	public int sorteoN(@PathVariable int n)
	{
		 Random generador = new Random();
	     int m = generador.nextInt(n);
		return m;
	}
	
	@GetMapping("/")
	public String Bienvenida() 
	{
		return "paginaBienvenida";
	}
	
	@GetMapping("/completar-datos")
	public String completar()
	{
		return "completarDatos";
	}
	
	@GetMapping("/comprobarFormulario")
	public String comprobarFormulario(Model template, @RequestParam String nombre, @RequestParam String dni, @RequestParam String email, @RequestParam String comentario)
	{
		template.addAttribute("nombrePreCargado", nombre);
		template.addAttribute("dniPreCargado", dni);
		template.addAttribute("emailPreCargado",email);
		template.addAttribute("comentarioPreCargado", comentario);
		
		if ( nombre.length() == 0 || dni.length() == 0 || ! dni.matches("[0-9]+") )
		{
    		return "completarDatos";
    	}
		
    	return "gracias";
    }
	
	

}
