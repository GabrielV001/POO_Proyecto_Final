package Logica;

public class Ticket {
    private String asunto;
    private String descripcion;
    private String estado;
    private Usuario usuario;
    private Departamento departamento;

    // Constructor vacio
    public Ticket() {
    }

    // Constructor nuevo
    public Ticket(String asunto, String descripcion, Usuario usuario, Departamento departamento) {
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.estado = "nuevo";
        this.usuario = usuario;
        this.departamento = departamento;
    }

    // Constructor completo
    public Ticket(String asunto, String descripcion, String estado, Usuario usuario, Departamento departamento) {
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.usuario = usuario;
        this.departamento = departamento;
    }

    // Getters y Setters
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Ticket[asunto=" + asunto + ", descripcion=" + descripcion +
                ", estado=" + estado + ", usuario=" + usuario +
                ", departamento=" + departamento + "]";
    }
}