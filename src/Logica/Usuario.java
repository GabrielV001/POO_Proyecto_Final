package Logica;

public class Usuario {
    private String nombre;
    private String correoElectronico;
    private String contraseña;
    private String telefono;
    private String rol;

    public Usuario() {
    }

    public Usuario(String nombre, String correoElectronico, String contraseña, String rol) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public Usuario(String nombre, String correoElectronico, String contraseña, String telefono, String rol) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.rol = rol;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return this.contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String toString() {
        return "Usuario[nombre=" + this.nombre + ", correoElectronico=" + this.correoElectronico + ", telefono=" + this.telefono + ", rol=" + this.rol + "]";
    }
}

