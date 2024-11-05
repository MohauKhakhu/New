/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatefulEjbClass.java to edit this template
 */
package za.ac.tut.sessions;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import za.ac.tut.entities.Question;

/**
 *
 * @author trant
 */
@Stateful
public class MarkingBean implements MarkingBeanService {
    private List<Question> questions;
    private int totalControlSign;
    private int totalCommandSign;
    private int totalProhibitionSign;
    private int totalReservationSign;
    

    @Override
    public void initialize() {
        questions = new ArrayList<Question>();
        totalControlSign = 0;
        totalCommandSign = 0;
        totalProhibitionSign = 0;
        totalReservationSign = 0;
    }

    @Override
    public void markQuestion(int questionId, String possibleAnswer) {
        int count = 0;
        for(Question question : questions){
            if(question.getQuestionId() == questionId){
                if(question.getAnswer().getCorrectAnswer().equals(possibleAnswer)){
                    switch (question.getSignGroup()) {
                        case "Control":
                            totalControlSign++;
                            break;
                        case "Command":
                            totalCommandSign++;
                            break;
                        case "Prohibition":
                            totalProhibitionSign++;
                            break;
                        case "Reservation":
                            totalReservationSign++;
                            break;
                        default:
                            throw new AssertionError();
                    }
                    question.setMark(1);
                }else{
                    question.setMark(0);
                }
                question.setIsAnswered(true);
                questions.set(count, question);
            }
            count++;
        }
    }

    @Override
    public String determineResult() {
        double totalControl = 0, totalCommand = 0, totalProhibition = 0, totalReservation = 0;
        for(Question question : questions){
            switch (question.getSignGroup()) {
                        case "Control":
                            ++totalControl;
                            break;
                        case "Command":
                            ++totalCommand;
                            break;
                        case "Prohibition":
                            ++totalProhibition;
                            break;
                        case "Reservation":
                            ++totalReservation;
                            break;
                        default:
                            throw new AssertionError();
                    }
            
        }
        if(totalControlSign/totalControl * 100 >= 90 && totalCommandSign/totalCommand * 100 >= 80 && totalProhibitionSign/totalProhibition * 100 >= 70 && totalReservationSign/totalReservation *100 >= 70){
            return "passed";
        }else{
            return "failed";
        }
    }

    @Override
    public void populateQuestion(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public List<Question> getAllQuestion() {
        return questions;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
