/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.bb;


import com.ejca.ejb.DiscussionFacade;
import com.ejca.ejb.PersonFacade;
import com.ejca.entity.Discussion;
import com.ejca.entity.Person;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.ColumnResizeEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author mido
 */
@RequestScoped
@Named("homepage")
@RolesAllowed("USERS")
public class HomepageBB implements Serializable {

    @EJB
    private DiscussionFacade ejbDiscussion;
    @EJB
    private PersonFacade ejbPerson;
    private List<Discussion> discussions;
    @Inject private UserBean userBean;
    private Discussion selectedDiscussion;
    private LazyDiscussionDataModel dataModel;
    
    public HomepageBB() {
        
    }
    
    public LazyDiscussionDataModel getLazyModel() {
        return dataModel;
    }

    public void setLazyModel(LazyDiscussionDataModel dataModel) {
        this.dataModel = dataModel;
    }

    @PostConstruct
    public void init() {
        dataModel = new LazyDiscussionDataModel(ejbDiscussion,userBean.getUser());
    }
    
    private String showDiscussion(){
        return "/page/createDiscussion?faces-redirect=true";
    }

    public String gotoCreateDiscussion() {
        return "/page/createDiscussion?faces-redirect=true";
    }

    public void onRowSelect(SelectEvent event) { // Not used for now
        FacesMessage msg = new FacesMessage("Discussion Selected", ((Discussion) event.getObject()).getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void onResize(ColumnResizeEvent event) { //TRY to PERSIST foR USERS SETTINGS
        FacesMessage msg = new FacesMessage("Column " + event.getColumn().getClientId() + " resized", "W:" + event.getWidth() + ", H:" + event.getHeight());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public List<Discussion> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(List<Discussion> discussions) {
        this.discussions = discussions;
    }

    public Discussion getSelectedDiscussion() {
        return selectedDiscussion;
    }

    public void setSelectedDiscussion(Discussion selectedDiscussion) {
        this.selectedDiscussion = selectedDiscussion;
    }

}
