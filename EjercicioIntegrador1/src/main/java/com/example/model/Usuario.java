package com.example.model;

public class Usuario {
	private String nombre;
	private String contrasenia;
	private boolean activo;
	private int id;
	
	public Usuario(int i,String n,String c,boolean a )
	{
		this.nombre=n;
		this.contrasenia=c;
		this.activo=a;
		this.id=i;
		
	}
	
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", contrasenia=" + contrasenia + ", activo=" + activo + ", id=" + id + "]";
	}

	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public void setNombre(String nombreNuevo) 
	{
		this.nombre=nombreNuevo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
