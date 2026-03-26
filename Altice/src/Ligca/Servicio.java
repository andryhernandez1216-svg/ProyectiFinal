package Ligca;

public class Servicio {

	private String idServicio;
    private String nombre;
    private String descripcion;
    private float precio;
    private String tipo; 
    private boolean estado;
	public String getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Servicio(String idServicio, String nombre, String descripcion, float precio, String tipo, boolean estado) {
		super();
		this.idServicio = idServicio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.tipo = tipo;
		this.estado = true;
	}
    
	
}
