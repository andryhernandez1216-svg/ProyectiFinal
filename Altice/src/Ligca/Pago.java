package Ligca;

import java.util.Date;

public class Pago {
	private String idPago;
    private String idContrato;
    private float monto;
    private Date fecha;
    private boolean pagado;
    
	public String getIdPago() {
		return idPago;
	}
	public void setIdPago(String idPago) {
		this.idPago = idPago;
	}
	public String getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public boolean isPagado() {
		return pagado;
	}
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	
	public Pago(String idPago, String idContrato, float monto, Date fecha, boolean pagado) {
		super();
		this.idPago = idPago;
		this.idContrato = idContrato;
		this.monto = monto;
		this.fecha = fecha;
		this.pagado = true;
	}
    
	

}
