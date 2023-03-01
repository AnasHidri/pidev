/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.entity;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import javafx.collections.ObservableList;
import javafx.scene.control.Cell;
import javafx.scene.control.TableView;
/**
 *
 * @author user
 */
public class PdfEv {
    
     
         public void generatePdf(String filename, Evenement evenement) throws  FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException, WriterException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        
        document.add(new Paragraph("            Date  :"+LocalDateTime.now()));
        //document.add(new Paragraph("            Liste des Evenements   :"));
         document.add(new Paragraph("                      "));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));

       document.add(new Paragraph("Titre : " + evenement.getTitre()));
        document.add(new Paragraph("Description : " + evenement.getDescription()));
        document.add(new Paragraph("Nom société : " + evenement.getNom_societe()));
        document.add(new Paragraph("Lieu : " + evenement.getLieu()));
        document.add(new Paragraph("Date début : " + evenement.getDate_debut()));
        document.add(new Paragraph("Date fin : " + evenement.getDate_fin()));
        
        

       ByteArrayOutputStream qrCodeStream = new ByteArrayOutputStream();
        generateQRCode("https://example.com/participation/" + evenement.getId_evenement(), qrCodeStream); // Replace "example.com" with your domain name
        Image qrCodeImage = Image.getInstance(qrCodeStream.toByteArray());
        document.add(qrCodeImage);
        
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.add(new Paragraph("                              KHADEMNI                    "));
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }
         
          public void generatePdfFromTableView(String filename, TableView<Evenement> tableView) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException, WriterException  {
        Evenement evenement = tableView.getSelectionModel().getSelectedItem();
        if (evenement != null) {
            generatePdf(filename, evenement);
        }
    }
          
          public void generateQRCode(String qrCodeData, OutputStream outputStream) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 200, 200);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
    }
         
         
       public void generatePdfWithQRCode(String filename, Evenement evenement, Image qrCodeImage) throws FileNotFoundException, DocumentException, IOException {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream(filename));
    document.open();

    document.add(new Paragraph("            Date  :" + LocalDateTime.now()));
    document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));

    document.add(new Paragraph("Titre : " + evenement.getTitre()));
    document.add(new Paragraph("Description : " + evenement.getDescription()));
    document.add(new Paragraph("Nom société : " + evenement.getNom_societe()));
    document.add(new Paragraph("Lieu : " + evenement.getLieu()));
    document.add(new Paragraph("Date début : " + evenement.getDate_debut()));
    document.add(new Paragraph("Date fin : " + evenement.getDate_fin()));

    document.add(qrCodeImage);

    document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
    document.add(new Paragraph("                              KHADEMNI                    "));
    document.close();
    Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
}   
          
    
}
