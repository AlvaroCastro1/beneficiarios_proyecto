package com.mycompany.beneficiarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "root";
    private static final String PUERTO = "3306";
    private static final String HOST = "localhost";
    private static final String NOMBRE_BD = "Beneficiarios";
    
    
    private static final String URL = "jdbc:mysql://"+HOST+":"+PUERTO+"/"+NOMBRE_BD;
    

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

}
