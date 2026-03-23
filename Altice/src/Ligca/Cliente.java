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

}
