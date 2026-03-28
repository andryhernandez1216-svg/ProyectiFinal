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
	public void setIdServicio(String idServicio) throws Exception {
        if (idServicio == null || idServicio.trim().isEmpty())
            throw new Exception("El id del servicio no puede estar vacio.");
        this.idServicio = idServicio;
    }

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty())
            throw new Exception("El nombre del servicio no puede estar vacio.");
        this.nombre = nombre;
    }
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) throws Exception {
        if (descripcion == null || descripcion.trim().isEmpty())
            throw new Exception("La descripcion del servicio no puede estar vacia.");
        this.descripcion = descripcion;
    }
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) throws Exception {
        if (precio <= 0)
            throw new Exception("El precio del servicio debe ser mayor a 0.");
        this.precio = precio;
    }
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) throws Exception {
        if (tipo == null || tipo.trim().isEmpty())
            throw new Exception("El tipo del servicio no puede estar vacio.");
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
		try {
            setIdServicio(idServicio);
            setNombre(nombre);
            setDescripcion(descripcion);
            setTipo(tipo);
            setPrecio(precio);
            this.estado = estado;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
	
}
