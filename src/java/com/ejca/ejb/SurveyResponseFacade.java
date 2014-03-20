/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejca.ejb;

import com.ejca.entity.SurveyResponse;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mido
 */
@Stateless
public class SurveyResponseFacade extends AbstractFacade<SurveyResponse> {
    @PersistenceContext(unitName = "CloudComPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SurveyResponseFacade() {
        super(SurveyResponse.class);
    }
    
}
