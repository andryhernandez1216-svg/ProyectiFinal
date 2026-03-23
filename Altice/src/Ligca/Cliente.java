package Ligca;

import java.util.ArrayList;

public class Cliente extends Persona {
	 public static final String ACTIVO = "activo";
	 public static final String SUSPENDIDO = "suspendido";
	 public static final String INACTIVO = "inactivo";

	 private String codigoCliente;
	 private String tipoCliente;
	 private String estado;
	 private ArrayList<Contrato> contratos;
	 private Comercial vendedorAsignado;
	 public Cliente(String codigoCliente, String tipoCliente, String estado, ArrayList<Contrato> contratos,
			Comercial vendedorAsignado) {
		super();
		this.codigoCliente = codigoCliente;
		this.tipoCliente = tipoCliente;
		this.estado = estado;
		this.contratos = contratos;
		this.vendedorAsignado = vendedorAsignado;
	 }
	 public String getCodigoCliente() {
		 return codigoCliente;
	 }
	 public void setCodigoCliente(String codigoCliente) {
		 this.codigoCliente = codigoCliente;
	 }
	 public String getTipoCliente() {
		 return tipoCliente;
	 }
	 public void setTipoCliente(String tipoCliente) {
		 this.tipoCliente = tipoCliente;
	 }
	 public String getEstado() {
		 return estado;
	 }
	 public void setEstado(String estado) {
		 this.estado = estado;
	 }
	 public ArrayList<Contrato> getContratos() {
		 return contratos;
	 }
	 public void setContratos(ArrayList<Contrato> contratos) {
		 this.contratos = contratos;
	 }
	 public Comercial getVendedorAsignado() {
		 return vendedorAsignado;
	 }
	 public void setVendedorAsignado(Comercial vendedorAsignado) {
		 this.vendedorAsignado = vendedorAsignado;
	 }
	 public static String getActivo() {
		 return ACTIVO;
	 }
	 public static String getSuspendido() {
		 return SUSPENDIDO;
	 }
	 public static String getInactivo() {
		 return INACTIVO;
	 }

}
