// Usuario.java
package Logica;

public class Usuario {
    private String nombre;
    private String correoElectronico;
    private String contraseña;
    private String telefono;
    private String rol;

    // Constructor vacio
    public Usuario() {
    }

    // Constructor sin telefono
    public Usuario(String nombre, String correoElectronico, String contraseña, String rol) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    // Constructor completo
    public Usuario(String nombre, String correoElectronico, String contraseña, String telefono, String rol) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.rol = rol;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario[nombre=" + nombre + ", correoElectronico=" + correoElectronico +
                ", telefono=" + telefono + ", rol=" + rol + "]";
    }
}
