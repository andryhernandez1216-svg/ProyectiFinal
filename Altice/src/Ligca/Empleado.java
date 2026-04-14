package Ligca;

import java.io.Serializable;
import java.util.Date;

// Agregamos implements Serializable para que pueda viajar por Sockets
public class Empleado extends Persona implements Serializable {

    // ID de versión para evitar errores de compatibilidad en el servidor
    private static final long serialVersionUID = 1L;

    private String codigo;
    private float salario;
    private Date fechaIngreso;
    private String estado;
    private float comisiones;
    private boolean activo;

    // Constructor actualizado
    public Empleado(String cedula, String nombre, String apellido, String telefono, String email,
                    String direccion, Date fechaRegistro, String codigo, float salario, Date fechaIngreso,
                    String estado, float comisiones, boolean activo) {

        // Llamada al constructor de Persona (que ya es Serializable)
        super(cedula, nombre, apellido, telefono, email, direccion, fechaRegistro);
        
        try {
            setCodigo(codigo);
            setSalario(salario);
            setFechaIngreso(fechaIngreso);
            setComisiones(comisiones);
            this.estado = (estado != null) ? estado : "NUEVO";
            this.activo = activo;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    // --- Métodos de acceso con validaciones ---

    public String getCodigo() { return codigo; }

    public void setCodigo(String codigo) throws Exception {
        if (codigo == null || codigo.trim().isEmpty())
            throw new Exception("El código del empleado no puede estar vacío.");
        this.codigo = codigo.trim();
    }

    public float getSalario() { return salario; }

    public void setSalario(float salario) throws Exception {
        if (salario <= 0)
            throw new Exception("El salario debe ser mayor a 0.");
        this.salario = salario;
    }

    public Date getFechaIngreso() { return fechaIngreso; }

    public void setFechaIngreso(Date fechaIngreso) throws Exception {
        if (fechaIngreso == null)
            throw new Exception("La fecha de contratación no puede ser nula.");
        this.fechaIngreso = fechaIngreso;
    }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public float getComisiones() { return comisiones; }

    public void setComisiones(float comisiones) throws Exception {
        if (comisiones < 0)
            throw new Exception("Las comisiones no pueden ser negativas.");
        this.comisiones = comisiones;
    }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    // --- Lógica de Negocio ---

    public void aumentarSalario(float porcentaje) throws Exception {
        if (porcentaje <= 0)
            throw new Exception("Porcentaje inválido.");
        this.salario += this.salario * (porcentaje / 100);
    }

    public float calcularSalarioTotal() {
        return salario + comisiones;
    }

    public int getAniosEnEmpresa() {
        if (fechaIngreso == null) return 0;
        long diferencia = new Date().getTime() - fechaIngreso.getTime();
        // 365.25 para considerar años bisiestos en cálculos largos
        return (int) (diferencia / (1000L * 60 * 60 * 24 * 365));
    }

    @Override
    public String toString() {
        return String.format("Empleado: %s | Código: %s | Salario Total: RD$%.2f", 
                getNombreCompleto(), codigo, calcularSalarioTotal());
    }
}
