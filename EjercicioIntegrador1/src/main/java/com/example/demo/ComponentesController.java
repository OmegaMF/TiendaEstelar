package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Componente;
import com.example.model.Usuario;


@Controller
public class ComponentesController
{
	
	
	
	
	
	
	
	@Autowired
	private Environment env;
	@Autowired private AdministradoresHelper AdministradoresHelper;
	
	
	@GetMapping("/editarC/{id}")
	public String editarC(HttpSession session, Model template, @PathVariable int id) throws SQLException
	{
Connection connection;
		
		connection=DriverManager.getConnection(env.getProperty("spring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = 
				connection.prepareStatement("SELECT * FROM componentes WHERE id=?;");
		
		consulta.setInt(1, id);
		
		template.addAttribute("idComponente", id);
		Usuario logueado=AdministradoresHelper.usuarioLogueado(session);
		if (logueado == null) {
	        template.addAttribute("estaLogeado", false);
	    } else {
	        template.addAttribute("estaLogeado", true);
	    }
		
		
		
		return "editarC";
	}
	
	
	
	
	@GetMapping("/detalleC/{id}")
	public String detalleC(HttpSession session, Model template, @PathVariable int id) throws SQLException
	{
		
Connection connection;
		
		connection=DriverManager.getConnection(env.getProperty("spring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
		
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
			String urlimg=resultado.getString("urlimg");
			
	
			
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
			template.addAttribute("urlimg",urlimg);
			
		}
		
		
		Usuario logueado=AdministradoresHelper.usuarioLogueado(session);
		if (logueado == null) {
	        template.addAttribute("estaLogeado", false);
	    } else {
	        template.addAttribute("estaLogeado", true);
	    }
		
		
		return "detalleComponente";
	}
	
	
	
	@GetMapping("/listadoComponentes")
	public String listadoComponentes(HttpSession session, Model template) throws SQLException
	{
		
Connection connection;
		
		connection=DriverManager.getConnection(env.getProperty("spring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
		

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
			String urlimg=resultado.getString("urlimg");
			int precio=resultado.getInt("precio");
			
			
			Componente x=new Componente(id, tipo, marca, modelo, velocidad, familia, socket, nucleos, tamanio, capacidad, certificada, precio ,urlimg );
			listadoComponentes.add(x);
		}
		
		
		template.addAttribute("listadoComponentes", listadoComponentes);

		Usuario logueado=AdministradoresHelper.usuarioLogueado(session);
		if (logueado == null) {
	        template.addAttribute("estaLogeado", false);
	    } else {
	        template.addAttribute("estaLogeado", true);
	    }
		
		
		
		return "listadoComponentes";
	}
	
	
	@GetMapping("/agregar-componente")
	public String agregarComponente(HttpSession session, Model template) throws SQLException
	{
		
		Usuario logueado=AdministradoresHelper.usuarioLogueado(session);
		if (logueado == null) {
	        template.addAttribute("estaLogeado", false);
	    } else {
	        template.addAttribute("estaLogeado", true);
	    }
		
		
	return "registro";	
	}
	
	
	
	
	@GetMapping("/insertar-componente")
	public String insertarComponente(HttpSession session, Model template, @RequestParam String tipo, @RequestParam String marca,@RequestParam String modelo,@RequestParam(required=false) String velocidad
			,@RequestParam(required=false) String familia,@RequestParam(required=false) String socket,@RequestParam(required=false) int nucleos,@RequestParam(required=false) String tamanio,@RequestParam(required=false) String capacidad
			,@RequestParam(required=false) boolean certificada, int precio, String urlimg) throws SQLException
	{
		
		
	
		
		Connection connection;
		
		connection=DriverManager.getConnection(env.getProperty("spring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = 
				connection.prepareStatement("INSERT INTO componentes(tipo,marca,modelo,velocidad,familia,socket,nucleos,tamanio,capacidad,certificada,precio,urlimg) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);");
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
		consulta.setString(12, urlimg);
		
		//precio=precio/100;
		
		consulta.executeUpdate();
		
		connection.close();
		
		Usuario logueado=AdministradoresHelper.usuarioLogueado(session);
		if (logueado == null) {
	        template.addAttribute("estaLogeado", false);
	    } else {
	        template.addAttribute("estaLogeado", true);
	    }
		
		
		
		return "redirect:/listadoComponentes";
	}
	
	
	@GetMapping("/eliminar-componente/{id}")
	@ResponseBody
	public String eliminarComponente(HttpSession session, Model template, @PathVariable int id) throws SQLException
	{
		
		Connection connection;
		
		connection=DriverManager.getConnection(env.getProperty("spring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = 
				connection.prepareStatement("DELETE FROM componentes WHERE id=?;");
											
		
		consulta.setInt(1, id);
		
		consulta.executeUpdate();
		
		connection.close();
		
		
		Usuario logueado=AdministradoresHelper.usuarioLogueado(session);
		if (logueado == null) {
	        template.addAttribute("estaLogeado", false);
	    } else {
	        template.addAttribute("estaLogeado", true);
	    }		
		
		
		
		return "Que así sea";
	}
	
	
	@GetMapping("/busquedaC")
	public String busquedaC(Model template,HttpSession session, @RequestParam String palabraBuscada) throws SQLException
	{
		
Connection connection;
		
		connection=DriverManager.getConnection(env.getProperty("spring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
		
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
			String urlimg=resultado.getString("urlimg");
			int precio=resultado.getInt("precio");
			
			
			Componente x=new Componente(id, tipo, marca, modelo, velocidad, familia, socket, nucleos, tamanio, capacidad, certificada, precio ,urlimg );
			listadoComponentes.add(x);
		}
		  
		
		template.addAttribute("listadoComponentes", listadoComponentes);
		
		
		
		Usuario logueado=AdministradoresHelper.usuarioLogueado(session);
		if (logueado == null) {
	        template.addAttribute("estaLogeado", false);
	    } else {
	        template.addAttribute("estaLogeado", true);
	    }
		
		return "listadoComponentes";
	}
	
	
	@GetMapping("/busquedaT/{tipoComponente}")
	public String busquedaT(HttpSession session, Model template, @PathVariable String tipoComponente) throws SQLException
	{
		
Connection connection;
		
		connection=DriverManager.getConnection(env.getProperty("spring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = 
				connection.prepareStatement("SELECT * FROM componentes WHERE tipo LIKE ?;");
		
		consulta.setString(1,"%" +tipoComponente+ "%");
		
		
		
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
			String urlimg=resultado.getString("urlimg");
			int precio=resultado.getInt("precio");
			
			
			Componente x=new Componente(id, tipo, marca, modelo, velocidad, familia, socket, nucleos, tamanio, capacidad, certificada, precio ,urlimg );
			listadoComponentes.add(x);
		}
		
		
		template.addAttribute("listadoComponentes", listadoComponentes);
		
		Usuario logueado=AdministradoresHelper.usuarioLogueado(session);
		if (logueado == null) {
	        template.addAttribute("estaLogeado", false);
	    } else {
	        template.addAttribute("estaLogeado", true);
	    }
		
		
		return "listadoComponentes";
	}
	
	
	@GetMapping("/busquedaM/{marcaComponente}")
	public String busquedaM(HttpSession session, Model template, @PathVariable String marcaComponente) throws SQLException
	{
		
Connection connection;
		
		connection=DriverManager.getConnection(env.getProperty("spring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = 
				connection.prepareStatement("SELECT * FROM componentes WHERE marca LIKE ?;");
		
		consulta.setString(1,"%" +marcaComponente+ "%");
		
		
		
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
			String urlimg=resultado.getString("urlimg");
			int precio=resultado.getInt("precio");
			
			
			Componente x=new Componente(id, tipo, marca, modelo, velocidad, familia, socket, nucleos, tamanio, capacidad, certificada, precio ,urlimg );
			listadoComponentes.add(x);
		}
		
		
		template.addAttribute("listadoComponentes", listadoComponentes);
		
		
		Usuario logueado=AdministradoresHelper.usuarioLogueado(session);
		if (logueado == null) {
	        template.addAttribute("estaLogeado", false);
	    } else {
	        template.addAttribute("estaLogeado", true);
	    }
		
		return "listadoComponentes";
	}
	
	
	
	

	
	

	
	@GetMapping("/services")
	public String ventas(HttpSession session, Model template) throws SQLException
	{
		
		Usuario logueado=AdministradoresHelper.usuarioLogueado(session);
		if (logueado == null) {
	        template.addAttribute("estaLogeado", false);
	    } else {
	        template.addAttribute("estaLogeado", true);
	    }
		
		
		return "servicios";
	}

	
	@GetMapping("/contacto")
	public String contact(HttpSession session, Model template) throws SQLException
	{
		Usuario logueado=AdministradoresHelper.usuarioLogueado(session);
		if (logueado == null) {
	        template.addAttribute("estaLogeado", false);
	    } else {
	        template.addAttribute("estaLogeado", true);
	    }
		
		
		return "contacto";
	}
	
	
	@GetMapping("/")
	public String Pagina(HttpSession session, Model template) throws SQLException
	{
		Usuario logueado=AdministradoresHelper.usuarioLogueado(session);
		if (logueado == null) {
	        template.addAttribute("estaLogeado", false);
	    } else {
	        template.addAttribute("estaLogeado", true);
	    }
		
		
		return "vPrevias";
	}
	
	//dividir el precio en 100
}
