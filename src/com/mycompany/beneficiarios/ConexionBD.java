package com.mycompany.beneficiarios;

import io.github.cdimascio.dotenv.Dotenv;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final Dotenv dotenv;

    static {
        Dotenv tempDotenv = null;
        try {
            String rutaArchivoEnv = "./../.env"; // Especifica la ruta donde se encuentra tu archivo .env
            tempDotenv = Dotenv.configure().directory(rutaArchivoEnv).load();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: No se encontró el archivo .env", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        dotenv = tempDotenv;
    }

    private static final String USUARIO = dotenv.get("USUARIO");
    private static final String CONTRASENA = dotenv.get("CONTRASENA");
    private static final String PUERTO = dotenv.get("PUERTO");
    private static final String HOST = dotenv.get("HOST");
    private static final String NOMBRE_BD = dotenv.get("NOMBRE_BD");

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PUERTO + "/" + NOMBRE_BD;

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
