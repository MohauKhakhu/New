/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/MessageDrivenBean.java to edit this template
 */
package za.ac.tut.sessions;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import za.ac.tut.entities.User;

/**
 *
 * @author trant
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "firstQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class LogonMDB implements MessageListener {
    @EJB
    UserBeanLocal userBean;
    public LogonMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            try {
                TextMessage txtMsg = (TextMessage) message;
                String [] logons = txtMsg.getText().split("#");
                User user = userBean.validateLogon(logons[0], logons[1]);
                if(user != null){
                    ObjectMessage objMsg = (ObjectMessage) message;
                    user = (User) objMsg.getObject();
                    System.out.println("User details are "+ user.toString());
                }else{
                    System.out.println("The user is not found");
                }
            } catch (JMSException ex) {
                Logger.getLogger(LogonMDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
