package Logica;

public class Departamento {
    private String nombre;
    private String descripcion;
    private String correoContacto;
    private String extensionTelefonica;

    public Departamento() {
    }

    public Departamento(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Departamento(String nombre, String descripcion, String correoContacto, String extensionTelefonica) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.correoContacto = correoContacto;
        this.extensionTelefonica = extensionTelefonica;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCorreoContacto() {
        return this.correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public String getExtensionTelefonica() {
        return this.extensionTelefonica;
    }

    public void setExtensionTelefonica(String extensionTelefonica) {
        this.extensionTelefonica = extensionTelefonica;
    }

    public String toString() {
        return "Departamento[nombre=" + this.nombre + ", descripcion=" + this.descripcion + ", correoContacto=" + this.correoContacto + ", extensionTelefonica=" + this.extensionTelefonica + "]";
    }
}
