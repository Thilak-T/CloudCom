/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.bb;

import com.ejca.ejb.PersonFacade;
import com.ejca.entity.Person;
import com.ejca.util.AppConfig;
import com.ejca.util.FileUploadUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Rucha Kulkarni
 */
@RequestScoped
@Named("profile")
@RolesAllowed("USERS")
public class ProfileBB implements Serializable {

    private Person person;
    private UploadedFile file;
    private FileUploadUtil fileUploadUtil;

    @EJB
    PersonFacade personFacade;
    @Inject
    UserBean userBean;
    @Inject
    AppConfig appConfig;

    @PostConstruct
    public void initPerson() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().
                getExternalContext().getRequest();
        String id = req.getParameter("id");
        if (id == null) {
            person = userBean.getUser();
        } else {
            person = personFacade.find(id);
        }
        fileUploadUtil = new FileUploadUtil();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getProfilePic() {
        return person.getPicture();
    }

    public void setProfilePic(String pic) {
        person.setPicture(pic);
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            String orgName = event.getFile().getFileName();
            String fileName = UUID.randomUUID().toString() + orgName.substring(orgName.lastIndexOf('.'));

            fileUploadUtil.copyFile(new AppConfig().getMEDIALoc() + fileName, event.getFile().getInputstream());

            person.setPicture(fileName);
            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {
            Logger.getLogger(ProfileBB.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public String save() {
        personFacade.edit(person);
        return "/page/userprofile.xhtml?faces-redirect=true";
    }

}
