/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khademni.guiQuiz ;



import javafx.scene.control.ListCell;
import khademni.entity.Question;
import khademni.entity.Quiz;

/**
 *
 * @author dell
 */
public class ListViewQuestion extends ListCell<Question> {
    
    
     @Override
     public void updateItem(Question e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            QuestionItemController data = new QuestionItemController();
            data.setInfo(e);
            setGraphic(data.getHbox());
            setGraphic(data.getBox());
        }
    }
    
}
