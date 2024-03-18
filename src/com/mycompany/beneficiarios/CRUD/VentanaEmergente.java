/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.beneficiarios.CRUD;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VentanaEmergente extends JFrame {

    public VentanaEmergente(Image imagen) {
        // Configurar la ventana emergente
        setTitle("Imagen Ampliada");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear un JLabel para mostrar la imagen
        JLabel labelImagen = new JLabel(new ImageIcon(imagen));

        // Crear un JScrollPane y agregar el JLabel a él
        JScrollPane scrollPane = new JScrollPane(labelImagen);

        // Añadir el JScrollPane a la ventana
        add(scrollPane);

        // Ajustar el tamaño de la ventana automáticamente
        pack();

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);

        // Agregar un ComponentListener para detectar cambios en el tamaño de la ventana
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Obtener el tamaño de la ventana
                Dimension size = scrollPane.getSize();

                // Obtener la imagen original
                ImageIcon icon = (ImageIcon) labelImagen.getIcon();
                Image imagenOriginal = icon.getImage();

                // Redimensionar la imagen para que se ajuste al tamaño del JScrollPane
                Image imagenEscalada = imagenOriginal.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);

                // Actualizar el icono del JLabel con la imagen redimensionada
                labelImagen.setIcon(new ImageIcon(imagenEscalada));
            }
        });
    }
}
