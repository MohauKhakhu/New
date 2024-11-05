/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/MessageDrivenBean.java to edit this template
 */
package za.ac.tut.sessions;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
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
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/DatDestinationTopic"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/DatDestinationTopic"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/DatDestinationTopic"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class ResultsSubscriberMDB implements MessageListener {
    
    public ResultsSubscriberMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
        if(message instanceof ObjectMessage){
            try {
                ObjectMessage txtMsg = (ObjectMessage) message;
                List<User> textMessage;
                textMessage = (List<User>) txtMsg.getObject();
                System.out.println(textMessage);
                
            } catch (JMSException ex) {
                Logger.getLogger(ResultsSubscriberMDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
