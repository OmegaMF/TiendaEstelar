package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Componente;

@Controller
public class ComponentesController
{
	
	@Autowired
	private Environment env;
	
	
	
	@GetMapping("/editarC/{id}")
	public String editarC(Model template, @PathVariable int id) throws SQLException
	{
Connection connection;
		
		connection=DriverManager.getConnection(env.getProperty("jspring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = 
				connection.prepareStatement("SELECT * FROM componentes WHERE id=?;");
		
		consulta.setInt(1, id);
		
		
		template.addAttribute("idComponente", id);
		return "editarC";
	}
	
	
	
	
	@GetMapping("/detalleC/{id}")
	public String detalleC(Model template, @PathVariable int id) throws SQLException
	{
		
Connection connection;
		
		connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ejemplo","postgres","admin");
		
		PreparedStatement consulta = 
				connection.prepareStatement("SELECT * FROM componentes WHERE id=?;");
		
		consulta.setInt(1, id);
		
		ResultSet resultado= consulta.executeQuery();
		
		if(resultado.next())
		{
			
			id=resultado.getInt("id");
			String tipo=resultado.getString("tipo");
			String marca=resultado.getString("marca");
			String modelo=resultado.getString("modelo");
			String velocidad=resultado.getString("velocidad");
			String familia=resultado.getString("familia");
			String socket=resultado.getString("socket");
			String nucleos=resultado.getString("nucleos");
			String tamanio=resultado.getString("tamanio");
			String capacidad=resultado.getString("capacidad");
			Boolean certificada = resultado.getBoolean("certificada"); if (resultado.wasNull()) certificada = null;
			int precio=resultado.getInt("precio");
			
	
			
			template.addAttribute("id", id);
			template.addAttribute("tipo", tipo);
			template.addAttribute("marca", marca);
			template.addAttribute("modelo",modelo);
			template.addAttribute("velocidad",velocidad);
			template.addAttribute("familia",familia);
			template.addAttribute("socket",socket);
			template.addAttribute("nucleos",nucleos);
			template.addAttribute("tamanio",tamanio);
			template.addAttribute("capacidad",capacidad);
			template.addAttribute("certificada",certificada);
			template.addAttribute("precio",precio);
			
		}
		
		
		return "detalleComponente";
	}
	
	
	
	@GetMapping("/listadoComponentes")
	public String listadoComponentes(Model template) throws SQLException
	{
		
Connection connection;
		
		connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ejemplo","postgres","admin");
		
		PreparedStatement consulta = 
				connection.prepareStatement("SELECT * FROM componentes;");
		
		
		
		ResultSet resultado= consulta.executeQuery();
		
		
		ArrayList<Componente> listadoComponentes=new ArrayList<Componente>();
		
		while(resultado.next())
		{
			int id=resultado.getInt("id");
			String tipo=resultado.getString("tipo");
			String marca=resultado.getString("marca");
			String modelo=resultado.getString("modelo");
			String velocidad=resultado.getString("velocidad");
			String familia=resultado.getString("familia");
			String socket=resultado.getString("socket");
			String nucleos=resultado.getString("nucleos");
			String tamanio=resultado.getString("tamanio");
			String capacidad=resultado.getString("capacidad");
			boolean certificada=resultado.getBoolean("certificada");
			int precio=resultado.getInt("precio");
			
			Componente x=new Componente(id, tipo, marca, modelo, velocidad, familia, socket, nucleos, tamanio, capacidad, certificada, precio);
			listadoComponentes.add(x);
		}
		
		
		template.addAttribute("listadoComponentes", listadoComponentes);

		
		return "listadoComponentes";
	}
	
	
	@GetMapping("/agregar-componente")
	public String agregarComponente(){
	return "registro";	
	}
	
	
	
	
	@GetMapping("/insertar-componente")
	public String insertarComponente(@RequestParam String tipo, @RequestParam String marca,@RequestParam String modelo,@RequestParam String velocidad
			,@RequestParam String familia,@RequestParam String socket,@RequestParam int nucleos,@RequestParam String tamanio,@RequestParam String capacidad
			,@RequestParam boolean certificada, int precio) throws SQLException
	{
		
		
	
		
		Connection connection;
		
		connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ejemplo","postgres","admin");
		
		PreparedStatement consulta = 
				connection.prepareStatement("INSERT INTO componentes(tipo,marca,modelo,velocidad,familia,socket,nucleos,tamanio,capacidad,certificada,precio) VALUES(?,?,?,?,?,?,?,?,?,?,?);");
											//DELETE FROM componentes WHERE id>17
		consulta.setString(1, tipo);
		consulta.setString(2, marca);
		consulta.setString(3, modelo);
		consulta.setString(4, velocidad);
		consulta.setString(5, familia);
		consulta.setString(6, socket);
		consulta.setInt(7, nucleos);
		consulta.setString(8, tamanio);
		consulta.setString(9, capacidad);
		consulta.setBoolean(10, certificada);
		consulta.setInt(11, precio);
		
		//precio=precio/100;
		
		consulta.executeUpdate();
		
		connection.close();
		return "redirect:/listadoComponentes";
	}
	
	
	@GetMapping("/eliminar-componente/{id}")
	@ResponseBody
	public String eliminarComponente(@PathVariable int id) throws SQLException
	{
		
		Connection connection;
		
		connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ejemplo","postgres","admin");
		
		PreparedStatement consulta = 
				connection.prepareStatement("DELETE FROM componentes WHERE id=?;");
											
		
		consulta.setInt(1, id);
		
		consulta.executeUpdate();
		
		connection.close();
		return "Que así sea";
	}
	
	
	@GetMapping("/busquedaC")
	public String busquedaC(Model template, @RequestParam String palabraBuscada) throws SQLException
	{
		
Connection connection;
		
		connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ejemplo","postgres","admin");
		
		PreparedStatement consulta = 
				connection.prepareStatement("SELECT * FROM componentes WHERE modelo LIKE ?;");
		
		consulta.setString(1,"%" +palabraBuscada+ "%");
		
		
		
		ResultSet resultado= consulta.executeQuery();
		
		
		ArrayList<Componente> listadoComponentes=new ArrayList<Componente>();
		
		while(resultado.next())
		{
			int id=resultado.getInt("id");
			String tipo=resultado.getString("tipo");
			String marca=resultado.getString("marca");
			String modelo=resultado.getString("modelo");
			String velocidad=resultado.getString("velocidad");
			String familia=resultado.getString("familia");
			String socket=resultado.getString("socket");
			String nucleos=resultado.getString("nucleos");
			String tamanio=resultado.getString("tamanio");
			String capacidad=resultado.getString("capacidad");
			boolean certificada=resultado.getBoolean("certificada");
			int precio=resultado.getInt("precio");
			
			Componente x=new Componente(id, tipo, marca, modelo, velocidad, familia, socket, nucleos, tamanio, capacidad, certificada, precio);
			listadoComponentes.add(x);
		}
		
		
		template.addAttribute("listadoComponentes", listadoComponentes);
		
		return "listadoComponentes";
	}
	
	
	@GetMapping("/busquedaT")
	public String busquedaT(Model template, @RequestParam String palabraBuscada) throws SQLException
	{
		
Connection connection;
		
		connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ejemplo","postgres","admin");
		
		PreparedStatement consulta = 
				connection.prepareStatement("SELECT * FROM componentes WHERE tipo LIKE ?;");
		
		consulta.setString(1,"%" +palabraBuscada+ "%");
		
		
		
		ResultSet resultado= consulta.executeQuery();
		
		
		ArrayList<Componente> listadoComponentes=new ArrayList<Componente>();
		
		while(resultado.next())
		{
			int id=resultado.getInt("id");
			String tipo=resultado.getString("tipo");
			String marca=resultado.getString("marca");
			String modelo=resultado.getString("modelo");
			String velocidad=resultado.getString("velocidad");
			String familia=resultado.getString("familia");
			String socket=resultado.getString("socket");
			String nucleos=resultado.getString("nucleos");
			String tamanio=resultado.getString("tamanio");
			String capacidad=resultado.getString("capacidad");
			boolean certificada=resultado.getBoolean("certificada");
			int precio=resultado.getInt("precio");
			
			Componente x=new Componente(id, tipo, marca, modelo, velocidad, familia, socket, nucleos, tamanio, capacidad, certificada, precio);
			listadoComponentes.add(x);
		}
		
		
		template.addAttribute("listadoComponentes", listadoComponentes);
		
		return "listadoComponentes";
	}
	
	
	
	@ResponseBody
	@GetMapping("/pepito")
	public String Pepito(Model template)throws SQLException
	{
		return "hola";
	}
	
	

	
	@GetMapping("/ventas")
	public String ventas()
	{
		return "home";
	}
	
	@GetMapping("/about")
	public String about()
	{
		return "about";
	}
	
	@GetMapping("/services")
	public String services()
	{
		return "services";
	}
	
	@GetMapping("/contact")
	public String contact()
	{
		return "contact";
	}
	
	@GetMapping("/home")
	public String home()
	{
		return "home";
	}
	
	@GetMapping("/tienda")
	public String Pagina()
	{
		return "paginaPrincipal";
	}
}
