/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.bb;

import com.ejca.ejb.GroupFacade;
import com.ejca.ejb.PersonFacade;
import com.ejca.entity.Group;
import com.ejca.entity.Person;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ramesh Kumar
 */
@RequestScoped
@Named("register")
@RolesAllowed("USERS")
public class RegisterBB {
    
    @EJB PersonFacade ejbPerson;
    @EJB GroupFacade ejbGroup;
    
    private Person person = new Person();

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    public void registerPerson(){
        Group group = ejbGroup.find(1);
        person.getGroups().add(group);
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            ejbPerson.registerPerson(person);
            FacesMessage success = new FacesMessage("Registration Successful");
            context.addMessage("register:registerBtn", success);
        } catch (Exception ex) {
            FacesMessage error = new FacesMessage("User Already Exists");
            context.addMessage("register:registerBtn", error);
        }
    }

}
    