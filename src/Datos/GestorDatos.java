// GestorDatos.java
package Datos;

import Logica.Departamento;
import Logica.Ticket;
import Logica.Usuario;
import Logica.DiccionarioEmocional;
import Logica.DiccionarioTecnico;
import java.util.ArrayList;
import java.util.List;

public class GestorDatos {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Departamento> departamentos = new ArrayList<>();
    private static List<Ticket> tickets = new ArrayList<>();
    private static DiccionarioEmocional diccionarioEmocional = new DiccionarioEmocional();
    private static DiccionarioTecnico diccionarioTecnico = new DiccionarioTecnico();

    // Gestión de Usuarios
    public static void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static List<Usuario> obtenerUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public static Usuario buscarUsuarioPorCorreo(String correo) {
        return usuarios.stream()
                .filter(u -> u.getCorreoElectronico().equals(correo))
                .findFirst()
                .orElse(null);
    }

    // Gestión de Departamentos
    public static void agregarDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }

    public static List<Departamento> obtenerDepartamentos() {
        return new ArrayList<>(departamentos);
    }

    public static Departamento buscarDepartamentoPorNombre(String nombre) {
        return departamentos.stream()
                .filter(d -> d.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }

    // Gestión de Tickets
    public static void agregarTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public static List<Ticket> obtenerTickets() {
        return new ArrayList<>(tickets);
    }

    // Gestión de Diccionarios
    public static DiccionarioEmocional getDiccionarioEmocional() {
        return diccionarioEmocional;
    }

    public static DiccionarioTecnico getDiccionarioTecnico() {
        return diccionarioTecnico;
    }

    // Métodos para actualizar el estado de los tickets
    public static void actualizarEstadoTicket(Ticket ticket, String nuevoEstado) {
        ticket.setEstado(nuevoEstado);
    }

    // Método para limpiar datos (útil para pruebas)
    public static void limpiarDatos() {
        usuarios.clear();
        departamentos.clear();
        tickets.clear();
    }
}