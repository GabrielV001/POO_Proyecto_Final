package Logica;

public class Ticket {
    private String asunto;
    private String descripcion;
    private String estado;
    private Usuario usuario;
    private Departamento departamento;

    public Ticket() {
    }

    public Ticket(String asunto, String descripcion, Usuario usuario, Departamento departamento) {
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.estado = "nuevo";
        this.usuario = usuario;
        this.departamento = departamento;
    }

    public Ticket(String asunto, String descripcion, String estado, Usuario usuario, Departamento departamento) {
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.usuario = usuario;
        this.departamento = departamento;
    }

    public String getAsunto() {
        return this.asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Departamento getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String toString() {
        String var10000 = this.asunto;
        return "Ticket[asunto=" + var10000 + ", descripcion=" + this.descripcion + ", estado=" + this.estado + ", usuario=" + String.valueOf(this.usuario) + ", departamento=" + String.valueOf(this.departamento) + "]";
    }
}
