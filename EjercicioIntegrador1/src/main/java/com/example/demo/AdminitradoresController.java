package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminitradoresController
{
	
	@Autowired
	private Environment env;
	
	
	
	@GetMapping("/agregarAdministrador")
	@ResponseBody
	public String agregarAdministrador(Model template) throws SQLException
	{
		return "registroAdministrador";
	}
	
	@GetMapping("/insertar-administrador")
	public String insertarComponente(@RequestParam String nombre, @RequestParam String contrasenia) throws SQLException
	{
		
		
	
		
		Connection connection;
		
		connection=DriverManager.getConnection(env.getProperty("spring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = 
				connection.prepareStatement("INSERT INTO administradores(nombre,contrasenia) VALUES(?,?);");
											//DELETE FROM componentes WHERE id>17
		consulta.setString(1, nombre);
		consulta.setString(2, contrasenia);

		
		//precio=precio/100;
		
		consulta.executeUpdate();
		
		connection.close();
		return "redirect:/vPrevia";
	}
	
	
}