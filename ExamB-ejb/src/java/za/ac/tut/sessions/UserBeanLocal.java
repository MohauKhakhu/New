/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package za.ac.tut.sessions;

import javax.ejb.Local;
import za.ac.tut.entities.User;

/**
 *
 * @author trant
 */
@Local
public interface UserBeanLocal {
    public void storeUser(User user);
    public User validateLogon(String usn, String password);
}
