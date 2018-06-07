package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Usuario;

@Controller
public class UsuariosController
{
	
	@GetMapping("/editar/{id}")
	public String editar(Model template, @PathVariable int id) throws SQLException
	{
Connection connection;
		
		connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ejemplo","postgres","admin");
		
		PreparedStatement consulta = 
				connection.prepareStatement("SELECT * FROM usuarios WHERE id=?;");
		
		consulta.setInt(1, id);
		
		
		template.addAttribute("idUsuario", id);
		return "editarUsuario";
	}
	
	
	
	
	@GetMapping("/detalle/{id}")
	public String detalle(Model template, @PathVariable int id) throws SQLException
	{
		
Connection connection;
		
		connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ejemplo","postgres","admin");
		
		PreparedStatement consulta = 
				connection.prepareStatement("SELECT * FROM usuarios WHERE id=?;");
		
		consulta.setInt(1, id);
		
		ResultSet resultado= consulta.executeQuery();
		
		if(resultado.next())
		{
			
			String name=resultado.getString("nombre");
			String password=resultado.getString("contrasenia");
			boolean activo=resultado.getBoolean("activo");
			
			template.addAttribute("name", name);
			template.addAttribute("password", password);
			template.addAttribute("activo", activo);
		}
		
		
		

		
		return "detalleUsuario";
	}
	
	
	
	@GetMapping("/listado")
	public String listado(Model template) throws SQLException
	{
		
Connection connection;
		
		connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ejemplo","postgres","admin");
		
		PreparedStatement consulta = 
				connection.prepareStatement("SELECT * FROM componentes;");
		
		
		
		ResultSet resultado= consulta.executeQuery();
		
		
		ArrayList<Usuario> listadoUsuarios=new ArrayList<Usuario>();
		
		while(resultado.next())
		{
			int id=resultado.getInt("id");
			String name=resultado.getString("nombre");
			String password=resultado.getString("contrasenia");
			boolean activo=resultado.getBoolean("activo");
			
			Usuario x=new Usuario(id, name, password);
			listadoUsuarios.add(x);
		}
		
		
		template.addAttribute("listadoUsuarios", listadoUsuarios);

		
		return "listadoUsuarios";
	}
	
	
	
	
	
	
	
	@GetMapping("/insertar-usuario")
	public String insertarUsuario(@RequestParam String name, @RequestParam String password) throws SQLException
	{
		
		
	
		
		Connection connection;
		
		connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ejemplo","postgres","admin");
		
		PreparedStatement consulta = 
				connection.prepareStatement("INSERT INTO usuarios(nombre,contrasenia,activo) VALUES(?,?,true);");
											//DELETE FROM usuarios WHERE id>17
		consulta.setString(1, name);
		consulta.setString(2, password);
		
		
		consulta.executeUpdate();
		
		connection.close();
		return "redirect:/ejercicio-css-bootstrap";
	}
	
	
	@GetMapping("/eliminar-usuario/{id}")
	@ResponseBody
	public String eliminarUsuario(@PathVariable int id) throws SQLException
	{
		
		Connection connection;
		
		connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ejemplo","postgres","admin");
		
		PreparedStatement consulta = 
				connection.prepareStatement("DELETE FROM usuarios WHERE id=?;");
											
		
		consulta.setInt(1, id);
		
		consulta.executeUpdate();
		
		connection.close();
		return "Que así sea";
	}
	
	
	@GetMapping("/busqueda")
	public String busqueda(Model template, @RequestParam String palabraBuscada) throws SQLException
	{
		
Connection connection;
		
		connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ejemplo","postgres","admin");
		
		PreparedStatement consulta = 
				connection.prepareStatement("SELECT * FROM usuarios WHERE nombre LIKE ?;");
		
		consulta.setString(1,"%" +palabraBuscada+ "%");
		
		
		
		ResultSet resultado= consulta.executeQuery();
		
		
		ArrayList<Usuario> listadoUsuarios=new ArrayList<Usuario>();
		
		while(resultado.next())
		{
			int id=resultado.getInt("id");
			String name=resultado.getString("nombre");
			String password=resultado.getString("contrasenia");
			boolean activo=resultado.getBoolean("activo");
			
			Usuario x=new Usuario(id, name, password);
			listadoUsuarios.add(x);
		}
		
		
		template.addAttribute("listadoUsuarios", listadoUsuarios);

		
		return "listadoUsuarios";
	}
	
	
	
	
	
	
	@GetMapping("/insertar-usuario-prueba")
	@ResponseBody
	public String insertarUsuarioPrueba() throws SQLException
	{
		
		Connection connection;
		
		connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ejemplo","postgres","admin");
		
		PreparedStatement consulta = 
				connection.prepareStatement("INSERT INTO usuarios(nombre,contrasenia,activo) VALUES('usuarioTest','1234',true);");
											//DELETE FROM usuarios WHERE id>17
		
		
		
		consulta.executeUpdate();
		
		connection.close();
		return "OK";
	}
	

	@GetMapping("/eliminar-usuario-prueba")
	@ResponseBody
	public String eliminarUsuarioPrueba() throws SQLException
	{
		
		Connection connection;
		
		connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ejemplo","postgres","admin");
		
		PreparedStatement consulta = 
				connection.prepareStatement("DELETE FROM usuarios WHERE nombre='usuarioTest';");
											
		
		
		
		consulta.executeUpdate();
		
		connection.close();
		return "OK";
	}
	
	@GetMapping("/estructura")
	public String estructura()
	{
		return "A";
	}
	
	@GetMapping("/B")
	public String B()
	{
		return "B";
	}
}

