/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package za.ac.tut.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import za.ac.tut.entities.User;

/**
 *
 * @author trant
 */
@Stateless
public class UserBean implements UserBeanLocal {
@PersistenceContext(unitName = "ExamB-ejbPU")
EntityManager entity;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void storeUser(User user) {
        entity.persist(user);
    }

    @Override
    public User validateLogon(String usn, String password) {
        String sql = "Select user FROM User user WHERE user.username LIKE '"+usn+"' AND user.password LIKE '"+password+"'";
        Query query = entity.createQuery(sql);
        User user = (User) query.getSingleResult();
        if(user!= null){
            return user;
        }
    return null;
    }
}
