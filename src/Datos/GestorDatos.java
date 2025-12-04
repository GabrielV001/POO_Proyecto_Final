package Datos;

import Logica.*;
import java.sql.*;
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
        try {
            // Verificar si el correo ya existe
            if (buscarUsuarioPorCorreo(usuario.getCorreoElectronico()) != null) {
                throw new IllegalArgumentException("El correo electrónico ya está registrado");
            }

            Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO usuarios (nombre, correo, contraseña, sal, telefono, rol) " +
                            "VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getCorreoElectronico());
            stmt.setString(3, usuario.getContraseña());
            stmt.setString(4, usuario.getSal());
            stmt.setString(5, usuario.getTelefono());
            stmt.setString(6, usuario.getRol());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La creación del usuario falló, no se insertaron datos");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("La creación del usuario falló, no se obtuvo el ID");
                }
            }

            usuarios.add(usuario);

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar usuario en la base de datos", e);
        }
    }

    public static List<Usuario> obtenerUsuarios() {
        try {
            usuarios.clear();
            Connection conn = ConexionBD.obtenerConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contraseña"),
                        rs.getString("sal"),
                        rs.getString("telefono"),
                        rs.getString("rol")
                );
                usuarios.add(usuario);
            }

            return new ArrayList<>(usuarios);
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener usuarios de la base de datos", e);
        }
    }

    public static Usuario buscarUsuarioPorCorreo(String correo) {
        try {
            Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE correo = ?");
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contraseña"),
                        rs.getString("sal"),
                        rs.getString("telefono"),
                        rs.getString("rol")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario por correo", e);
        }
    }

    // Gestión de Departamentos
    public static void agregarDepartamento(Departamento departamento) {
        try {
            // Verificar si el nombre ya existe
            if (buscarDepartamentoPorNombre(departamento.getNombre()) != null) {
                throw new IllegalArgumentException("Ya existe un departamento con ese nombre");
            }

            Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO departamentos (nombre, descripcion, correo_contacto, extension_telefonica) " +
                            "VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            stmt.setString(1, departamento.getNombre());
            stmt.setString(2, departamento.getDescripcion());
            stmt.setString(3, departamento.getCorreoContacto());
            stmt.setString(4, departamento.getExtensionTelefonica());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La creación del departamento falló, no se insertaron datos");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Si la clase Departamento tiene un campo ID, actualizarlo aquí
                }
            }

            departamentos.add(departamento);

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar departamento en la base de datos", e);
        }
    }

    public static List<Departamento> obtenerDepartamentos() {
        try {
            departamentos.clear();
            Connection conn = ConexionBD.obtenerConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM departamentos");

            while (rs.next()) {
                Departamento departamento = new Departamento(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("correo_contacto"),
                        rs.getString("extension_telefonica")
                );
                departamentos.add(departamento);
            }

            return new ArrayList<>(departamentos);
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener departamentos de la base de datos", e);
        }
    }

    public static Departamento buscarDepartamentoPorNombre(String nombre) {
        try {
            Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM departamentos WHERE nombre = ?");
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Departamento(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("correo_contacto"),
                        rs.getString("extension_telefonica")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar departamento por nombre", e);
        }
    }

    // Gestión de Tickets
    public static void agregarTicket(Ticket ticket) {
        try {
            Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO tickets (asunto, descripcion, estado, usuario_id, departamento_id) " +
                            "VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            stmt.setString(1, ticket.getAsunto());
            stmt.setString(2, ticket.getDescripcion());
            stmt.setString(3, ticket.getEstado());
            stmt.setInt(4, ticket.getUsuario().getId());
            // Asumiendo que Departamento tiene un getId()
            stmt.setInt(5, obtenerDepartamentoId(ticket.getDepartamento().getNombre()));

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La creación del ticket falló, no se insertaron datos");
            }

            tickets.add(ticket);

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar ticket en la base de datos", e);
        }
    }

    private static int obtenerDepartamentoId(String nombreDepartamento) throws SQLException {
        Connection conn = ConexionBD.obtenerConexion();
        PreparedStatement stmt = conn.prepareStatement("SELECT id FROM departamentos WHERE nombre = ?");
        stmt.setString(1, nombreDepartamento);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");
        }
        throw new SQLException("No se encontró el departamento: " + nombreDepartamento);
    }

    public static List<Ticket> obtenerTickets() {
        try {
            tickets.clear();
            Connection conn = ConexionBD.obtenerConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT t.*, u.*, d.* FROM tickets t " +
                            "JOIN usuarios u ON t.usuario_id = u.id " +
                            "JOIN departamentos d ON t.departamento_id = d.id"
            );

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("u.id"),
                        rs.getString("u.nombre"),
                        rs.getString("u.correo"),
                        rs.getString("u.contraseña"),
                        rs.getString("u.sal"),
                        rs.getString("u.telefono"),
                        rs.getString("u.rol")
                );

                Departamento departamento = new Departamento(
                        rs.getString("d.nombre"),
                        rs.getString("d.descripcion"),
                        rs.getString("d.correo_contacto"),
                        rs.getString("d.extension_telefonica")
                );

                Ticket ticket = new Ticket(
                        rs.getString("t.asunto"),
                        rs.getString("t.descripcion"),
                        rs.getString("t.estado"),
                        usuario,
                        departamento
                );

                tickets.add(ticket);
            }

            return new ArrayList<>(tickets);
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener tickets de la base de datos", e);
        }
    }

    // Gestión de Diccionarios
    public static DiccionarioEmocional getDiccionarioEmocional() {
        return diccionarioEmocional;
    }

    public static DiccionarioTecnico getDiccionarioTecnico() {
        return diccionarioTecnico;
    }

    // Actualización de estado de tickets
    public static void actualizarEstadoTicket(Ticket ticket, String nuevoEstado) {
        try {
            Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE tickets SET estado = ? WHERE asunto = ? AND usuario_id = ?"
            );

            stmt.setString(1, nuevoEstado);
            stmt.setString(2, ticket.getAsunto());
            stmt.setInt(3, ticket.getUsuario().getId());

            stmt.executeUpdate();
            ticket.setEstado(nuevoEstado);

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar estado del ticket", e);
        }
    }

    // Limpieza de datos (útil para pruebas)
    public static void limpiarDatos() {
        try {
            Connection conn = ConexionBD.obtenerConexion();
            Statement stmt = conn.createStatement();

            // Orden importante debido a las claves foráneas
            stmt.executeUpdate("DELETE FROM tickets");
            stmt.executeUpdate("DELETE FROM usuarios");
            stmt.executeUpdate("DELETE FROM departamentos");

            usuarios.clear();
            departamentos.clear();
            tickets.clear();

        } catch (SQLException e) {
            throw new RuntimeException("Error al limpiar datos", e);
        }
    }
}