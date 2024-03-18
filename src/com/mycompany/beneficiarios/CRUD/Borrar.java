/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.beneficiarios.CRUD;

import com.mycompany.beneficiarios.ConexionBD;
import com.mycompany.beneficiarios.Menu;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Alvaro
 */
public class Borrar extends javax.swing.JFrame {

    private String usuario;
    private File archivoAnverso, archivoInverso;
    private String id;

    /**
     * Creates new form Crear
     */
    public Borrar() {
        setLocationRelativeTo(null);
        initComponents();
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    private void setId(String id) {
        this.id = id;
    }

    private void mostrarDatos() throws IOException {
        try {
            // Obtener una conexión a la base de datos
            Connection conexion = ConexionBD.obtenerConexion();

            // Preparar la consulta para obtener los datos del beneficiario
            String consulta = "SELECT * FROM Beneficiarios WHERE id=?";
            PreparedStatement pstmt = conexion.prepareStatement(consulta);

            // Establecer el parámetro de la consulta (ID del beneficiario)
            pstmt.setString(1, this.id);

            // Ejecutar la consulta y obtener el resultado
            ResultSet rs = pstmt.executeQuery();

            // Verificar si se encontraron resultados
            if (rs.next()) {
                // Mostrar los datos del beneficiario en los campos de texto correspondientes
                txt_nombre.setText(rs.getString("nombre_completo"));
                txt_nacimiento.setDate(rs.getDate("fecha_nacimiento"));
                txt_direccion.setText(rs.getString("direccion"));
                txt_cp.setText(rs.getString("codigo_postal"));
                txt_curp.setText(rs.getString("curp"));
                txt_clave_elector.setText(rs.getString("clave_elector"));
                txt_OCR.setText(rs.getString("ocr"));
                txt_vigenciaINE.setDate(rs.getDate("vigencia_ine"));
                txt_correo.setText(rs.getString("correo_electronico"));
                txt_facebook.setText(rs.getString("facebook"));
                txt_instagram.setText(rs.getString("instagram"));

                byte[] imagenAnverso = rs.getBytes("imagen_anverso_ine");
                ImageIcon icono = new ImageIcon(imagenAnverso);
                Image imagen_anv_Escalada = icono.getImage().getScaledInstance(
                        lb_anverso.getWidth(), lb_anverso.getHeight(), Image.SCALE_SMOOTH);
                lb_anverso.setIcon(new ImageIcon(imagen_anv_Escalada));

                File tempFile = File.createTempFile("anverso_", ".png");

                // Escribir los bytes de la imagen en el archivo temporal
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    fos.write(imagenAnverso);
                }

                // Asignar el archivo temporal a la variable archivoAnverso
                archivoAnverso = tempFile;

                byte[] imagenReverso = rs.getBytes("imagen_reverso_ine");
                ImageIcon icono_rev = new ImageIcon(imagenReverso);
                Image imagen_rev_Escalada = icono_rev.getImage().getScaledInstance(
                        lb_inverso.getWidth(), lb_inverso.getHeight(), Image.SCALE_SMOOTH);
                lb_inverso.setIcon(new ImageIcon(imagen_rev_Escalada));

                File tempFileReverso = File.createTempFile("reverso_", ".png");

                // Escribir los bytes de la imagen en el archivo temporal
                try (FileOutputStream fos = new FileOutputStream(tempFileReverso)) {
                    fos.write(imagenReverso);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // Asignar el archivo temporal a la variable archivoReverso
                archivoInverso = tempFileReverso;
            } else {
                // Mostrar un mensaje indicando que no se encontró ningún usuario con el ID especificado
                JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el ID especificado.");
            }

            // Cerrar la conexión y liberar recursos
            rs.close();
            pstmt.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos del beneficiario.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        txt_nombre = new javax.swing.JTextField();
        txt_nacimiento = new com.toedter.calendar.JDateChooser();
        txt_direccion = new javax.swing.JTextField();
        txt_cp = new javax.swing.JTextField();
        txt_curp = new javax.swing.JTextField();
        txt_clave_elector = new javax.swing.JTextField();
        txt_OCR = new javax.swing.JTextField();
        txt_vigenciaINE = new com.toedter.calendar.JDateChooser();
        txt_correo = new javax.swing.JTextField();
        txt_facebook = new javax.swing.JTextField();
        txt_instagram = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lb_inverso = new javax.swing.JLabel();
        lb_anverso = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btn_borrar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Eliminar Beneficiario");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txt_nombre.setEnabled(false);

        txt_nacimiento.setEnabled(false);

        txt_direccion.setEnabled(false);

        txt_cp.setEnabled(false);

        txt_curp.setEnabled(false);

        txt_clave_elector.setEnabled(false);

        txt_OCR.setEnabled(false);

        txt_vigenciaINE.setEnabled(false);

        txt_correo.setEnabled(false);

        txt_facebook.setEnabled(false);

        txt_instagram.setEnabled(false);

        jLabel1.setText("Nombre");

        jLabel2.setText("Fecha de nacimiento");

        jLabel3.setText("Direccion");

        jLabel4.setText("Codigo postal");

        jLabel5.setText("CURP");

        jLabel6.setText("Clave elector");

        jLabel7.setText("OCR");

        jLabel8.setText("Vigencia del INE ");

        jLabel11.setText("correo_electronico");

        jLabel13.setText("facebook");

        jLabel14.setText("instagram");

        lb_inverso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lb_anverso.setBackground(new java.awt.Color(102, 255, 255));
        lb_anverso.setForeground(new java.awt.Color(255, 255, 0));
        lb_anverso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_idKeyTyped(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_borrar.setText("BORRAR");
        btn_borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_borrarActionPerformed(evt);
            }
        });

        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel9.setText("Escriba el ID del beneficiario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_borrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_nombre)
                            .addComponent(txt_nacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_direccion)
                            .addComponent(txt_cp)
                            .addComponent(txt_curp)
                            .addComponent(txt_clave_elector)
                            .addComponent(txt_OCR)
                            .addComponent(txt_vigenciaINE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_correo)
                            .addComponent(txt_facebook)
                            .addComponent(txt_instagram, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(58, 58, 58)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_inverso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_anverso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addComponent(btn_borrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addComponent(txt_nacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(20, 20, 20)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(txt_cp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(txt_curp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(txt_clave_elector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(txt_OCR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8))
                                    .addComponent(txt_vigenciaINE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_facebook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_instagram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(jButton2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(lb_inverso, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_anverso, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(360, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Menu m = new Menu();
        m.setVisible(true);
        m.setUsuario(this.usuario);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            setId(txt_id.getText().trim());
            clear();
            mostrarDatos();
        } catch (IOException ex) {
            Logger.getLogger(Borrar.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isDigit(c) || txt_cp.getText().length() >= 6) {
            // Reproducir alerta (puedes cambiar esto por tu propia lógica de alerta)
            Toolkit.getDefaultToolkit().beep();
            evt.consume(); // Consumir el evento para evitar que se ingrese el carácter
        }
    }//GEN-LAST:event_txt_idKeyTyped

    private void btn_borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_borrarActionPerformed
        if (existeBeneficiario(txt_id.getText().trim())) {
            // Mostrar mensaje de advertencia antes de eliminar al beneficiario
            int opcion = JOptionPane.showConfirmDialog(null, "Estás a punto de eliminar al beneficiario con ID " + id + ". ¿Estás seguro?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (opcion == JOptionPane.YES_OPTION) {
                // Si el usuario confirma la eliminación, proceder a borrar el beneficiario
                try (Connection conexion = ConexionBD.obtenerConexion()) {
                    // Preparar la consulta para eliminar al beneficiario con el ID proporcionado
                    String consulta = "DELETE FROM Beneficiarios WHERE id = ?";
                    try (PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
                        // Establecer el parámetro ID en la consulta
                        pstmt.setString(1, id);
                        // Ejecutar la consulta para eliminar al beneficiario
                        pstmt.executeUpdate();
                        // Mostrar un mensaje de éxito
                        JOptionPane.showMessageDialog(null, "El beneficiario con ID " + id + " ha sido eliminado correctamente.");
                        clear();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar al beneficiario.");
                }
            }
        } else {
            // Mostrar mensaje de error si no se encuentra un beneficiario con el ID proporcionado
            JOptionPane.showMessageDialog(null, "No hay un beneficiario con el ID " + id, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_borrarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clear();
    }//GEN-LAST:event_jButton2ActionPerformed

    private boolean existeBeneficiario(String id) {
        // Declarar una variable para almacenar si el beneficiario existe o no
        boolean existe = false;

        // Establecer la conexión a la base de datos
        try (Connection conexion = ConexionBD.obtenerConexion()) {
            // Preparar la consulta para verificar si existe el beneficiario con el ID proporcionado
            String consulta = "SELECT COUNT(*) AS total FROM Beneficiarios WHERE id = ?";
            try (PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
                // Establecer el parámetro ID en la consulta
                pstmt.setString(1, id);

                // Ejecutar la consulta y obtener el resultado
                try (ResultSet rs = pstmt.executeQuery()) {
                    // Verificar si se encontró algún resultado
                    if (rs.next()) {
                        // Obtener el número total de filas encontradas
                        int total = rs.getInt("total");
                        // Si el total es mayor que 0, significa que el beneficiario existe
                        existe = total > 0;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejar cualquier excepción SQL aquí
        }

        return existe;
    }

    public void clear() {
        txt_nombre.setText("");
        txt_nacimiento.setDate(null);
        txt_direccion.setText("");
        txt_cp.setText("");
        txt_curp.setText("");
        txt_OCR.setText("");
        txt_vigenciaINE.setDate(null);

        // Limpiar imágenes
        lb_anverso.setIcon(null);
        lb_inverso.setIcon(null);

        txt_correo.setText("");
        txt_clave_elector.setText("");
        txt_facebook.setText("");
        txt_instagram.setText("");

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Borrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Borrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Borrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Borrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Borrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_borrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lb_anverso;
    private javax.swing.JLabel lb_inverso;
    private javax.swing.JTextField txt_OCR;
    private javax.swing.JTextField txt_clave_elector;
    private javax.swing.JTextField txt_correo;
    private javax.swing.JTextField txt_cp;
    private javax.swing.JTextField txt_curp;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_facebook;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_instagram;
    private com.toedter.calendar.JDateChooser txt_nacimiento;
    private javax.swing.JTextField txt_nombre;
    private com.toedter.calendar.JDateChooser txt_vigenciaINE;
    // End of variables declaration//GEN-END:variables
}
