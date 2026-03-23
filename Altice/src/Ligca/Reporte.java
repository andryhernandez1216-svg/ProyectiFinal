package Ligca;

public abstract class Reporte {

	protected String titulo;
    protected String periodo;
	public Reporte(String titulo, String periodo) {
		super();
		this.titulo = titulo;
		this.periodo = periodo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	

}
