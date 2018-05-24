package com.example.model;

public class Componente {
	private String tipo;
	private String marca;
	private String modelo;
	private String velocidad;
	private String familia;
	private String socket;
	private String nucleos;
	private String tamanio;
	private String capacidad;
	private boolean certificada;
	private int precio;
	private int id;
	
	public Componente(int i,String t,String m,String mo, String v, String f, String s, String n, String ta, String c, boolean cer,int p )
	{
		this.tipo=t;
		this.marca=m;
		this.modelo=mo;
		this.velocidad=v;
		this.familia=f;
		this.socket=s;
		this.nucleos=n;
		this.tamanio=ta;
		this.capacidad=c;
		this.certificada=cer;
		this.id=i;
		this.precio=p;
		
	}
	
	@Override
	public String toString() {
		return "Componente [tipo=" + tipo + ", marca=" + marca + ", modelo=" + modelo + ", velocidad=" + velocidad +", familia="+
	familia+", socket="+ socket+", nucleos="+nucleos+", tamanio="+tamanio+", capacidad="+capacidad+", certificada="+certificada+
	", id=" + id + ", precio="+ precio + " ]";
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(String velocidad) {
		this.velocidad = velocidad;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getSocket() {
		return socket;
	}

	public void setSocket(String socket) {
		this.socket = socket;
	}

	public String getNucleos() {
		return nucleos;
	}

	public void setNucleos(String nucleos) {
		this.nucleos = nucleos;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public String getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

	public boolean isCertificada() {
		return certificada;
	}

	public void setCertificada(boolean certificada) {
		this.certificada = certificada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
	

}
	
	