/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package za.ac.tut.sessions;

import java.util.List;
import javax.ejb.Remote;
import za.ac.tut.entities.Question;


/**
 *
 * @author trant
 */
@Remote
public interface MarkingBeanService {
    public void initialize();
    public void markQuestion(int questionId, String possibleAnswer);
    public String determineResult();
    public void populateQuestion(List<Question> questions);
    public List<Question> getAllQuestion();
}
