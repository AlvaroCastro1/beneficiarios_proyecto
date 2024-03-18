package com.mycompany.beneficiarios;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JOptionPane;

public class Usuario {

    public static boolean iniciarSesion(String nombreUsuario, String contrasena) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = ConexionBD.obtenerConexion();
            String consulta = "SELECT contrasena FROM usuarios WHERE nombre = ?";
            statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombreUsuario);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String contrasenaAlmacenada = resultSet.getString("contrasena");

                if (verificarContrasena(contrasena, contrasenaAlmacenada)) {
                    actualizarUltimaConexion(nombreUsuario);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "La contraseña proporcionada no es correcta.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El usuario " + nombreUsuario + " no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    private static boolean verificarContrasena(String contrasena, String contrasenaAlmacenada) {
        // Verificar contraseñas mediante el cifrado
        String contrasenaCifrada = cifrarContrasena(contrasena);
        return contrasenaCifrada.equals(contrasenaAlmacenada);
    }

    private static void actualizarUltimaConexion(String nombreUsuario) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = ConexionBD.obtenerConexion();
            String consulta = "UPDATE usuarios SET ultima_conexion = ? WHERE nombre = ?";
            statement = conexion.prepareStatement(consulta);
            statement.setTimestamp(1, new Timestamp(new Date().getTime()));
            statement.setString(2, nombreUsuario);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean agregarUsuario(String nombre, String correo, String contrasena) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = ConexionBD.obtenerConexion();
            String consulta = "INSERT INTO usuarios (nombre, correo, contrasena) VALUES (?, ?, ?)";
            statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombre);
            statement.setString(2, correo);
            statement.setString(3, cifrarContrasena(contrasena));
            int filasInsertadas = statement.executeUpdate();

            return filasInsertadas > 0;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Código de error específico para violación de restricción de unicidad
                if (e.getMessage().contains("nombre")) {
                    JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (e.getMessage().contains("correo")) {
                    JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese correo electrónico.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al agregar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                e.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static String cifrarContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(contrasena.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
