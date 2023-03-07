/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.entity;

/**
 *
 * @author moham
 */
public class Question {
private int id_question ;
private String question , type_question ;
private int id_quiz ;

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType_question() {
        return type_question;
    }

    public void setType_question(String type_question) {
        this.type_question = type_question;
    }

    public int getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(int id_quiz) {
        this.id_quiz = id_quiz;
    }
    
    

    @Override
    public String toString() {
        return "Question{" + "id_question=" + id_question + ", question=" + question + ", type_question=" + type_question + '}';
    }

    public Question() {
    }
            

}
