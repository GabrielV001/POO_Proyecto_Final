package Datos;

import Logica.Departamento;
import Logica.Ticket;
import Logica.Usuario;
import java.util.ArrayList;
import java.util.List;

public class GestorDatos {
    private static List<Usuario> usuarios = new ArrayList();
    private static List<Departamento> departamentos = new ArrayList();
    private static List<Ticket> tickets = new ArrayList();

    public static void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static List<Usuario> obtenerUsuarios() {
        return new ArrayList(usuarios);
    }

    public static Usuario buscarUsuarioPorCorreo(String correo) {
        return (Usuario)usuarios.stream().filter((u) -> u.getCorreoElectronico().equals(correo)).findFirst().orElse((Usuario) null);
    }

    public static void agregarDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }

    public static List<Departamento> obtenerDepartamentos() {
        return new ArrayList(departamentos);
    }

    public static Departamento buscarDepartamentoPorNombre(String nombre) {
        return (Departamento)departamentos.stream().filter((d) -> d.getNombre().equals(nombre)).findFirst().orElse((Departamento) null);
    }

    public static void agregarTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public static List<Ticket> obtenerTickets() {
        return new ArrayList(tickets);
    }
}
