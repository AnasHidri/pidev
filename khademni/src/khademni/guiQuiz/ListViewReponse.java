/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khademni.guiQuiz ;



import javafx.scene.control.ListCell;
import khademni.entity.Quiz;
import khademni.entity.Reponse;

/**
 *
 * @author dell
 */
public class ListViewReponse extends ListCell<Reponse> {
    
    
     @Override
     public void updateItem(Reponse e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            ReonseItem data = new ReonseItem();
            data.setInfo(e);
            setGraphic(data.getHbox());
            setGraphic(data.getBox());
        }
    }
    
}
