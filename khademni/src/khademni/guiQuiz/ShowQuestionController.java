/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khademni.guiQuiz;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.printing.PDFPageable;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import khademni.entity.Note;
import khademni.entity.Question;
import khademni.entity.Quiz;
import static khademni.guiQuiz.ShowQuizController.idQf;
import khademni.services.NoteService;
import khademni.services.QuestionService;
import khademni.services.QuizService;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class ShowQuestionController implements Initializable {

    @FXML
    private ListView<Question> listView;
    
    ObservableList<Question> data;
     
     public static int idQuestionf ;
     
     public static float nb =0f ;
     
     QuestionService qs = new QuestionService();

     NoteService ns = new NoteService();
    @FXML
    private Button btnImp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                        try {
            data = (ObservableList<Question>) qs.getAllQuestion(ShowQuizController.idQf);   
            listView.setItems(data);
            listView.setCellFactory((ListView<Question> param) -> new ListViewQuestion());
            
            
            // TODO
        } catch (SQLDataException ex) {
                    Logger.getLogger(ShowQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Passer(ActionEvent event) {
        
              ObservableList<Question> e = listView.getSelectionModel().getSelectedItems();     
               for (Question m : e) {
               idQuestionf = m.getId_question();  
                }
               
                             
            Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/khademni/guiQuiz/ShowReponse.fxml"));
               Stage myWindow = (Stage) listView.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherQuizController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
    }

    @FXML
    private void finquiz(ActionEvent event) throws SQLException {
        
          nb = ShowReponseController.count/qs.NumQuestionParQuiz(idQf);
        
               Note n = new Note();
               n.setId_question(idQuestionf);
               n.setId_user(1);
               n.setNote((int) nb);
        
        
                 Parent root;
               try {
               root = FXMLLoader.load(getClass().getResource("/khademni/guiQuiz/NoteUser.fxml"));
               Stage myWindow = (Stage) listView.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherQuizController.class.getName()).log(Level.SEVERE, null, ex);
               }
    }

    @FXML
    private void print(ActionEvent event) throws IOException {
        // Create a new PDF document
    PDDocument document = new PDDocument();
    PDPage page = new PDPage();
    document.addPage(page);
    PDPageContentStream contentStream = new PDPageContentStream(document, page);

    // Add title to the document
    contentStream.beginText();
    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 24);
    contentStream.newLineAtOffset(100, 700);
    contentStream.showText("Question List");
    contentStream.endText();

    // Add data to the document
    contentStream.beginText();
    contentStream.setFont(PDType1Font.HELVETICA, 14);
    contentStream.newLineAtOffset(100, 650);

    for (Question question : data) {
        contentStream.showText("Question: " + question.getQuestion());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Answer: " + question.getType_question());
        contentStream.newLineAtOffset(0, -20);
    }

    contentStream.endText();

    // Close the content stream and save the document
    contentStream.close();

    // Save the PDF document to a temporary file
    File pdfFile = File.createTempFile("question-list", ".pdf");
    document.save(pdfFile);
    document.close();

    // Open the PDF file
    if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().open(pdfFile);
    } else {
        System.out.println("Desktop not supported, unable to open PDF file.");
    }
    
}
    
}
