/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package za.ac.tut.sessions;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import za.ac.tut.entities.Question;

/**
 *
 * @author trant
 */
@Stateless
public class QuestionBean implements QuestionBeanLocal {
@PersistenceContext(unitName = "ExamB-ejbPU")
EntityManager entity;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Question> retrieveQuestion() {
        String sql = "Select questions FROM Question questions";
        Query query = entity.createQuery(sql);
        List<Question> questions = (List<Question>) query.getResultList();
        for(Question question : questions){
            question.getPossibleAnswers().size();
        }
        return questions;
    }

    @Override
    public void insertQuestion(Question question) {
        entity.persist(question);
    }

    @Override
    public Question findQuestion(int id) {
        Question question = entity.find(Question.class, id);
        return question;
    }

    @Override
    public void deleteQuestion(int id) {
        Question question = findQuestion(id);
        if(question != null){
            entity.remove(question);
        }
    }
}
