/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.ejb;

import com.ejca.entity.Comment;
import com.ejca.entity.Discussion;
import com.ejca.entity.Person;
import com.ejca.entity.SolicitBubble;
import com.ejca.entity.SolicitResponse;
import com.ejca.entity.SurveyBubble;
import com.ejca.entity.SurveyOptions;
import com.ejca.entity.SurveyResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rucha Kulkarni
 */
@Stateless
public class CloudComManager {
    @PersistenceContext EntityManager em;

    public Person getPerson(int id) {
        return null;
    }


    public List<Person> getAllPersons() {
        return null;
    }

    public Discussion getDiscussion(int id) {
        return null;
    }

    public void createSurvey() {

        try {
            SurveyBubble s = new SurveyBubble();
        Comment t = new Comment();
        s.setQuestion("What");
        ArrayList<SurveyOptions> te = new ArrayList<>();
        SurveyOptions o = new SurveyOptions();
        o.setChoice("hello");
        o.setSelectedCount(0);
        te.add(o);
        s.setSurveyOptionsCollection(te);
        s.setSurveyResponseCollection(new ArrayList<SurveyResponse>());
        t.setBubble(s);
        em.persist(t);
        } catch(javax.validation.ConstraintViolationException e) {
            Iterator i = e.getConstraintViolations().iterator();
            while(i.hasNext()) {
                System.out.println(i.next().toString());
            }
        }

//        Bubble t;
//        TypedQuery<Comment> query = em.createNamedQuery("Comment.findById", Comment.class);
//        query.setParameter("id", 1);
//        t = query.getSingleResult().getBubble();
//        if ( t instanceof SurveyBubble)
//                System.out.println(((SurveyBubble)t).getQuestion());

//          Person p = new Person();
//          p.setName("Thilak");
//          p.setRole("Student");
//          em.persist(p);
    }

        public void createSolicit(Comment comm) {
	        em.persist(comm);
	    }

        public void changeAttendance(Comment comm) {
	        em.merge(comm);
	    }
}
