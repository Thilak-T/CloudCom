/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.ejb;

import com.ejca.entity.Person;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ramesh Kumar
 */
@Stateless
public class PersonFacade extends AbstractFacade<Person> {
    @PersistenceContext(unitName = "CloudComPU")
    private EntityManager em;
    //@Inject Person person;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonFacade() {
        super(Person.class);
    }
    
    public void registerPerson(Person person) throws Exception {
        create(person);                
    }
    
}
