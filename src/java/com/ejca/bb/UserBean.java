/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.bb;

import com.ejca.entity.Discussion;
import com.ejca.entity.Person;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Ramesh Kumar
 */
@Named
@SessionScoped
public class UserBean implements Serializable {

    private Person user;
    @EJB private com.ejca.ejb.PersonFacade ejbPerson;
   
    public Person getUser() {
        return user;
    }

    @PostConstruct
    public void init(){
        String userId = "thilak";
        user=ejbPerson.find(userId);
    }
    
        
    public String logout(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(false);
        httpSession.invalidate();
        return "/faces/login.xhtml?faces-redirect=true";
    }

}
