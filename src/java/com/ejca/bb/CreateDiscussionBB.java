/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.bb;

import com.ejca.entity.Discussion;
import com.ejca.entity.Person;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DualListModel;

/**
 *
 * @author mido
 */
@RequestScoped
@Named("createDiscussion")
@DeclareRoles({"USERS"})
@RolesAllowed("USERS")
public class CreateDiscussionBB implements Serializable {

    private Discussion discussion;
    //private Person creator;
    private DualListModel<Person> participants;
    @Inject private UserBean userBean;
    @EJB
    private com.ejca.ejb.DiscussionFacade ejbDiscussion;
    @EJB
    private com.ejca.ejb.PersonFacade ejbPerson;

    @PostConstruct
    public void init() {
        try {
            List<Person> source = new ArrayList<Person>();
            List<Person> target = new ArrayList<Person>();
            source = ejbPerson.findAll();
            source.remove(userBean.getUser());
            participants = new DualListModel<Person>(source, target);
        } catch (Exception e) {
            e.printStackTrace();
        }

        discussion = new Discussion();
    }

    public String createNewDiscussion() {
        try {
            discussion.setCreatorId(userBean.getUser());
            discussion.setCreatedTime(new Date());
            discussion.setLastUpdatedTime(new Date());
            discussion.setReplyCount(0);
            List<Person> pl = new ArrayList<Person>();
            for(Object s:participants.getTarget() ){
            pl.add(ejbPerson.find(s));
            }
            pl.add(userBean.getUser());
               discussion.setParticipants(pl);
               ejbDiscussion.create(discussion);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/page/homepage?face-redirect=true";
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }

    public DualListModel<Person> getParticipants() {
        return participants;
    }

    public void setParticipants(DualListModel<Person> participants) {
        this.participants = participants;
    }

}
