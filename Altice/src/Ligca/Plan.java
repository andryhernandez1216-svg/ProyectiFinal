package Ligca;

public class Plan {

	private String idPlan;
    private String nombre;
    private String descripcion;
    private String velocidad; 
    private float precio;
    private String tipo; 
    private int duracionContrato; 
    private boolean estado;
	public String getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
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
	public String getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(String velocidad) {
		this.velocidad = velocidad;
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
	public int getDuracionContrato() {
		return duracionContrato;
	}
	public void setDuracionContrato(int duracionContrato) {
		this.duracionContrato = duracionContrato;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Plan(String idPlan, String nombre, String descripcion, String velocidad, float precio, String tipo,
			int duracionContrato, boolean estado) {
		super();
		this.idPlan = idPlan;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.velocidad = velocidad;
		this.precio = precio;
		this.tipo = tipo;
		this.duracionContrato = duracionContrato;
		this.estado = true;
	}
    
	
}
