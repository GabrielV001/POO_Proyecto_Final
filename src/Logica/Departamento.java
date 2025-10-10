package Logica;

public class Departamento {
    private String nombre;
    private String descripcion;
    private String correoContacto;
    private String extensionTelefonica;

    // Constructor vacio
    public Departamento() {
    }

    // Constructor basico
    public Departamento(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Constructor completo
    public Departamento(String nombre, String descripcion, String correoContacto, String extensionTelefonica) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.correoContacto = correoContacto;
        this.extensionTelefonica = extensionTelefonica;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public String getExtensionTelefonica() {
        return extensionTelefonica;
    }

    public void setExtensionTelefonica(String extensionTelefonica) {
        this.extensionTelefonica = extensionTelefonica;
    }

    @Override
    public String toString() {
        return "Departamento[nombre=" + nombre + ", descripcion=" + descripcion +
                ", correoContacto=" + correoContacto + ", extensionTelefonica=" + extensionTelefonica + "]";
    }
}