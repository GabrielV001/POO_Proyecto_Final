package Logica;

import Utilidades.EncriptadorContraseña;

public class Usuario {
    private int id;
    private String nombre;
    private String correoElectronico;
    private String contraseña;
    private String sal;
    private String telefono;
    private String rol;

    public Usuario() {
    }

    public Usuario(String nombre, String correoElectronico, String contraseña, String rol) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.sal = EncriptadorContraseña.generarSal();
        this.contraseña = EncriptadorContraseña.encriptarContraseña(contraseña, this.sal);
        this.rol = rol;
    }

    public Usuario(String nombre, String correoElectronico, String contraseña, String telefono, String rol) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.sal = EncriptadorContraseña.generarSal();
        this.contraseña = EncriptadorContraseña.encriptarContraseña(contraseña, this.sal);
        this.telefono = telefono;
        this.rol = rol;
    }

    // Constructor para cargar desde la base de datos
    public Usuario(int id, String nombre, String correoElectronico, String contraseñaEncriptada,
                   String sal, String telefono, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseñaEncriptada;
        this.sal = sal;
        this.telefono = telefono;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public void setContraseña(String contraseñaPlana) {
        this.sal = EncriptadorContraseña.generarSal();
        this.contraseña = EncriptadorContraseña.encriptarContraseña(contraseñaPlana, this.sal);
    }

    public String getSal() {
        return sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
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

    public boolean verificarContraseña(String contraseñaIntento) {
        String contraseñaEncriptada = EncriptadorContraseña.encriptarContraseña(contraseñaIntento, this.sal);
        return this.contraseña.equals(contraseñaEncriptada);
    }

    @Override
    public String toString() {
        return "Usuario[id=" + id +
                ", nombre=" + nombre +
                ", correoElectronico=" + correoElectronico +
                ", telefono=" + telefono +
                ", rol=" + rol + "]";
    }
}
