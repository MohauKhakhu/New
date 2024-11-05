/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author trant
 */
@Entity
public class PossibleAnswers {
     @Id
    @GeneratedValue
    private int possibleAnswerId;
    private String possibleAnswer;

    public int getPossibleAnswerId() {
        return possibleAnswerId;
    }

    public void setPossibleAnswerId(int possibleAnswerId) {
        this.possibleAnswerId = possibleAnswerId;
    }

    public String getPossibleAnswer() {
        return possibleAnswer;
    }

    public void setPossibleAnswer(String possibleAnswer) {
        this.possibleAnswer = possibleAnswer;
    }
    
}
