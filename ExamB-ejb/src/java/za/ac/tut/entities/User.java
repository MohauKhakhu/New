/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author trant
 */
@Entity
@Table(name = "tblUser")
public class User extends Person {
    private int learnerNo;
    private String password;
    private String username;
    private String userType;

    public int getLearnerNo() {
        return learnerNo;
    }

    public void setLearnerNo(int learnerNo) {
        this.learnerNo = learnerNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "User{" + "learnerNo=" + learnerNo + ", password=" + password + ", username=" + username + ", userType=" + userType + '}';
    }
    
    
}
