/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejca.ejb;

import com.ejca.entity.Bubble;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mido
 */
@Stateless
public class BubbleFacade extends AbstractFacade<Bubble> {
    @PersistenceContext(unitName = "CloudComPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BubbleFacade() {
        super(Bubble.class);
    }
    
}
