/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.entity;


import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Table;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import javafx.collections.ObservableList;
import javafx.scene.control.Cell;
/**
 *
 * @author user
 */
public class PdfEv {
    
     
         public void generatePdf(String filename, ObservableList<Evenement> evenements) throws  FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        
        document.add(new Paragraph("            Date  :"+LocalDateTime.now()));
         document.add(new Paragraph("                      "));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));

        // Table with 6 columns
        PdfPTable table = new PdfPTable(6);

        // Add table headers
        Stream.of("Titre", "Description", "Nom société", "Lieu", "Date début", "Date fin")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });

        // Add table data
        evenements.forEach(evenement -> {
            table.addCell(evenement.getTitre());
            table.addCell(evenement.getDescription());
            table.addCell(evenement.getNom_societe());
            table.addCell(evenement.getLieu());
            table.addCell(evenement.getDate_debut().toString());
            table.addCell(evenement.getDate_fin().toString());
        });

        // Add table to document
        document.add(table);
       document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.add(new Paragraph("                              KHADEMNI                    "));
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }
    
}
