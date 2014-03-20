/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejca.ejb;

import com.ejca.entity.Discussion;
import com.ejca.entity.Person;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author mido
 */
@Stateless
public class DiscussionFacade extends AbstractFacade<Discussion> {
    @PersistenceContext(unitName = "CloudComPU")
    private EntityManager em;
    private static final String getDisQuery = "select d from Discussion d join d.participants p where p = :personId order by d.createdTime desc";
    private static final String getCountOfDiscussions = "select count(d) from Discussion d join d.participants p where p = :personId";
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiscussionFacade() {
        super(Discussion.class);
    }
    
    public List<Discussion> findRelated(Person person){
        List<Discussion> discussions = new ArrayList<Discussion>();
        return discussions;
    }
    
    public List<Discussion> getDiscussions(Person person, int index, int size){
        TypedQuery<Discussion> query = em.createQuery(getDisQuery, Discussion.class);
        query.setFirstResult(index);
        query.setMaxResults(size);
        query.setParameter("personId", person);
        List<Discussion> discussions = query.getResultList();
        return discussions;
    }
    
    public Integer getCountOfDiscussions(Person person) {
         TypedQuery<Long> query = em.createQuery(getCountOfDiscussions, Long.class);
         query.setParameter("personId", person);
         return query.getSingleResult().intValue();
    }
    
    public void updateDiscussion(Discussion d, List<Person> target){
        try {
            
           // d.getParticipants().clear();
            //d.getParticipants().addAll(target);
            d.setParticipants(target);
            em.merge(d);
//            Discussion discussion=find(d.getId());
//            discussion.setParticipants(target);
//            edit(discussion);
//            ArrayList<Person> rem = new ArrayList<Person>(d.getParticipants());
//            rem.removeAll(target);
//            
//            ArrayList<Person> addP = new ArrayList<Person>(target);
//            addP.removeAll(d.getParticipants());
//            
//            addParticipants(d, addP);
//            removeParticipants(d, rem);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void removeParticipant(Discussion discussion, Person p){
        discussion.getParticipants().remove(p);
        edit(discussion);
        
    }

}
