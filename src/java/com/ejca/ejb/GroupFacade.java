/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.ejb;

import com.ejca.entity.Group;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ramesh Kumar
 */
@Stateless
public class GroupFacade extends AbstractFacade<Group> {
    @PersistenceContext(unitName = "CloudComPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupFacade() {
        super(Group.class);
    }
    
}
