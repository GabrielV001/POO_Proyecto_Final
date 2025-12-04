package Datos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {
    private static Connection conexion = null;

    public static Connection obtenerConexion() {
        if (conexion == null) {
            try {
                Properties props = new Properties();
                FileInputStream fis = new FileInputStream("config.properties");
                props.load(fis);

                String url = props.getProperty("jdbc:mysql://localhost:3306/formas_db");
                String usuario = props.getProperty("Gabo");
                String contraseña = props.getProperty("2301VgA!");

                conexion = DriverManager.getConnection(url, usuario, contraseña);
            } catch (SQLException | IOException e) {
                throw new RuntimeException("Error al conectar con la base de datos", e);
            }
        }
        return conexion;
    }
}