/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khademni.guiQuiz ;



import javafx.scene.control.ListCell;
import khademni.entity.Quiz;

/**
 *
 * @author dell
 */
public class ListViewQuiz extends ListCell<Quiz> {
    
    
     @Override
     public void updateItem(Quiz e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            QuizItemController data = new QuizItemController();
            data.setInfo(e);
            setGraphic(data.getHbox());
            setGraphic(data.getBox());
        }
    }
    
}
