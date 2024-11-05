/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package za.ac.tut.sessions;

import java.util.List;
import javax.ejb.Local;
import za.ac.tut.entities.Question;

/**
 *
 * @author trant
 */
@Local
public interface QuestionBeanLocal {
    public List<Question> retrieveQuestion();
    public void insertQuestion(Question question);
    public Question findQuestion(int id);
    public void deleteQuestion(int id);
}
