/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejca.ejb;

import com.ejca.entity.SolicitResponse;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mido
 */
@Stateless
public class SolicitResponseFacade extends AbstractFacade<SolicitResponse> {
    @PersistenceContext(unitName = "CloudComPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitResponseFacade() {
        super(SolicitResponse.class);
    }
    
}
