package com.mycompany.beneficiarios.reporte;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class generarPDF {

    public static void main(String[] args) {
        generarPDF p = new generarPDF();
        p.crearReporte(1, "John Doe", "2024.03.12", "123 Main St", "12345", "ABCDE", "1234567890", "OCR123", "2024-03-19", "john@example.com", "", "");
    }
    
    public void crearReporte(int id, String nombre, String fechanacimiento, String direccion, String cp, String curp, String claveElector,
                              String ocr, String vigenciaINE, String correo, String facebook, String instagram) {
        // Creación de un objeto de documento
        Document document = new Document();
        String filePath="";
        try {
            // Creación del escritor
            String userDirectory = System.getProperty("user.home") + "/Desktop/";
            // Crear un archivo en el escritorio del usuario
            filePath = userDirectory + File.separator + nombre + ".pdf";
            // Creación del escritor con el archivo en el escritorio del usuario
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Abrir el documento
            document.open();

            // Obtenemos el ContentByte y hacemos algo con él
            PdfContentByte cb = writer.getDirectContent();

            //--------------------- página 1 --------------------------
            Font fontTitulo = new Font(Font.HELVETICA, 28, Font.BOLD, new Color(0, 0, 0));
            Font fontInfo = new Font(Font.HELVETICA, 14, Font.NORMAL, new Color(0, 0, 0));
            Font fontHeader = new Font(Font.HELVETICA, 18, Font.BOLD, new Color(0, 0, 255));
            
            Paragraph espacio = new Paragraph("\n");
            document.add(espacio);
            document.add(espacio);
            document.add(espacio);

            // Añadir imagen de encabezado
            Image imagenEncabezado = Image.getInstance(getClass().getResource("/com/mycompany/beneficiarios/imagenes/logo.png"));
            imagenEncabezado.scaleToFit(200, 200);
            imagenEncabezado.setAlignment(Image.ALIGN_CENTER);

            Paragraph titulo = new Paragraph("Nuevo Beneficiario", fontTitulo);
            Paragraph header = new Paragraph("Información del Beneficiario", fontHeader);
            String infoText =
                    "ID: " + id + "\n" +
                            "Nombre: " + nombre + "\n" +
                            "Fecha nacimiento: " + fechanacimiento + "\n" +
                            "Dirección: " + direccion + "\n" +
                            "Código Postal: " + cp + "\n" +
                            "CURP: " + curp + "\n" +
                            "Clave de Elector: " + claveElector + "\n" +
                            "OCR: " + ocr + "\n" +
                            "Vigencia INE: " + vigenciaINE + "\n" +
                            "Correo: " + correo + "\n" +
                            "Facebook: " + (facebook.isEmpty() ? "No hay dato" : facebook) + "\n" +
                            "Instagram: " + (instagram.isEmpty() ? "No hay dato" : instagram);

            Paragraph info = new Paragraph(infoText, fontInfo);

            titulo.setAlignment(Element.ALIGN_CENTER);
            header.setAlignment(Element.ALIGN_CENTER);
            info.setAlignment(Element.ALIGN_LEFT);

            // Añadir espacio antes del título

            document.add(imagenEncabezado); // Agregar imagen de encabezado
            document.add(titulo);
            document.add(header);

            // Añadir una línea decorativa
            cb.moveTo(36, 750);
            cb.lineTo(559, 750);
            cb.stroke();

            document.add(info);

        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        } finally {
            // Cerramos el documento
            document.close();

            JOptionPane.showMessageDialog(null,
                    "Se creó el archivo " + filePath );
        }
    }
}
