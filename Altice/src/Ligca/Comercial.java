package Ligca;

public class Comercial extends Empleado {
	private String zona;
    private double comision;
    private double ventasMes;
    private double meta;
	public Comercial(String zona, double comision, double ventasMes, double meta) {
		super();
		this.zona = zona;
		this.comision = comision;
		this.ventasMes = ventasMes;
		this.meta = meta;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public double getComision() {
		return comision;
	}
	public void setComision(double comision) {
		this.comision = comision;
	}
	public double getVentasMes() {
		return ventasMes;
	}
	public void setVentasMes(double ventasMes) {
		this.ventasMes = ventasMes;
	}
	public double getMeta() {
		return meta;
	}
	public void setMeta(double meta) {
		this.meta = meta;
	}

}
