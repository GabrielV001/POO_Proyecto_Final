package Datos;

import Logica.*;
import java.util.ArrayList;
import java.util.List;

public class GestorDatos {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Departamento> departamentos = new ArrayList<>();
    private static List<Ticket> tickets = new ArrayList<>();

    // Metodos para Usuario
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

    // Metodos para Departamento
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

    // Metodos para Ticket
    public static void agregarTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public static List<Ticket> obtenerTickets() {
        return new ArrayList<>(tickets);
    }
}