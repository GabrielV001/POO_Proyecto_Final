package ui;

import Datos.GestorDatos;
import Logica.*;
import java.util.List;
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
            System.out.println("4. Gestionar Diccionarios");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> menuUsuarios();
                case 2 -> menuDepartamentos();
                case 3 -> menuTickets();
                case 4 -> menuDiccionarios();
                case 5 -> salir = true;
                default -> System.out.println("Opcion no válida");
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
            case 1 -> registrarUsuario();
            case 2 -> listarUsuarios();
            default -> System.out.println("Opcion no valida");
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
            case 1 -> registrarDepartamento();
            case 2 -> listarDepartamentos();
            default -> System.out.println("Opcion no valida");
        }
    }

    private static void menuTickets() {
        System.out.println("\n=== GESTIÓN DE TICKETS ===");
        System.out.println("1. Registrar nuevo ticket");
        System.out.println("2. Listar tickets");
        System.out.println("3. Analizar ticket (BoW)");
        System.out.println("4. Actualizar estado de ticket");
        System.out.print("Seleccione una opcion: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1 -> registrarTicket();
            case 2 -> listarTickets();
            case 3 -> analizarTicket();
            case 4 -> actualizarEstadoTicket();
            default -> System.out.println("Opcion no valida");
        }
    }

    private static void menuDiccionarios() {
        System.out.println("\n=== GESTIÓN DE DICCIONARIOS ===");
        System.out.println("1. Gestionar Diccionario Emocional");
        System.out.println("2. Gestionar Diccionario Tecnico");
        System.out.print("Seleccione una opcion: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1 -> gestionarDiccionarioEmocional();
            case 2 -> gestionarDiccionarioTecnico();
            default -> System.out.println("Opcion no valida");
        }
    }

    private static void registrarUsuario() {
        System.out.println("\n== Registro de Usuario ==");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo electronico: ");
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

    private static void registrarDepartamento() {
        System.out.println("\n== Registro de Departamento ==");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();
        System.out.print("Correo de contacto (opcional): ");
        String correoContacto = scanner.nextLine();
        System.out.print("Extension telefonica (opcional): ");
        String extension = scanner.nextLine();

        Departamento departamento = new Departamento(nombre, descripcion, correoContacto, extension);
        GestorDatos.agregarDepartamento(departamento);
        System.out.println("Departamento registrado exitosamente");
    }

    private static void registrarTicket() {
        if (GestorDatos.obtenerUsuarios().isEmpty() || GestorDatos.obtenerDepartamentos().isEmpty()) {
            System.out.println("Error: Debe haber usuarios y departamentos registrados");
            return;
        }

        System.out.println("\n== Registro de Ticket ==");
        System.out.print("Asunto: ");
        String asunto = scanner.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();

        // Mostrar usuarios disponibles
        System.out.println("\nUsuarios disponibles:");
        GestorDatos.obtenerUsuarios().forEach(u ->
                System.out.println("- " + u.getCorreoElectronico()));
        System.out.print("Ingrese el correo del usuario: ");
        String correoUsuario = scanner.nextLine();
        Usuario usuario = GestorDatos.buscarUsuarioPorCorreo(correoUsuario);

        if (usuario == null) {
            System.out.println("Error: Usuario no encontrado");
            return;
        }

        // Mostrar departamentos disponibles
        System.out.println("\nDepartamentos disponibles:");
        GestorDatos.obtenerDepartamentos().forEach(d ->
                System.out.println("- " + d.getNombre()));
        System.out.print("Ingrese el nombre del departamento: ");
        String nombreDepartamento = scanner.nextLine();
        Departamento departamento = GestorDatos.buscarDepartamentoPorNombre(nombreDepartamento);

        if (departamento == null) {
            System.out.println("Error: Departamento no encontrado");
            return;
        }

        Ticket ticket = new Ticket(asunto, descripcion, usuario, departamento);
        GestorDatos.agregarTicket(ticket);
        System.out.println("Ticket registrado exitosamente");
    }

    private static void analizarTicket() {
        if (GestorDatos.obtenerTickets().isEmpty()) {
            System.out.println("No hay tickets para analizar");
            return;
        }

        System.out.println("\nSeleccione el ticket a analizar:");
        List<Ticket> tickets = GestorDatos.obtenerTickets();
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println((i+1) + ". " + tickets.get(i).getAsunto());
        }

        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < tickets.size()) {
            Ticket ticket = tickets.get(index);
            String[] palabras = AnalizadorBoW.preprocesarTexto(ticket.getDescripcion());
            var vector = AnalizadorBoW.vectorizar(palabras);

            // Análisis emocional
            System.out.println("\nAnalisis emocional:");
            var emociones = GestorDatos.getDiccionarioEmocional().analizarEmociones(vector);
            emociones.forEach((palabra, emocion) ->
                    System.out.println("Palabra: " + palabra + " -> Emocion: " + emocion));

            // Análisis técnico
            System.out.println("\nAnalisis tecnico:");
            var categorias = GestorDatos.getDiccionarioTecnico().analizarCategoriaTecnica(vector);
            categorias.forEach((palabra, categoria) ->
                    System.out.println("Palabra: " + palabra + " -> Categoria: " + categoria));
        } else {
            System.out.println("Indice de ticket invalido");
        }
    }

    private static void actualizarEstadoTicket() {
        if (GestorDatos.obtenerTickets().isEmpty()) {
            System.out.println("No hay tickets para actualizar");
            return;
        }

        System.out.println("\nSeleccione el ticket a actualizar:");
        List<Ticket> tickets = GestorDatos.obtenerTickets();
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println((i+1) + ". " + tickets.get(i).getAsunto() + " (Estado actual: " + tickets.get(i).getEstado() + ")");
        }

        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < tickets.size()) {
            System.out.println("Seleccione el nuevo estado:");
            System.out.println("1. Nuevo");
            System.out.println("2. En progreso");
            System.out.println("3. Resuelto");

            int opcionEstado = scanner.nextInt();
            scanner.nextLine();

            String nuevoEstado = switch (opcionEstado) {
                case 1 -> "nuevo";
                case 2 -> "en progreso";
                case 3 -> "resuelto";
                default -> null;
            };

            if (nuevoEstado != null) {
                GestorDatos.actualizarEstadoTicket(tickets.get(index), nuevoEstado);
                System.out.println("Estado actualizado exitosamente");
            } else {
                System.out.println("Estado invalido");
            }
        } else {
            System.out.println("Indice de ticket invalido");
        }
    }

    private static void listarUsuarios() {
        System.out.println("\n== Lista de Usuarios ==");
        GestorDatos.obtenerUsuarios().forEach(System.out::println);
    }

    private static void listarDepartamentos() {
        System.out.println("\n== Lista de Departamentos ==");
        GestorDatos.obtenerDepartamentos().forEach(System.out::println);
    }

    private static void listarTickets() {
        System.out.println("\n== Lista de Tickets ==");
        GestorDatos.obtenerTickets().forEach(System.out::println);
    }

    private static void gestionarDiccionarioEmocional() {
        System.out.println("\n=== GESTION DICCIONARIO EMOCIONAL ===");
        System.out.println("1. Agregar palabra");
        System.out.println("2. Eliminar palabra");
        System.out.print("Seleccione una opcion: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            System.out.print("Ingrese la palabra: ");
            String palabra = scanner.nextLine();
            System.out.print("Ingrese la emocion: ");
            String emocion = scanner.nextLine();
            GestorDatos.getDiccionarioEmocional().agregarPalabra(palabra, emocion);
            System.out.println("Palabra agregada exitosamente");
        } else if (opcion == 2) {
            System.out.print("Ingrese la palabra a eliminar: ");
            String palabra = scanner.nextLine();
            GestorDatos.getDiccionarioEmocional().eliminarPalabra(palabra);
            System.out.println("Palabra eliminada exitosamente");
        }
    }

    private static void gestionarDiccionarioTecnico() {
        System.out.println("\n=== GESTION DICCIONARIO TECNICO ===");
        System.out.println("1. Agregar palabra");
        System.out.println("2. Eliminar palabra");
        System.out.print("Seleccione una opcion: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            System.out.print("Ingrese la palabra: ");
            String palabra = scanner.nextLine();
            System.out.print("Ingrese la categoría tecnica: ");
            String categoria = scanner.nextLine();
            GestorDatos.getDiccionarioTecnico().agregarPalabra(palabra, categoria);
            System.out.println("Palabra agregada exitosamente");
        } else if (opcion == 2) {
            System.out.print("Ingrese la palabra a eliminar: ");
            String palabra = scanner.nextLine();
            GestorDatos.getDiccionarioTecnico().eliminarPalabra(palabra);
            System.out.println("Palabra eliminada exitosamente");
        }
    }
}