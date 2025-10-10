package ui;

import Logica.*;
import Datos.GestorDatos;
import java.util.Scanner;

public class MenuPrincipal {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Gestionar Usuarios");
            System.out.println("2. Gestionar Departamentos");
            System.out.println("3. Gestionar Tickets");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuUsuarios();
                    break;
                case 2:
                    menuDepartamentos();
                    break;
                case 3:
                    menuTickets();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

    private static void menuUsuarios() {
        System.out.println("\n=== GESTION DE USUARIOS ===");
        System.out.println("1. Registrar nuevo usuario");
        System.out.println("2. Listar usuarios");
        System.out.print("Seleccione una opcion: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                registrarUsuario();
                break;
            case 2:
                listarUsuarios();
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }

    private static void registrarUsuario() {
        System.out.println("\n== Registro de Usuario ==");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();
        System.out.print("Telefono (opcional, presione Enter para omitir): ");
        String telefono = scanner.nextLine();
        System.out.print("Rol (administrador/estudiante/funcionario): ");
        String rol = scanner.nextLine();

        Usuario usuario = new Usuario(nombre, correo, contraseña, telefono, rol);
        GestorDatos.agregarUsuario(usuario);
        System.out.println("Usuario registrado exitosamente");
    }

    private static void listarUsuarios() {
        System.out.println("\n== Lista de Usuarios ==");
        for (Usuario usuario : GestorDatos.obtenerUsuarios()) {
            System.out.println(usuario);
        }
    }

    private static void menuDepartamentos() {
        System.out.println("\n=== GESTIÓN DE DEPARTAMENTOS ===");
        System.out.println("1. Registrar nuevo departamento");
        System.out.println("2. Listar departamentos");
        System.out.print("Seleccione una opcion: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                registrarDepartamento();
                break;
            case 2:
                listarDepartamentos();
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }

    private static void registrarDepartamento() {
        System.out.println("\n== Registro de Departamento ==");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();
        System.out.print("Correo de contacto (opcional, presione Enter para omitir): ");
        String correoContacto = scanner.nextLine();
        System.out.print("Extension telefonica (opcional, presione Enter para omitir): ");
        String extension = scanner.nextLine();

        Departamento departamento = new Departamento(nombre, descripcion, correoContacto, extension);
        GestorDatos.agregarDepartamento(departamento);
        System.out.println("Departamento registrado exitosamente");
    }

    private static void listarDepartamentos() {
        System.out.println("\n== Lista de Departamentos ==");
        for (Departamento departamento : GestorDatos.obtenerDepartamentos()) {
            System.out.println(departamento);
        }
    }

    private static void menuTickets() {
        System.out.println("\n=== GESTION DE TICKETS ===");
        System.out.println("1. Registrar nuevo ticket");
        System.out.println("2. Listar tickets");
        System.out.print("Seleccione una opcion: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                registrarTicket();
                break;
            case 2:
                listarTickets();
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }

    private static void registrarTicket() {
        System.out.println("\n== Registro de Ticket ==");

        // Verificar si hay usuarios y departamentos registrados
        if (GestorDatos.obtenerUsuarios().isEmpty()) {
            System.out.println("Error: Debe registrar al menos un usuario primero.");
            return;
        }
        if (GestorDatos.obtenerDepartamentos().isEmpty()) {
            System.out.println("Error: Debe registrar al menos un departamento primero.");
            return;
        }

        System.out.print("Asunto: ");
        String asunto = scanner.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();

        // Seleccionar usuario
        System.out.println("Usuarios disponibles:");
        for (Usuario usuario : GestorDatos.obtenerUsuarios()) {
            System.out.println("- " + usuario.getCorreoElectronico());
        }
        System.out.print("Ingrese el correo del usuario: ");
        String correoUsuario = scanner.nextLine();
        Usuario usuario = GestorDatos.buscarUsuarioPorCorreo(correoUsuario);

        if (usuario == null) {
            System.out.println("Error: Usuario no encontrado.");
            return;
        }

        // Seleccionar departamento
        System.out.println("Departamentos disponibles:");
        for (Departamento depto : GestorDatos.obtenerDepartamentos()) {
            System.out.println("- " + depto.getNombre());
        }
        System.out.print("Ingrese el nombre del departamento: ");
        String nombreDepartamento = scanner.nextLine();
        Departamento departamento = GestorDatos.buscarDepartamentoPorNombre(nombreDepartamento);

        if (departamento == null) {
            System.out.println("Error: Departamento no encontrado.");
            return;
        }

        Ticket ticket = new Ticket(asunto, descripcion, usuario, departamento);
        GestorDatos.agregarTicket(ticket);
        System.out.println("Ticket registrado exitosamente");
    }

    private static void listarTickets() {
        System.out.println("\n== Lista de Tickets ==");
        for (Ticket ticket : GestorDatos.obtenerTickets()) {
            System.out.println(ticket);
        }
    }
}