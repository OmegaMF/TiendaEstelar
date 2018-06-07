package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminitradoresController
{
	
	@Autowired
	private Environment env;

	@Autowired
	private AdministradoresHelper AdministradoresHelper;
	
	
	@GetMapping("/login")
	public String login() {
		return "log-in";
	}
	
	@GetMapping("/procesarLogin")
	public String procesarLogin(HttpSession session, @RequestParam String nombre, @RequestParam String contrasenia) throws SQLException {
		boolean sePudo = AdministradoresHelper.intentarLoguearse(session, nombre, contrasenia);
		
		if (sePudo) {
			return "redirect:/";
		} else {
			// TODO: pregarcar los datos que lleno, salvo la contrasenia
			return "log-in";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) throws SQLException {
		AdministradoresHelper.cerrarSesion(session);
		return "redirect:/";
	}
	
	
	@GetMapping("/agregarAdministrador")
	public String agregarAdministrador(Model template) throws SQLException
	{
		return "registroAdministrador";
	}
	
	@GetMapping("/insertarAdministrador")
	public String insertarComponente(@RequestParam String nombre, @RequestParam String contrasenia) throws SQLException
	{
		
		
	
		
		Connection connection;
		
		connection=DriverManager.getConnection(env.getProperty("spring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = 
				connection.prepareStatement("INSERT INTO administradores(nombre,contrasenia) VALUES(?,?);");
											//DELETE FROM componentes WHERE id>17
		consulta.setString(1, nombre);
		consulta.setString(2, contrasenia);

		
		consulta.executeUpdate();
		
		Email email = EmailBuilder.startingBlank()
			    .from("Manu", "tuaweloide@hotmail.com")
			    .to("manu", "manu.f12345@gmail.com")
			    .withSubject("My Bakery is finally open!")
			    .withPlainText("Boiiii, bakery's open boiiiii")
			    .buildEmail();

			MailerBuilder
			  .withSMTPServer("smtp.sendgrid.net", 587, "apikey", "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")
			  .buildMailer()
			  .sendMail(email);
		
		
		
		connection.close();
		return "redirect:/";
	}
	
	@GetMapping("/eliminarAdministrador/{id}")
	public String eliminarAdministrador(@PathVariable int id) throws SQLException
	{
		
		Connection connection;
		
		connection=DriverManager.getConnection(env.getProperty("spring.datasource.url"),env.getProperty("spring.datasource.username"),env.getProperty("spring.datasource.password"));
		
		PreparedStatement consulta = 
				connection.prepareStatement("DELETE FROM administradores WHERE id=?;");
											
		
		consulta.setInt(1, id);
		
		consulta.executeUpdate();
		
		connection.close();
		return "redirect:/";
	}
	
	
	
	
}