/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.beneficiarios.CRUD;

import com.mycompany.beneficiarios.ConexionBD;
import com.mycompany.beneficiarios.Menu;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Alvaro
 */
public class Actualizar extends javax.swing.JFrame {

    private File archivoAnverso, archivoInverso;
    private String usuario;

    /**
     * Creates new form Crear
     */
    public Actualizar() {
        setLocationRelativeTo(null);
        initComponents();
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    private static String id;

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
        btn_seleccionar_anverso = new javax.swing.JButton();
        btn_seleccionar_inverso = new javax.swing.JButton();
        txt_correo = new javax.swing.JTextField();
        txt_correoConfirm = new javax.swing.JTextField();
        txt_facebook = new javax.swing.JTextField();
        txt_instagram = new javax.swing.JTextField();
        btn_guardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lb_inverso = new javax.swing.JLabel();
        lb_anverso = new javax.swing.JLabel();
        ayuda_nombre = new javax.swing.JLabel();
        ayuda_f_nac = new javax.swing.JLabel();
        ayuda_direccion = new javax.swing.JLabel();
        ayuda_cp = new javax.swing.JLabel();
        ayuda_curp = new javax.swing.JLabel();
        ayuda_elector = new javax.swing.JLabel();
        ayuda_ocr = new javax.swing.JLabel();
        ayuda_vigencia = new javax.swing.JLabel();
        ayuda_fotos = new javax.swing.JLabel();
        ayuda_correo = new javax.swing.JLabel();
        ayuda_facebook = new javax.swing.JLabel();
        ayuda_instagram = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Actualizar Beneficiario");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombreKeyTyped(evt);
            }
        });

        txt_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_direccionKeyTyped(evt);
            }
        });

        txt_cp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cpKeyTyped(evt);
            }
        });

        txt_curp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_curpKeyTyped(evt);
            }
        });

        txt_clave_elector.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_clave_electorKeyTyped(evt);
            }
        });

        txt_OCR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_OCRKeyTyped(evt);
            }
        });

        btn_seleccionar_anverso.setText("seleccionar");
        btn_seleccionar_anverso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_seleccionar_anversoActionPerformed(evt);
            }
        });

        btn_seleccionar_inverso.setText("seleccionar");
        btn_seleccionar_inverso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_seleccionar_inversoActionPerformed(evt);
            }
        });

        txt_correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_correoKeyTyped(evt);
            }
        });

        txt_correoConfirm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_correoConfirmKeyTyped(evt);
            }
        });

        txt_facebook.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_facebookKeyTyped(evt);
            }
        });

        txt_instagram.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_instagramKeyTyped(evt);
            }
        });

        btn_guardar.setText("Actualizar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre");

        jLabel2.setText("Fecha de nacimiento");

        jLabel3.setText("Direccion");

        jLabel4.setText("Codigo postal");

        jLabel5.setText("CURP");

        jLabel6.setText("Clave elector");

        jLabel7.setText("OCR");

        jLabel8.setText("Vigencia del INE ");

        jLabel9.setText("imagen_anverso_ine");

        jLabel10.setText("imagen_Inverso_ine");

        jLabel11.setText("correo_electronico");

        jLabel12.setText("correo_electronico");

        jLabel13.setText("facebook");

        jLabel14.setText("instagram");

        lb_inverso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lb_anverso.setBackground(new java.awt.Color(102, 255, 255));
        lb_anverso.setForeground(new java.awt.Color(255, 255, 0));
        lb_anverso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ayuda_nombre.setIcon(new javax.swing.ImageIcon("src/main/java/com/mycompany/beneficiarios/imagenes/ayuda.png")); // NOI18N
        ayuda_nombre.setToolTipText(" Introduce el nombre completo tal como aparece en el documento de identidad. Ejemplo: Juan Pérez López");
        ayuda_nombre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ayuda_f_nac.setIcon(new javax.swing.ImageIcon("src/main/java/com/mycompany/beneficiarios/imagenes/ayuda.png")); // NOI18N
        ayuda_f_nac.setToolTipText("Introduce la fecha de nacimiento en el calendario desplegable. Ejemplo: 1 ene 1990");
        ayuda_f_nac.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ayuda_direccion.setIcon(new javax.swing.ImageIcon("src/main/java/com/mycompany/beneficiarios/imagenes/ayuda.png")); // NOI18N
        ayuda_direccion.setToolTipText("Introduce la dirección completa, incluyendo calle, número, colonia, ciudad y estado. ");
        ayuda_direccion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ayuda_cp.setIcon(new javax.swing.ImageIcon("src/main/java/com/mycompany/beneficiarios/imagenes/ayuda.png")); // NOI18N
        ayuda_cp.setToolTipText(" Introduce el código postal de la dirección. Ejemplo: 12345");
        ayuda_cp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ayuda_curp.setIcon(new javax.swing.ImageIcon("src/main/java/com/mycompany/beneficiarios/imagenes/ayuda.png")); // NOI18N
        ayuda_curp.setToolTipText(" Introduce el CURP, clave alfanumérica única de 18 dígitos. Ejemplo: AAAA000101HDFXXX01");
        ayuda_curp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ayuda_elector.setIcon(new javax.swing.ImageIcon("src/main/java/com/mycompany/beneficiarios/imagenes/ayuda.png")); // NOI18N
        ayuda_elector.setToolTipText("Introduce la clave de elector, que se encuentra en la credencial de elector, 18 dígitos.");
        ayuda_elector.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ayuda_ocr.setIcon(new javax.swing.ImageIcon("src/main/java/com/mycompany/beneficiarios/imagenes/ayuda.png")); // NOI18N
        ayuda_ocr.setToolTipText(" Introduce el OCR (Optical Character Recognition). 13 números");
        ayuda_ocr.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ayuda_vigencia.setIcon(new javax.swing.ImageIcon("src/main/java/com/mycompany/beneficiarios/imagenes/ayuda.png")); // NOI18N
        ayuda_vigencia.setToolTipText("Seleccione la fecha de  vencimiento del INE en el calendario desplegable. Ejemplo: 1 ene 2030.");
        ayuda_vigencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ayuda_fotos.setIcon(new javax.swing.ImageIcon("src/main/java/com/mycompany/beneficiarios/imagenes/ayuda.png")); // NOI18N
        ayuda_fotos.setToolTipText("Seleccione en cada campo la imagen que corresponde. \\nIMPORTANTE: Solo se aceptan formatos jpg, png, jpeg");
        ayuda_fotos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ayuda_correo.setIcon(new javax.swing.ImageIcon("src/main/java/com/mycompany/beneficiarios/imagenes/ayuda.png")); // NOI18N
        ayuda_correo.setToolTipText("Introduce la dirección de correo electrónico y confirmalo en esta campo. Ejemplo: ejemplo@correo.com");
        ayuda_correo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ayuda_facebook.setIcon(new javax.swing.ImageIcon("src/main/java/com/mycompany/beneficiarios/imagenes/ayuda.png")); // NOI18N
        ayuda_facebook.setToolTipText("Introduce el nombre de usuario de Facebook, Se puede dejar en blanco. Ejemplo: ejemplo.usuario");
        ayuda_facebook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ayuda_instagram.setIcon(new javax.swing.ImageIcon("src/main/java/com/mycompany/beneficiarios/imagenes/ayuda.png")); // NOI18N
        ayuda_instagram.setToolTipText("Introduce el nombre de usuario de Instagram, Se puede dejar en blanco. Ejemplo: ejemplo_usuario");
        ayuda_instagram.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

        jLabel15.setText("Escriba el ID del beneficiario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
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
                    .addComponent(txt_nombre)
                    .addComponent(txt_nacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_direccion)
                    .addComponent(txt_cp)
                    .addComponent(txt_curp)
                    .addComponent(txt_clave_elector)
                    .addComponent(txt_OCR)
                    .addComponent(txt_vigenciaINE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_correo)
                    .addComponent(txt_correoConfirm)
                    .addComponent(txt_facebook)
                    .addComponent(txt_instagram)
                    .addComponent(btn_seleccionar_anverso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_seleccionar_inverso, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ayuda_direccion)
                            .addComponent(ayuda_cp)
                            .addComponent(ayuda_curp)
                            .addComponent(ayuda_elector)
                            .addComponent(ayuda_ocr)
                            .addComponent(ayuda_vigencia)
                            .addComponent(ayuda_fotos)
                            .addComponent(ayuda_correo)
                            .addComponent(ayuda_facebook)
                            .addComponent(ayuda_instagram)
                            .addComponent(ayuda_f_nac))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_inverso, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(txt_id)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton1))
                                .addComponent(lb_anverso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ayuda_nombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(58, 58, 58))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ayuda_nombre, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(ayuda_f_nac, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(txt_nacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ayuda_direccion, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txt_cp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(ayuda_cp))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txt_curp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ayuda_curp, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txt_clave_elector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ayuda_elector, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(txt_OCR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ayuda_ocr))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(ayuda_vigencia, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(txt_vigenciaINE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(btn_seleccionar_anverso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ayuda_fotos)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(btn_seleccionar_inverso))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(txt_correoConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ayuda_correo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txt_facebook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ayuda_facebook))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ayuda_instagram, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txt_instagram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(btn_guardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_anverso, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lb_inverso, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_seleccionar_anversoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seleccionar_anversoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar imagen del ANVERSO del INE");
        // Filtrar los archivos para mostrar solo imágenes
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            archivoAnverso = fileChooser.getSelectedFile(); // Asignar el archivo seleccionado a archivoAnverso
            String fileName = archivoAnverso.getName();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

            // Verificar si la extensión del archivo es una imagen válida
            if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg")
                    || extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("gif")) {
                // Aquí puedes usar el archivo seleccionado (archivoAnverso)
                // Por ejemplo, puedes obtener la ruta del archivo:
                String filePath = archivoAnverso.getAbsolutePath();
                System.out.println("Ruta del archivo seleccionado: " + filePath);
                ImageIcon imageIcon = new ImageIcon(filePath);
                // Escalamos la imagen para que se ajuste al tamaño del JLabel
                Image image = imageIcon.getImage().getScaledInstance(lb_anverso.getWidth(), lb_anverso.getHeight(), Image.SCALE_SMOOTH);
                lb_anverso.setIcon(new ImageIcon(image));
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el ANVERSO del INE (jpg, jpeg, png, gif).");
            }
        }
    }//GEN-LAST:event_btn_seleccionar_anversoActionPerformed

    private void btn_seleccionar_inversoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seleccionar_inversoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar imagen del INVERSO del INE");
        // Filtrar los archivos para mostrar solo imágenes
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            archivoInverso = fileChooser.getSelectedFile(); // Asignar el archivo seleccionado a archivoAnverso
            String fileName = archivoInverso.getName();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

            // Verificar si la extensión del archivo es una imagen válida
            if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg")
                    || extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("gif")) {
                // Aquí puedes usar el archivo seleccionado (archivoAnverso)
                // Por ejemplo, puedes obtener la ruta del archivo:
                String filePath = archivoInverso.getAbsolutePath();
                System.out.println("Ruta del archivo seleccionado: " + filePath);
                ImageIcon imageIcon = new ImageIcon(filePath);
                // Escalamos la imagen para que se ajuste al tamaño del JLabel
                Image image = imageIcon.getImage().getScaledInstance(lb_inverso.getWidth(), lb_inverso.getHeight(), Image.SCALE_SMOOTH);
                lb_inverso.setIcon(new ImageIcon(image));
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el INVERSO del INE (jpg, jpeg, png, gif).");
            }
        }
    }//GEN-LAST:event_btn_seleccionar_inversoActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        if (validarFechaNacimiento() && validarVigenciaINE() && validarMayorEdad() && validarCurp() && validarCorreo() && validarOCR() && validarImagenesSeleccionadas() && validarClaveElector()) {
            try {
                // Obtener una conexión a la base de datos
                Connection conexion = ConexionBD.obtenerConexion();

                // Preparar la consulta para actualizar los datos del beneficiario
                String consulta = "UPDATE Beneficiarios SET nombre_completo=?, fecha_nacimiento=?, direccion=?, codigo_postal=?, curp=?, clave_elector=?, ocr=?, vigencia_ine=?, correo_electronico=?, facebook=?, instagram=?, imagen_anverso_ine=?, imagen_reverso_ine=? WHERE id=?";
                PreparedStatement pstmt = conexion.prepareStatement(consulta);

                // Establecer los parámetros de la consulta
                pstmt.setString(1, txt_nombre.getText());
                pstmt.setDate(2, new java.sql.Date(txt_nacimiento.getDate().getTime()));
                pstmt.setString(3, txt_direccion.getText());
                pstmt.setString(4, txt_cp.getText());
                pstmt.setString(5, txt_curp.getText());
                pstmt.setString(6, txt_clave_elector.getText());
                pstmt.setString(7, txt_OCR.getText());
                pstmt.setDate(8, new java.sql.Date(txt_vigenciaINE.getDate().getTime()));
                pstmt.setString(9, txt_correo.getText());
                pstmt.setString(10, txt_facebook.getText());
                pstmt.setString(11, txt_instagram.getText());

                // Leer las imágenes como arreglos de bytes
                byte[] imagenAnverso = leerArchivo(archivoAnverso);
                byte[] imagenInverso = leerArchivo(archivoInverso);

                // Establecer los datos de las imágenes como parámetros de la consulta
                pstmt.setBytes(12, imagenAnverso);
                pstmt.setBytes(13, imagenInverso);

                // Establecer el ID del beneficiario para la actualización
                pstmt.setString(14, this.id);

                // Ejecutar la consulta para actualizar los datos en la base de datos
                pstmt.executeUpdate();

                // Cerrar la conexión y liberar recursos
                pstmt.close();
                conexion.close();

                JOptionPane.showMessageDialog(null, "Los datos del beneficiario se han actualizado correctamente.");
                clear(); // Limpiar los campos si es necesario
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al actualizar los datos del beneficiario.");
            } catch (IOException ex) {
                Logger.getLogger(Actualizar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btn_guardarActionPerformed

    private void txt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyTyped
        char c = evt.getKeyChar();
        // Validar que el carácter sea una letra o un espacio y que la longitud no exceda los 200 caracteres
        if ((!Character.isLetter(c) && c != ' ' && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE)
                || txt_nombre.getText().length() >= 200) {
            // Reproducir alerta (puedes cambiar esto por tu propia lógica de alerta)
            Toolkit.getDefaultToolkit().beep();
            evt.consume(); // Consumir el evento para evitar que se ingrese el carácter
        }
    }//GEN-LAST:event_txt_nombreKeyTyped

    private void txt_direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_direccionKeyTyped
        char c = evt.getKeyChar();
        // Validar que el carácter sea una letra y que la longitud no exceda los 200 caracteres
        if (txt_direccion.getText().length() >= 250) {
            // Reproducir alerta (puedes cambiar esto por tu propia lógica de alerta)
            Toolkit.getDefaultToolkit().beep();
            evt.consume(); // Consumir el evento para evitar que se ingrese el carácter
        }
    }//GEN-LAST:event_txt_direccionKeyTyped

    private void txt_cpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cpKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isDigit(c) || txt_cp.getText().length() >= 6) {
            // Reproducir alerta (puedes cambiar esto por tu propia lógica de alerta)
            Toolkit.getDefaultToolkit().beep();
            evt.consume(); // Consumir el evento para evitar que se ingrese el carácter
        }
    }//GEN-LAST:event_txt_cpKeyTyped

    private void txt_curpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_curpKeyTyped
        char c = evt.getKeyChar();
        // Validar que el carácter sea una letra (mayúscula o minúscula) o un número
        if (!Character.isLetterOrDigit(c) || txt_curp.getText().length() >= 18) {
            // Reproducir alerta (puedes cambiar esto por tu propia lógica de alerta)
            Toolkit.getDefaultToolkit().beep();
            evt.consume(); // Consumir el evento para evitar que se ingrese el carácter
        }
    }//GEN-LAST:event_txt_curpKeyTyped

    private void txt_OCRKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_OCRKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isDigit(c) || txt_OCR.getText().length() >= 13) {
            // Reproducir alerta (puedes cambiar esto por tu propia lógica de alerta)
            Toolkit.getDefaultToolkit().beep();
            evt.consume(); // Consumir el evento para evitar que se ingrese el carácter
        }
    }//GEN-LAST:event_txt_OCRKeyTyped

    private void txt_clave_electorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_clave_electorKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isLetterOrDigit(c) || txt_clave_elector.getText().length() >= 18) {
            // Reproducir alerta (puedes cambiar esto por tu propia lógica de alerta)
            Toolkit.getDefaultToolkit().beep();
            evt.consume(); // Consumir el evento para evitar que se ingrese el carácter
        }
    }//GEN-LAST:event_txt_clave_electorKeyTyped

    private void txt_correoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_correoKeyTyped
        char c = evt.getKeyChar();
        String correo = txt_correo.getText();
        // Validar que el carácter sea válido para una dirección de correo electrónico
        if ((!Character.isLetterOrDigit(c) && c != '@' && c != '.' && c != '_' && c != ' ')
                || correo.length() >= 50) {
            // Reproducir alerta (puedes cambiar esto por tu propia lógica de alerta)
            Toolkit.getDefaultToolkit().beep();
            evt.consume(); // Consumir el evento para evitar que se ingrese el carácter
        }
    }//GEN-LAST:event_txt_correoKeyTyped

    private void txt_correoConfirmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_correoConfirmKeyTyped
        char c = evt.getKeyChar();
        String correoConfirm = txt_correoConfirm.getText();
        // Validar que el carácter sea válido para una dirección de correo electrónico
        if ((!Character.isLetterOrDigit(c) && c != '@' && c != '.' && c != '_' && c != ' ')
                || correoConfirm.length() >= 50) {
            // Reproducir alerta (puedes cambiar esto por tu propia lógica de alerta)
            Toolkit.getDefaultToolkit().beep();
            evt.consume(); // Consumir el evento para evitar que se ingrese el carácter
        }
    }//GEN-LAST:event_txt_correoConfirmKeyTyped

    private void txt_facebookKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_facebookKeyTyped
        char c = evt.getKeyChar();
        String facebook = txt_facebook.getText();
        // Validar que el carácter sea válido para un nombre de usuario de Facebook
        if ((!Character.isLetterOrDigit(c) && c != '.' && c != '_' && c != '-' && c != ' ')
                || facebook.length() >= 50) {
            // Reproducir alerta (puedes cambiar esto por tu propia lógica de alerta)
            Toolkit.getDefaultToolkit().beep();
            evt.consume(); // Consumir el evento para evitar que se ingrese el carácter
        }
    }//GEN-LAST:event_txt_facebookKeyTyped

    private void txt_instagramKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_instagramKeyTyped
        char c = evt.getKeyChar();
        String instagram = txt_instagram.getText();
        // Validar que el carácter sea válido para un nombre de usuario de Instagram
        if ((!Character.isLetterOrDigit(c) && c != '.' && c != '_' && c != '-')
                || instagram.length() >= 30) {
            // Reproducir alerta (puedes cambiar esto por tu propia lógica de alerta)
            Toolkit.getDefaultToolkit().beep();
            evt.consume(); // Consumir el evento para evitar que se ingrese el carácter
        }
    }//GEN-LAST:event_txt_instagramKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Menu m = new Menu();
        m.setVisible(true);
        m.setUsuario(this.usuario);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            setId(txt_id.getText().trim());
            mostrarDatos();
        } catch (IOException ex) {
            Logger.getLogger(Actualizar.class.getName()).log(Level.SEVERE, null, ex);
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

    private byte[] leerArchivo(File archivo) throws IOException {
        byte[] buffer = new byte[(int) archivo.length()];
        FileInputStream fis = new FileInputStream(archivo);
        fis.read(buffer);
        fis.close();
        return buffer;
    }

    private boolean validarClaveElector() {
        String claveElector = txt_clave_elector.getText().trim();
        if (claveElector.length() != 18) {
            JOptionPane.showMessageDialog(null, "Por favor, Ingrese una Clave de Elector correcta.");
            return false;
        }

        // Si se cumplen todas las condiciones anteriores, la Clave de Elector es válida
        return true;
    }

    private boolean validarOCR() {
        // Especifica la longitud esperada del OCR
        String ocr = txt_OCR.getText().trim();

        int longitudEsperada = 13;

        // Verifica si el OCR tiene la longitud correcta
        if (ocr.length() != longitudEsperada) {
            JOptionPane.showMessageDialog(null, "El OCR no es válido.");
            return false;

        }

        // Verifica si el OCR contiene solo caracteres alfanuméricos
        if (!ocr.matches("[a-zA-Z0-9]+")) {
            return false;
        }

        // Si se cumplen todas las condiciones anteriores, el OCR es válido
        return true;
    }

    private boolean validarFechaNacimiento() {
        Date fechaNacimiento = txt_nacimiento.getDate();
        if (fechaNacimiento == null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fecha de nacimiento válida.");
            return false;
        }
        // Validar que la fecha de nacimiento no sea en el futuro
        if (fechaNacimiento.after(new Date())) {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no puede ser en el futuro.");
            return false;
        }
        return true;
    }

    private boolean validarVigenciaINE() {
        Date vigenciaINE = txt_vigenciaINE.getDate();
        if (vigenciaINE == null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fecha de vigencia de INE válida.");
            return false;
        }
        // Validar que la vigencia de la INE no sea en el pasado
        if (vigenciaINE.before(new Date())) {
            JOptionPane.showMessageDialog(null, "La vigencia de la INE no puede ser en el pasado.");
            return false;
        }
        return true;
    }

    private boolean validarMayorEdad() {
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.setTime(txt_nacimiento.getDate());
        Calendar hoy = Calendar.getInstance();
        int edad = hoy.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        if (hoy.get(Calendar.MONTH) < fechaNacimiento.get(Calendar.MONTH)
                || (hoy.get(Calendar.MONTH) == fechaNacimiento.get(Calendar.MONTH)
                && hoy.get(Calendar.DAY_OF_MONTH) < fechaNacimiento.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }
        if (edad < 18) {
            JOptionPane.showMessageDialog(null, "El beneficiario debe ser mayor de edad.");
            return false;
        }
        return true;
    }

    private boolean validarCurp() {
        String curp = txt_curp.getText().trim();

        String regex = "^([A-Z][AEIOUX][A-Z]{2}\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])[HM](?:AS|B[CS]|C[CLMSH]|D[FG]|G[TR]|HG|JC|M[CNS]|N[ETL]|OC|PL|Q[TR]|S[PLR]|T[CSL]|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\\d])(\\d)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(curp);

        if (!matcher.matches()) { // ¿Coincide con el formato general?
            JOptionPane.showMessageDialog(null, "La CURP no es válida.");
            return false;
        }
        return true; // Validado
    }

    private boolean validarCorreo() {
        String correo = txt_correo.getText().trim();
        String correoConfirm = txt_correoConfirm.getText().trim();
        if (!correo.equals(correoConfirm)) {
            JOptionPane.showMessageDialog(null, "Los correos electrónicos no coinciden.");
            return false;
        }
        // Validar la estructura del correo electrónico
        Pattern patronCorreo = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        Matcher matcherCorreo = patronCorreo.matcher(correo);
        if (!matcherCorreo.matches()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un correo electrónico válido.");
            return false;
        }
        return true;
    }

    private boolean validarImagenesSeleccionadas() {
        if (archivoAnverso == null || archivoInverso == null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione ambas imágenes del anverso y reverso de la INE.");
            return false;
        }
        return true;
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
        archivoAnverso = null;
        archivoInverso = null;
        lb_anverso.setIcon(null);
        lb_inverso.setIcon(null);

        txt_correo.setText("");
        txt_correoConfirm.setText("");
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
            java.util.logging.Logger.getLogger(Actualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Actualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Actualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Actualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Actualizar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ayuda_correo;
    private javax.swing.JLabel ayuda_cp;
    private javax.swing.JLabel ayuda_curp;
    private javax.swing.JLabel ayuda_direccion;
    private javax.swing.JLabel ayuda_elector;
    private javax.swing.JLabel ayuda_f_nac;
    private javax.swing.JLabel ayuda_facebook;
    private javax.swing.JLabel ayuda_fotos;
    private javax.swing.JLabel ayuda_instagram;
    private javax.swing.JLabel ayuda_nombre;
    private javax.swing.JLabel ayuda_ocr;
    private javax.swing.JLabel ayuda_vigencia;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_seleccionar_anverso;
    private javax.swing.JButton btn_seleccionar_inverso;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JTextField txt_correoConfirm;
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
